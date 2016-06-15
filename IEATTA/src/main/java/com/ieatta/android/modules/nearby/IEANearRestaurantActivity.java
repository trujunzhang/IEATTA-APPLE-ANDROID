package com.ieatta.android.modules.nearby;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.ieatta.android.R;
import com.ieatta.android.modules.adapter.NSIndexPath;
import com.ieatta.android.modules.cells.model.IEANearRestaurantMore;
import com.ieatta.android.modules.common.MainSegueIdentifier;
import com.ieatta.android.modules.tools.CollectionUtils;
import com.ieatta.android.notification.NSNotification;
import com.ieatta.android.observers.LocationObserver;
import com.parse.ParseGeoPoint;

import org.ieatta.tasks.NearRestaurantsTask;

import java.util.List;

public class IEANearRestaurantActivity extends LocationObserveActivity {
    private List fetchedRestaurants;

    private NearRestaurantsTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        task = new NearRestaurantsTask(this, this.recyclerView);
        task.makeActivePage();

        // TOOD djzhang(test)
//        this.queryNearRestaurant(null);
    }

    /// Add rows for section "More".
    private void configModelsInMoreSection() {
//        this.setRegisterCellClassWhenSelected(IEANearRestaurantMoreCell.getType(), NearRestaurantSection.sectionMoreItems.ordinal());
//
//        this.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.More), NearRestaurantSection.sectionMoreItems.ordinal());
//
//        this.setSectionItems(new LinkedList(getNearRestaurantMoresItems()), NearRestaurantSection.sectionMoreItems.ordinal());
    }

    @NonNull
    private List<IEANearRestaurantMore> getNearRestaurantMoresItems() {
        // "Manager Restaurant"
        IEANearRestaurantMore managerRestaurantItem = new IEANearRestaurantMore(R.drawable.restaurants_icon, R.string.Add_a_Restaurant, MainSegueIdentifier.editRestaurantSegueIdentifier);

        // "Search Restaurant"
        IEANearRestaurantMore searchRestaurant = new IEANearRestaurantMore(R.drawable.nav_search, R.string.Search_Restaurants, MainSegueIdentifier.searchRestaurantSegueIdentifier);

        // "Manager People"
        IEANearRestaurantMore managerPeople = new IEANearRestaurantMore(R.drawable.nav_add_friends, R.string.Manage_Friends, MainSegueIdentifier.managerPeopleSegueIdentifier);

        // "Read Reviews"
        IEANearRestaurantMore readReviews = new IEANearRestaurantMore(R.drawable.nav_passport_reviews, R.string.Read_Reviews, MainSegueIdentifier.readReviewsSegueIdentifier);

        IEANearRestaurantMore[] mores = {managerRestaurantItem, searchRestaurant, managerPeople, readReviews};
        return CollectionUtils.createList(mores);
    }

    // MARK: Query near restaurant list.
    public void queryNearRestaurant(ParseGeoPoint geoPoint) {

//        Restaurant.queryNearRestaurants(geoPoint)
//                .onSuccessTask(new Continuation<List , Task<Boolean>>() {
//                    @Override
//                    public Task<Boolean> then(Task<List > task) throws Exception {
//                        this.fetchedRestaurants = task.getResult();
//                        this.fetchedRestaurants = RestaurantSortUtils.sort(this.fetchedRestaurants);
//
//                        // Next, fetch related photos
//                        return this.getPhotosForModelsTask(task);
//                    }
//                }).onSuccess(new Continuation<Boolean, Void>() {
//            @Override
//            public Void then(Task<Boolean> task) throws Exception {
//
//                if (this.fetchedRestaurants.size() != 0) {
//                    this.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Nearby_Restaurants), NearRestaurantSection.sectionRestaurants.ordinal());
//                }
//                this.setSectionItems(this.fetchedRestaurants, NearRestaurantSection.sectionRestaurants.ordinal());
//
////                LocationObserver.sharedInstance.popLastLocation();
//
//                return null;
//            }
//        }, Task.UI_THREAD_EXECUTOR);
    }

//    @Override
//    public void whenSelectedEvent(Object model, NSIndexPath indexPath) {
////        if (indexPath.section == NearRestaurantSection.sectionMoreItems.ordinal()) {
////            this.whenSelectedCellTaped(((IEANearRestaurantMore) model).identifier);
////        } else {
//////            this.selectedModel = (Restaurant) model;
//////            this.whenSelectedCellTaped(MainSegueIdentifier.detailRestaurantSegueIdentifier);
////        }
//    }
//
//    // MARK: NSNotificationCenter notification handlers
//    @Override
//    protected void RestaurantWasCreated(NSNotification note) {
//        queryNearRestaurant(LocationObserver.sharedInstance.getCurrentPFGeoPoint());
//    }
//
//    @Override
//    protected void LocationDidChange(NSNotification note) {
//        queryNearRestaurant(LocationObserver.sharedInstance.getCurrentPFGeoPoint());
//    }

    @Override
    protected boolean shouldLeftBarButtonItem() {
        return false;
    }
}
