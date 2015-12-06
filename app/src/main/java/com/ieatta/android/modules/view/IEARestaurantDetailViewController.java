package com.ieatta.android.modules.view;

import android.os.Bundle;
import android.virtualbreak.com.manualdatabase.ActivityModelDebug;

import com.ieatta.android.R;
import com.ieatta.android.modules.IEAReviewsInDetailTableViewController;
import com.ieatta.android.modules.cells.IEARestaurantEventsCell;
import com.ieatta.android.modules.cells.headerview.IEARestaurantDetailHeaderCell;
import com.ieatta.android.modules.cells.model.IEARestaurantDetailHeader;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.android.modules.tools.CollectionUtils;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Event;
import com.ieatta.com.parse.models.Photo;
import com.ieatta.com.parse.models.Restaurant;
import com.ieatta.com.parse.models.Team;

import java.util.Collection;
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
    private Event selectedModel;
    // Fetched list by quering database.
    private List<ParseModelAbstract> fetchedEvents = new LinkedList<>();

    @Override
    public boolean havePhotoGallery() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO djzhang(test)
        self.restaurant = ActivityModelDebug.getRestaurantForRestaurantDetail();

        // Do any additional setup after loading the view.
//        assert(self.restaurant != nil, "Must setup selected restaurant.")

//        NSNotificationCenter.defaultCenter().addObserver(self, selector: "RestaurantWasCreated:", name: PAModelCreatedRestaurantNotification, object: nil)
//        NSNotificationCenter.defaultCenter().addObserver(self, selector: "EventWasCreated:", name: PAModelCreateEventNotification, object: nil)


        Event.queryEventsRelatedRestaurant(self.restaurant).onSuccessTask(new Continuation<List<ParseModelAbstract>, Task<List<ParseModelAbstract>>>() {
            @Override
            public Task<List<ParseModelAbstract>> then(Task<List<ParseModelAbstract>> task) throws Exception {
                self.fetchedEvents =  task.getResult();

                return Photo.queryPhotosByRestaurant(self.restaurant);
            }
        }).onSuccess(new Continuation<List<ParseModelAbstract>, Object>() {
            @Override
            public Object then(Task<List<ParseModelAbstract>> task) throws Exception {
                self.fetchedPhotosTask = task;

                // Finally, hide hud.
                self.hideHUD();

                self.setRegisterCellClass(IEARestaurantDetailHeaderCell.getType(), RestaurantDetailSection.sectionHeader.ordinal());
                self.setSectionItems(CollectionUtils.createList(new IEARestaurantDetailHeader(self, self.restaurant)), RestaurantDetailSection.sectionHeader.ordinal());

                self.showGoogleMapAddress(RestaurantDetailSection.sectionGoogleMapAddress.ordinal());
                self.setRegisterCellClassWhenSelected(IEARestaurantEventsCell.getType(), RestaurantDetailSection.sectionEvents.ordinal());
                self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Events_Recorded), RestaurantDetailSection.sectionEvents.ordinal());

                self.configureEventSection(self.fetchedEvents);
//            self.configurePhotoGallerySection(fetchedPhotosTask)
//            self.configureReviewsSection(task.result as! [Team])

                return null;
            }
        }).continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
                if (task.isFaulted() == true) {
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


}
