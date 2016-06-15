package org.ieatta.activity.restaurant;

import android.os.Bundle;

import com.ieatta.android.modules.IEAReviewsInDetailTableViewController;

import org.ieatta.tasks.RestaurantDetailTask;

public class IEARestaurantDetailViewController extends IEAReviewsInDetailTableViewController {
    private RestaurantDetailTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        task = new RestaurantDetailTask(this, this.recyclerView);
        task.makeActivePage();
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        this.transfer((Restaurant) this.getTransferedModel());
//
//        // Do any additional setup after loading the view.
////        assert(this.restaurant != nil, "Must setup selected restaurant.")
//
////        NSNotificationCenter.defaultCenter().addObserver(this, selector: "RestaurantWasCreated:", name: PAModelCreatedRestaurantNotification, object: nil)
////        NSNotificationCenter.defaultCenter().addObserver(this, selector: "EventWasCreated:", name: PAModelCreateEventNotification, object: nil)
//
//
//        Event.queryEventsRelatedRestaurant(this.restaurant)
//                .onSuccessTask(new Continuation<List , Task<List >>() {
//                    @Override
//                    public Task<List > then(Task<List > task) throws Exception {
//                        this.fetchedEvents = task.getResult();
//
//                        return Photo.queryPhotosByRestaurant(this.restaurant);
//                    }
//                }).onSuccessTask(new Continuation<List , Task<Boolean>>() {
//            @Override
//            public Task<Boolean> then(Task<List > task) throws Exception {
//                this.fetchedPhotosTask = task;
//
//                // Next, Load Reviews.
//                return this.getReviewsRelatedModelQueryTask();
//            }
//        }).onSuccess(new Continuation<Boolean, Object>() {
//            @Override
//            public Object then(Task<Boolean> task) throws Exception {
//                this.setRegisterCellClass(IEARestaurantDetailHeaderCell.getType(), RestaurantDetailSection.sectionHeader.ordinal());
//                this.setSectionItems(CollectionUtils.createList(new IEARestaurantDetailHeader(this, this.restaurant)), RestaurantDetailSection.sectionHeader.ordinal());
//
//                this.showGoogleMapAddress(RestaurantDetailSection.sectionGoogleMapAddress.ordinal());
//                this.setRegisterCellClassWhenSelected(IEARestaurantEventsCell.getType(), RestaurantDetailSection.sectionEvents.ordinal());
//                this.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Events_Recorded), RestaurantDetailSection.sectionEvents.ordinal());
//
//                this.configureDetailSection(this.fetchedEvents, R.string.Empty_for_Event, IEARestaurantEventsCell.getType(),RestaurantDetailSection.sectionEvents.ordinal());
//                this.configureReviewsSection();
//                this.configurePhotoGallerySection(fetchedPhotosTask);
//
//                return null;
//            }
//        }, Task.UI_THREAD_EXECUTOR);
//    }
//
//    // MARK: Override IEAReviewsTableViewController methods
//    @Override
//    public int getReviewsSectionIndex() {
//        return RestaurantDetailSection.sectionReviews.ordinal();
//    }
//
//    // MARK: Override IEABaseTableViewController methods
//    @Override
//    public ParseModelAbstract getPageModel() {
//        return this.restaurant;
//    }
//
//    // MARK: Override IEAPhotoGalleryViewController methods
////    @Override
//    public int getPhotoGallerySectionIndex() {
//        return RestaurantDetailSection.sectionPhotos.ordinal();
//    }
//
//    @Override
//    public void whenSelectedEvent(Object model, NSIndexPath indexPath) {
//        if (indexPath.section == RestaurantDetailSection.sectionEvents.ordinal()) {
//            Event event = (Event) model;
//            event.belongToModel = this.restaurant;
//            this.selectedModel = event;
//            this.performSegueWithIdentifier(MainSegueIdentifier.detailEventSegueIdentifier, this);
//        } else {
//            super.whenSelectedEvent(model, indexPath);
//        }
//    }
//
//    @Override
//    protected void segueForEditRestaurantViewController(IEAEditRestaurantViewController destination, Intent sender) {
//        // Edit Restaurant
//        this.setTransferedModelForEdit(sender, this.restaurant);
//    }
//
//    @Override
//    protected void segueForEventDetailViewController(IEAEventDetailViewController destination, Intent sender) {
//        /// Show detailed event
//        this.setTransferedModel(sender, this.selectedModel);
//    }
//
//    @Override
//    protected void segueForEditEventViewController(IEAEditEventViewController destination, Intent sender) {
//        // Add Event
//        this.setTransferedModelForEdit(sender, new Event(this.restaurant), true);
//    }
//
//    // MARK: TableView header events
//    public void performSegueForEditingModel() {
//        this.performSegueWithIdentifier(MainSegueIdentifier.editRestaurantSegueIdentifier, this);
//    }
//
//    public void performSegueForAddingEvent() {
//        this.performSegueWithIdentifier(MainSegueIdentifier.editEventSegueIdentifier, this);
//    }
//
//    // MARK: NSNotificationCenter notification handlers
//    @Override
//    protected void RestaurantWasCreated(NSNotification note) {
//        setSectionItems(CollectionUtils.createList(new IEARestaurantDetailHeader(this, this.restaurant)), RestaurantDetailSection.sectionHeader.ordinal());
//    }
//
//    @Override
//    protected void EventWasCreated(NSNotification note) {
//        // 3. load events related restaurant.
//        Event.queryEventsRelatedRestaurant(this.restaurant)
//                .onSuccess(new Continuation<List , Object>() {
//                    @Override
//                    public Object then(Task<List > task) throws Exception {
//                        this.fetchedEvents = task.getResult();
//                        this.configureDetailSection(this.fetchedEvents, R.string.Empty_for_Event,IEARestaurantEventsCell.getType(), RestaurantDetailSection.sectionEvents.ordinal());
//                        return null;
//                    }
//                });
//    }
}
