package com.ieatta.android.modules.view;

import android.os.Bundle;

import com.ieatta.android.modules.IEASplitMasterViewController;
import com.ieatta.android.modules.cells.IEANearRestaurantMoreCell;
import com.ieatta.android.modules.cells.IENearRestaurantMore;

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
        IENearRestaurantMore managerRestaurantItem =new  IENearRestaurantMore(icon: UIImage.Asset._28x28Restaurants.image,title: L10n.AddARestaurant.string,identifier: MainSegueIdentifier.editRestaurantSegueIdentifier)

        // "Search Restaurant"
        IENearRestaurantMore searchRestaurant =new  IENearRestaurantMore(icon: UIImage.Asset._28x28Search.image,title: L10n.SearchRestaurants.string,identifier: MainSegueIdentifier.searchRestaurantSegueIdentifier)

        // "Manager People"
        IENearRestaurantMore managerPeople =new  IENearRestaurantMore(icon: UIImage.Asset._24x24Friends.image,title: L10n.ManageFriends.string,identifier: MainSegueIdentifier.managerPeopleSegueIdentifier)

        // "Read Reviews"
        IENearRestaurantMore readReviews =new  IENearRestaurantMore(icon: UIImage.Asset._28x28Review.image,title: L10n.ReadReviews.string,identifier: MainSegueIdentifier.readReviewsSegueIdentifier)

        setSectionItems([managerRestaurantItem,searchRestaurant,managerPeople,readReviews], forSectionIndex: NearRestaurantSection.sectionMoreItems.rawValue)
    }

}
