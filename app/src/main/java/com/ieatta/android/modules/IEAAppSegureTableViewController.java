package com.ieatta.android.modules;

import com.ieatta.android.modules.common.MainSegueIdentifier;
import com.ieatta.android.modules.view.IEAChoicePeopleViewController;
import com.ieatta.android.modules.view.IEAEventDetailViewController;
import com.ieatta.android.modules.view.IEAOrderedRecipesViewController;
import com.ieatta.android.modules.view.IEARecipeDetailViewController;
import com.ieatta.android.modules.view.IEARestaurantDetailViewController;
import com.ieatta.android.modules.view.IEAReviewDetailViewController;
import com.ieatta.android.modules.view.IEASeeReviewsInDetailViewController;
import com.ieatta.android.modules.view.edit.IEAEditEventViewController;
import com.ieatta.android.modules.view.edit.IEAEditPeopleViewController;
import com.ieatta.android.modules.view.edit.IEAEditRecipeViewController;
import com.ieatta.android.modules.view.edit.IEAEditRestaurantViewController;

import static com.ieatta.android.modules.common.MainSegueIdentifier.*;

/**
 * Created by djzhang on 12/1/15.
 */
public class IEAAppSegureTableViewController extends IEAAppTableViewController {
    private IEAAppSegureTableViewController self = this;

    public void prepareForSegue(MainSegueIdentifier identifier) {
//        switch (identifier) {
//            // Four menus in the near restaurant page.
//            case editRestaurantSegueIdentifier:
//                self.segueForEditRestaurantViewController((segue.destinationViewController as ? IEAEditRestaurantViewController) !)
//                break;
//            case searchRestaurantSegueIdentifier:
//                break;
//            case managerPeopleSegueIdentifier:
//                break;
//            case readReviewsSegueIdentifier:
//                break;
//            // Four detail pages.
//            case detailRestaurantSegueIdentifier:
//                self.segueForRestaurantDetailViewController((segue.destinationViewController as ? IEARestaurantDetailViewController) !)
//                break;
//            case detailEventSegueIdentifier:
//                self.segueForEventDetailViewController((segue.destinationViewController as ? IEAEventDetailViewController) !)
//                break;
//            case detailOrderedRecipesSegueIdentifier:
//                self.segueForOrderedRecipesViewController((segue.destinationViewController as ? IEAOrderedRecipesViewController) !)
//                break;
//            case detailRecipeSegueIdentifier:
//                self.segueForRecipeDetailViewController((segue.destinationViewController as ? IEARecipeDetailViewController) !)
//                break;
//
//            // Show all posted reviews for restaurant,recipe.
//            case detailSeeReviewSegueIdentifier:
//                self.segueForSeeReviewsInDetailViewController((segue.destinationViewController as ? IEASeeReviewsInDetailViewController) !)
//                break;
//            // Show detail review from review list.
//            case detailReviewSegueIdentifier:
//                self.segueForReviewDetailViewController((segue.destinationViewController as ? IEAReviewDetailViewController) !)
//                break;
//            // Four new/edit model pages.(the following three, and restaurant)
//            case editEventSegueIdentifier:
//                self.segueForEditEventViewController((segue.destinationViewController as ? IEAEditEventViewController) !)
//                break;
//            case editPeopleSegueIdentifier:
//                self.segueForEditPeopleViewController((segue.destinationViewController as ? IEAEditPeopleViewController) !)
//                break;
//            case editRecipeSegueIdentifier:
//                self.segueForEditRecipeViewController((segue.destinationViewController as ? IEAEditRecipeViewController) !)
//                break;
//            // Choice Person in the event page.
//            case choicePeopleSegueIdentifier:
//                self.segueForChoicePeopleViewController((segue.destinationViewController as ? IEAChoicePeopleViewController) !)
//                break;
//        }
    }

    protected void segueForEditRestaurantViewController(IEAEditRestaurantViewController destination) {
    }

    protected void segueForRestaurantDetailViewController(IEARestaurantDetailViewController destination) {
    }

    protected void segueForEventDetailViewController(IEAEventDetailViewController destination) {
    }

    protected void segueForEditEventViewController(IEAEditEventViewController destination) {
    }

    protected void segueForOrderedRecipesViewController(IEAOrderedRecipesViewController destination) {
    }

    protected void segueForChoicePeopleViewController(IEAChoicePeopleViewController destination) {
    }

    protected void segueForEditPeopleViewController(IEAEditPeopleViewController destination) {
    }

    protected void segueForRecipeDetailViewController(IEARecipeDetailViewController destination) {
    }

    protected void segueForEditRecipeViewController(IEAEditRecipeViewController destination) {
    }

    protected void segueForReviewDetailViewController(IEAReviewDetailViewController destination) {
    }

    protected void segueForSeeReviewsInDetailViewController(IEASeeReviewsInDetailViewController destination) {
    }
}
