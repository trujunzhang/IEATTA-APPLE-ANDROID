package com.ieatta.android.modules.view;

import android.os.Bundle;

import com.ieatta.android.R;
import com.ieatta.android.modules.IEASplitMasterViewController;
import com.ieatta.android.modules.cells.IEANearRestaurantMoreCell;
import com.ieatta.android.modules.cells.IENearRestaurantMore;
import com.ieatta.android.modules.common.MainSegueIdentifier;

/**
 * Created by djzhang on 12/1/15.
 */

enum NearRestaurantSection  {
         sectionMoreItems  ,//= 0
         sectionRestaurants, //= 1
        }

public class IEANearRestaurantViewController extends IEASplitMasterViewController {
    private IEANearRestaurantViewController self =this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    /// Add rows for section "More".
    private void configModelsInMoreSection(){
//        self.setRegisterCellClassWhenSelected(IEANearRestaurantMoreCell.self);

//        self.appendSectionTitleCell(SectionTitleCellModel(editKey: IEAEditKey.Section_Title, title: L10n.More.string), forSectionIndex: NearRestaurantSection.sectionMoreItems.rawValue)

        // "Manager Restaurant"
        IENearRestaurantMore managerRestaurantItem =new  IENearRestaurantMore( R.drawable.restaurants_icon, R.string.Add_a_Restaurant, MainSegueIdentifier.editRestaurantSegueIdentifier);

        // "Search Restaurant"
        IENearRestaurantMore searchRestaurant =new  IENearRestaurantMore( R.drawable.nav_search, R.string.Search_Restaurants, MainSegueIdentifier.searchRestaurantSegueIdentifier);

        // "Manager People"
        IENearRestaurantMore managerPeople =new  IENearRestaurantMore( R.drawable.nav_add_friends, R.string.Manage_Friends, MainSegueIdentifier.managerPeopleSegueIdentifier);

        // "Read Reviews"
        IENearRestaurantMore readReviews =new  IENearRestaurantMore( R.drawable.restaurants_icon, R.string.Read_Reviews, MainSegueIdentifier.readReviewsSegueIdentifier);

//        setSectionItems([managerRestaurantItem,searchRestaurant,managerPeople,readReviews], forSectionIndex: NearRestaurantSection.sectionMoreItems.rawValue)
    }

}
