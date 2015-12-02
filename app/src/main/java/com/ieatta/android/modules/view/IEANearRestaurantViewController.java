package com.ieatta.android.modules.view;

import android.os.Bundle;

import com.ieatta.android.R;
import com.ieatta.android.modules.IEASplitMasterViewController;
import com.ieatta.android.modules.cells.IEANearRestaurantMoreCell;
import com.ieatta.android.modules.cells.IEANearRestaurantsCell;
import com.ieatta.android.modules.cells.model.IENearRestaurantMore;
import com.ieatta.android.modules.common.MainSegueIdentifier;
import com.ieatta.android.modules.common.edit.IEAEditKey;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.android.modules.tools.RestaurantSortUtils;
import com.ieatta.android.observers.LocationObserver;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Restaurant;
import com.parse.ParseGeoPoint;

import java.util.Arrays;
import java.util.LinkedList;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 12/1/15.
 */

enum NearRestaurantSection {
    sectionMoreItems,//= 0
    sectionRestaurants, //= 1
}

public class IEANearRestaurantViewController extends IEASplitMasterViewController {
    private IEANearRestaurantViewController self = this;
    private LinkedList<Object> fetchedRestaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Register Cells by class.
        self.setRegisterCellClassWhenSelected(IEANearRestaurantsCell.class,NearRestaurantSection.sectionRestaurants.ordinal(),IEANearRestaurantsCell.layoutResId);

        self.configModelsInMoreSection();

        // TOOD djzhang(test)
//        self.queryNearRestaurant(null);
    }

    /// Add rows for section "More".
    private void configModelsInMoreSection() {
        self.setRegisterCellClassWhenSelected(IEANearRestaurantMoreCell.class,NearRestaurantSection.sectionMoreItems.ordinal(),IEANearRestaurantMoreCell.layoutResId);

        self.appendSectionTitleCell(new SectionTitleCellModel( IEAEditKey.Section_Title, R.string.More),  NearRestaurantSection.sectionMoreItems.ordinal());

        // "Manager Restaurant"
        IENearRestaurantMore managerRestaurantItem = new IENearRestaurantMore(R.drawable.restaurants_icon, R.string.Add_a_Restaurant, MainSegueIdentifier.editRestaurantSegueIdentifier);

        // "Search Restaurant"
        IENearRestaurantMore searchRestaurant = new IENearRestaurantMore(R.drawable.nav_search, R.string.Search_Restaurants, MainSegueIdentifier.searchRestaurantSegueIdentifier);

        // "Manager People"
        IENearRestaurantMore managerPeople = new IENearRestaurantMore(R.drawable.nav_add_friends, R.string.Manage_Friends, MainSegueIdentifier.managerPeopleSegueIdentifier);

        // "Read Reviews"
        IENearRestaurantMore readReviews = new IENearRestaurantMore(R.drawable.restaurants_icon, R.string.Read_Reviews, MainSegueIdentifier.readReviewsSegueIdentifier);

        IENearRestaurantMore[] mores = {managerRestaurantItem,searchRestaurant,managerPeople,readReviews};
        self.setSectionItems(new LinkedList<Object>(Arrays.asList(mores)),  NearRestaurantSection.sectionMoreItems.ordinal());
    }

    // MARK: Query near restaurant list.
    public void queryNearRestaurant(ParseGeoPoint geoPoint) {
        // TODO djzhang(test)
        geoPoint = LocationObserver.sharedInstance.getCurrentPFGeoPoint();

        Restaurant.queryNearRestaurants(geoPoint).continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
                // Next, fetch related photos
                return self.getPhotosForModelsTask(task);
            }
        }).continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {

                self.fetchedRestaurants = (LinkedList<Object>) task.getResult();

                self.fetchedRestaurants = RestaurantSortUtils.sort(self.fetchedRestaurants);

                if (self.fetchedRestaurants.size() != 0) {
                    self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title,R.string.Nearby_Restaurants),  NearRestaurantSection.sectionRestaurants.ordinal());
                }
                self.setSectionItems(self.fetchedRestaurants, NearRestaurantSection.sectionRestaurants.ordinal());

//                LocationObserver.sharedInstance.popLastLocation();


                return null;
            }
        });
    }

}
