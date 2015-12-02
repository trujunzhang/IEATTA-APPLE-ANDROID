package com.ieatta.android.modules.view;

import android.os.Bundle;

import com.ieatta.android.modules.IEAReviewsInDetailTableViewController;
import com.ieatta.android.modules.common.edit.IEAEditKey;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Event;
import com.ieatta.com.parse.models.Photo;
import com.ieatta.com.parse.models.Restaurant;
import com.ieatta.com.parse.models.Team;

import java.util.LinkedList;

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

    IEARestaurantDetailViewController transfer(Restaurant selectedModel)  {
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
    //    var fetchedEvents:[Event] = [Event]()

    @Override
    public boolean havePhotoGallery() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        // Do any additional setup after loading the view.
//        assert(self.restaurant != nil, "Must setup selected restaurant.")

//        NSNotificationCenter.defaultCenter().addObserver(self, selector: "RestaurantWasCreated:", name: PAModelCreatedRestaurantNotification, object: nil)
//        NSNotificationCenter.defaultCenter().addObserver(self, selector: "EventWasCreated:", name: PAModelCreateEventNotification, object: nil)


        final LinkedList<Object> fetchedEvents = new LinkedList<>();// Event
//        var fetchedPhotosTask = BFTask()

Event.queryEventsRelatedRestaurant(self.restaurant)
        .continueWith(new Continuation<Object, Object>() {
    @Override
    public Object then(Task<Object> task) throws Exception {
//        fetchedEvents = new LinkedList<Object>(task.getResult());
        // Next, Load photo gallery.
        return Photo.queryPhotosByRestaurant(self.restaurant);
    }
}).continueWith(new Continuation<Object, Object>() {
    @Override
    public Object then(Task<Object> task) throws Exception {
//        fetchedPhotosTask = task;

        // Next, Load Reviews.
        return self.getReviewsReleatdModelQueryTask();
    }
}).continueWith(new Continuation<Object, Object>() {
    @Override
    public Object then(Task<Object> task) throws Exception {
        if(task.getError()!= null){

        }else{
            // Finally, hide hud.
            self.hideHUD();

//            self.setRegisterCellClass(IEARestaurantDetailHeaderCell)
//            self.setRegisterCellClassWhenSelected(IEARestaurantEventsCell.self)

//            self.appendSectionTitleCell(SectionTitleCellModel(editKey: IEAEditKey.Section_Title, title: ""), forSectionIndex: RestaurantDetailSection.sectionHeader.rawValue)

//            self.setSectionItems([IEARestaurantDetailHeader(viewController: self, model: self.restaurant!)], forSectionIndex: RestaurantDetailSection.sectionHeader.rawValue)

//            self.appendSectionTitleCell(SectionTitleCellModel(editKey: IEAEditKey.Section_Title, title: L10n.EventsRecorded.string), forSectionIndex: RestaurantDetailSection.sectionEvents.rawValue)

            self.showGoogleMapAddress(RestaurantDetailSection.sectionGoogleMapAddress.ordinal());

            self.configureEventSection(fetchedEvents);
//            self.configurePhotoGallerySection(fetchedPhotosTask)
//            self.configureReviewsSection(task.result as! [Team])
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
    public ParseModelAbstract getPageModel()  {
        return self.restaurant;
    }


    private void configureEventSection(LinkedList<Object> fetchedEvents){
        self.setSectionItems(fetchedEvents,  RestaurantDetailSection.sectionEvents.ordinal());
    }

    // MARK: Override IEAPhotoGalleryViewController methods
//    @Override
    public int getPhotoGallerySectionIndex() {
        return RestaurantDetailSection.sectionPhotos.ordinal();
    }


}
