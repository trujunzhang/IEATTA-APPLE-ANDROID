package com.ieatta.android.modules.view;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.ieatta.android.R;
import com.ieatta.android.modules.IEASplitMasterViewController;
import com.ieatta.android.modules.cells.IEANearRestaurantMoreCell;
import com.ieatta.android.modules.cells.IEANearRestaurantsCell;
import com.ieatta.android.modules.cells.model.IEANearRestaurantMore;
import com.ieatta.android.modules.common.MainSegueIdentifier;
import com.ieatta.android.modules.common.edit.IEAEditKey;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.android.modules.tools.RestaurantSortUtils;
import com.ieatta.android.observers.LocationObserver;
import com.ieatta.com.parse.models.Restaurant;
import com.parse.ParseGeoPoint;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
        self.setRegisterCellClassWhenSelected(IEANearRestaurantsCell.class, IEANearRestaurantsCell.layoutResId, NearRestaurantSection.sectionRestaurants.ordinal());

        self.configModelsInMoreSection();

        // TOOD djzhang(test)
        self.queryNearRestaurant(null);
    }

    /// Add rows for section "More".
    private void configModelsInMoreSection() {
        self.setRegisterCellClassWhenSelected(IEANearRestaurantMoreCell.class, IEANearRestaurantMoreCell.layoutResId, NearRestaurantSection.sectionMoreItems.ordinal());

        self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.More), NearRestaurantSection.sectionMoreItems.ordinal());

        self.setSectionItems(new LinkedList<Object>(getNearRestaurantMoresItems()), NearRestaurantSection.sectionMoreItems.ordinal());
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
        IEANearRestaurantMore readReviews = new IEANearRestaurantMore(R.drawable.restaurants_icon, R.string.Read_Reviews, MainSegueIdentifier.readReviewsSegueIdentifier);

        IEANearRestaurantMore[] mores = {managerRestaurantItem, searchRestaurant, managerPeople, readReviews};
        return Arrays.asList(mores);
    }

    // MARK: Query near restaurant list.
    public void queryNearRestaurant(ParseGeoPoint geoPoint) {
        // TODO djzhang(test)
        geoPoint = LocationObserver.sharedInstance.getCurrentPFGeoPoint();

        Restaurant.queryRestaurants().continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
                // Next, fetch related photos
                return self.getPhotosForModelsTask(task);
            }
        }).continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
                if(task.getError() != null){
                    Exception error = task.getError();
                    int x = 0;
                }else {

                    self.fetchedRestaurants = (LinkedList<Object>) task.getResult();

                    self.fetchedRestaurants = RestaurantSortUtils.sort(self.fetchedRestaurants);

                    if (self.fetchedRestaurants.size() != 0) {
                        self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Nearby_Restaurants), NearRestaurantSection.sectionRestaurants.ordinal());
                    }
                    self.setSectionItems(self.fetchedRestaurants, NearRestaurantSection.sectionRestaurants.ordinal());

//                LocationObserver.sharedInstance.popLastLocation();
                }

                return null;
            }
        });
    }

}
