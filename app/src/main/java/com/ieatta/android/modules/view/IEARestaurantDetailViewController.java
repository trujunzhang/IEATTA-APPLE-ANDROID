package com.ieatta.android.modules.view;

import android.content.Intent;
import android.os.Bundle;

import com.ieatta.android.R;
import com.ieatta.android.modules.IEAReviewsInDetailTableViewController;
import com.ieatta.android.modules.adapter.NSIndexPath;
import com.ieatta.android.modules.cells.IEARestaurantEventsCell;
import com.ieatta.android.modules.cells.headerview.IEARestaurantDetailHeaderCell;
import com.ieatta.android.modules.cells.model.IEARestaurantDetailHeader;
import com.ieatta.android.modules.common.MainSegueIdentifier;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.tools.CollectionUtils;
import com.ieatta.android.modules.view.edit.IEAEditEventViewController;
import com.ieatta.android.modules.view.edit.IEAEditRestaurantViewController;
import com.ieatta.android.notification.NSNotification;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Event;
import com.ieatta.com.parse.models.Photo;
import com.ieatta.com.parse.models.Restaurant;

import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 12/1/15.
 */
enum RestaurantDetailSection {
    sectionHeader,//= 0
    sectionGoogleMapAddress,//= 1
    sectionEvents,//= 2
    sectionPhotos,//= 3
    sectionReviews,//= 4
}

public class IEARestaurantDetailViewController extends IEAReviewsInDetailTableViewController {
    private IEARestaurantDetailViewController self = this;

    IEARestaurantDetailViewController transfer(Restaurant selectedModel) {
        self.restaurant = selectedModel;
        return self;
    }

    @Override
    public boolean shouldShowHUD() {
        return true;
    }

    // Transferd Model from previous page.
    private Restaurant restaurant;
    // Selected model from tableview.
    private ParseModelAbstract selectedModel;
    // Fetched list by quering database.
    private List<ParseModelAbstract> fetchedEvents = new LinkedList<>();

    @Override
    public boolean havePhotoGallery() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        self.transfer((Restaurant) self.getTransferedModel());

        // Do any additional setup after loading the view.
//        assert(self.restaurant != nil, "Must setup selected restaurant.")

//        NSNotificationCenter.defaultCenter().addObserver(self, selector: "RestaurantWasCreated:", name: PAModelCreatedRestaurantNotification, object: nil)
//        NSNotificationCenter.defaultCenter().addObserver(self, selector: "EventWasCreated:", name: PAModelCreateEventNotification, object: nil)


        Event.queryEventsRelatedRestaurant(self.restaurant)
                .onSuccessTask(new Continuation<List<ParseModelAbstract>, Task<List<ParseModelAbstract>>>() {
                    @Override
                    public Task<List<ParseModelAbstract>> then(Task<List<ParseModelAbstract>> task) throws Exception {
                        self.fetchedEvents = task.getResult();

                        return Photo.queryPhotosByRestaurant(self.restaurant);
                    }
                }).onSuccessTask(new Continuation<List<ParseModelAbstract>, Task<Boolean>>() {
            @Override
            public Task<Boolean> then(Task<List<ParseModelAbstract>> task) throws Exception {
                self.fetchedPhotosTask = task;

                // Next, Load Reviews.
                return self.getReviewsRelatedModelQueryTask();
            }
        }).onSuccess(new Continuation<Boolean, Object>() {
            @Override
            public Object then(Task<Boolean> task) throws Exception {
                self.setRegisterCellClass(IEARestaurantDetailHeaderCell.getType(), RestaurantDetailSection.sectionHeader.ordinal());
                self.setSectionItems(CollectionUtils.createList(new IEARestaurantDetailHeader(self, self.restaurant)), RestaurantDetailSection.sectionHeader.ordinal());

                self.showGoogleMapAddress(RestaurantDetailSection.sectionGoogleMapAddress.ordinal());
                self.setRegisterCellClassWhenSelected(IEARestaurantEventsCell.getType(), RestaurantDetailSection.sectionEvents.ordinal());
                self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Events_Recorded), RestaurantDetailSection.sectionEvents.ordinal());

                self.configureEventSection(self.fetchedEvents);
                self.configureReviewsSection();
                self.configurePhotoGallerySection(fetchedPhotosTask);

                return null;
            }
        }).continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
                // Finally, hide hud.
                self.hideHUD();

                if (task.isFaulted() == true) {
                    Exception error = task.getError();
                    String message = error.getMessage();
                    Throwable cause = error.getCause();
                }
                return null;
            }
        });
    }

    // MARK: Override IEAReviewsTableViewController methods
    @Override
    public int getReviewsSectionIndex() {
        return RestaurantDetailSection.sectionReviews.ordinal();
    }

    // MARK: Override IEABaseTableViewController methods
    @Override
    public ParseModelAbstract getPageModel() {
        return self.restaurant;
    }


    private void configureEventSection(List<ParseModelAbstract> fetchedEvents) {
        self.setSectionItems(fetchedEvents, RestaurantDetailSection.sectionEvents.ordinal());
    }

    // MARK: Override IEAPhotoGalleryViewController methods
//    @Override
    public int getPhotoGallerySectionIndex() {
        return RestaurantDetailSection.sectionPhotos.ordinal();
    }

    @Override
    public void whenSelectedEvent(Object model, NSIndexPath indexPath) {
        if (indexPath.section == RestaurantDetailSection.sectionEvents.ordinal()) {
            Event event = (Event) model;
            event.belongToModel = self.restaurant;
            self.selectedModel = event;
            self.performSegueWithIdentifier(MainSegueIdentifier.detailEventSegueIdentifier, self);
        } else {
            super.whenSelectedEvent(model, indexPath);
        }
    }

    @Override
    protected void segueForEditRestaurantViewController(IEAEditRestaurantViewController destination, Intent sender) {
        // Edit Restaurant
        self.setTransferedModelForEdit(sender, self.restaurant);
    }

    @Override
    protected void segueForEventDetailViewController(IEAEventDetailViewController destination, Intent sender) {
        /// Show detailed event
        self.setTransferedModel(sender, self.selectedModel);
    }

    @Override
    protected void segueForEditEventViewController(IEAEditEventViewController destination, Intent sender) {
        // Add Event
        self.setTransferedModelForEdit(sender, new Event(self.restaurant), true);
    }

    // MARK: TableView header events
    public void performSegueForEditingModel() {
        self.performSegueWithIdentifier(MainSegueIdentifier.editRestaurantSegueIdentifier, self);
    }

    public void performSegueForAddingEvent() {
        self.performSegueWithIdentifier(MainSegueIdentifier.editEventSegueIdentifier, self);
    }

    // MARK: NSNotificationCenter notification handlers
    @Override
    protected void RestaurantWasCreated(NSNotification note) {
        setSectionItems(CollectionUtils.createList(new IEARestaurantDetailHeader(self, self.restaurant)), RestaurantDetailSection.sectionHeader.ordinal());
    }

    @Override
    protected void EventWasCreated(NSNotification note) {
        // 3. load events related restaurant.
        Event.queryEventsRelatedRestaurant(self.restaurant)
                .onSuccess(new Continuation<List<ParseModelAbstract>, Object>() {
                    @Override
                    public Object then(Task<List<ParseModelAbstract>> task) throws Exception {
                        self.fetchedEvents = task.getResult();
                        self.configureEventSection(self.fetchedEvents);
                        return null;
                    }
                });
    }
}
