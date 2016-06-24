package com.ieatta.android.modules;

import android.content.Intent;
import android.view.View;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.android.modules.adapter.NSIndexPath;
import com.ieatta.android.modules.adapter.RecyclerItemClickListener;
import com.ieatta.android.modules.common.MainSegueIdentifier;
import com.ieatta.android.modules.view.IEAChoicePeopleViewController;
import com.ieatta.android.modules.view.IEAEventDetailViewController;
import com.ieatta.android.modules.view.IEAOrderedRecipesViewController;
import com.ieatta.android.modules.view.IEARecipeDetailViewController;

import org.ieatta.activity.restaurant.IEARestaurantDetailActivity;

import com.ieatta.android.modules.view.IEAReviewDetailViewController;
import com.ieatta.android.modules.view.IEASeeReviewsInDetailViewController;
import com.ieatta.android.modules.view.edit.IEAEditEventViewController;
import com.ieatta.android.modules.view.edit.IEAEditPeopleViewController;
import com.ieatta.android.modules.view.edit.IEAEditRecipeViewController;
import com.ieatta.android.modules.view.edit.IEAEditRestaurantViewController;
import com.ieatta.android.modules.view.photogallery.PhotoGalleryPagerActivity;
import com.ieatta.android.modules.view.posts.IEAWriteReviewViewController;

public class IEAAppSegureTableViewController extends IEANotificationTableViewController implements RecyclerItemClickListener {
    public void performSegueWithIdentifier(MainSegueIdentifier identifier, com.ieatta.android.IEAPageActivity sender) {
        Intent intent = new Intent(EnvironmentUtils.sharedInstance.getGlobalContext(), identifier.getActivity());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.prepareForSegue(identifier, intent);
        this.startActivity(intent);
    }

    public void prepareForSegue(MainSegueIdentifier identifier, Intent sender) {
        switch (identifier) {
            // Four menus in the near restaurant page.
            case editRestaurantSegueIdentifier:
                this.segueForEditRestaurantViewController(null, sender);
                break;
            case searchRestaurantSegueIdentifier:
                break;
            case managerPeopleSegueIdentifier:
                break;
            case readReviewsSegueIdentifier:
                break;
            // Four detail pages.
            case detailRestaurantSegueIdentifier:
                this.segueForRestaurantDetailViewController(null, sender);
                break;
            case detailEventSegueIdentifier:
                this.segueForEventDetailViewController(null, sender);
                break;
            case detailOrderedRecipesSegueIdentifier:
                this.segueForOrderedRecipesViewController(null, sender);
                break;
            case detailRecipeSegueIdentifier:
                this.segueForRecipeDetailViewController(null, sender);
                break;

            // Show all posted reviews for restaurant,recipe.
            case detailSeeReviewSegueIdentifier:
                this.segueForSeeReviewsInDetailViewController(null, sender);
                break;
            // Show detail review from review list.
            case detailReviewSegueIdentifier:
                this.segueForReviewDetailViewController(null, sender);
                break;
            // Four new/edit model pages.(the following three, and restaurant)
            case editEventSegueIdentifier:
                this.segueForEditEventViewController(null, sender);
                break;
            case editPeopleSegueIdentifier:
                this.segueForEditPeopleViewController(null, sender);
                break;
            case editRecipeSegueIdentifier:
                this.segueForEditRecipeViewController(null, sender);
                break;
            // Choice Person in the event page.
            case choicePeopleSegueIdentifier:
                this.segueForChoicePeopleViewController(null, sender);
                break;
            case postReviewSegueIdentifier:
                this.segueForPostReviewViewController(null, sender);
                break;
            case photoPagesControllerSegueIdentifier:
                this.segueForPhotoPagesController(null, sender);

        }
    }

    protected void segueForPhotoPagesController(PhotoGalleryPagerActivity destination, Intent sender) {

    }

    protected void segueForPostReviewViewController(IEAWriteReviewViewController destination, Intent sender) {

    }

    protected void segueForEditRestaurantViewController(IEAEditRestaurantViewController destination, Intent sender) {
    }

    protected void segueForRestaurantDetailViewController(IEARestaurantDetailActivity destination, Intent sender) {
    }

    protected void segueForEventDetailViewController(IEAEventDetailViewController destination, Intent sender) {
    }

    protected void segueForEditEventViewController(IEAEditEventViewController destination, Intent sender) {
    }

    protected void segueForOrderedRecipesViewController(IEAOrderedRecipesViewController destination, Intent sender) {
    }

    protected void segueForChoicePeopleViewController(IEAChoicePeopleViewController destination, Intent sender) {
    }

    protected void segueForEditPeopleViewController(IEAEditPeopleViewController destination, Intent sender) {
    }

    protected void segueForRecipeDetailViewController(IEARecipeDetailViewController destination, Intent sender) {
    }

    protected void segueForEditRecipeViewController(IEAEditRecipeViewController destination, Intent sender) {
    }

    protected void segueForReviewDetailViewController(IEAReviewDetailViewController destination, Intent sender) {
    }

    protected void segueForSeeReviewsInDetailViewController(IEASeeReviewsInDetailViewController destination, Intent sender) {
    }

    public void whenSelectedEvent(Object model, NSIndexPath indexPath) {
//        fatalError("whenSelectedEvent() has not been implemented");
    }

    @Override
    public void onItemClick(View view, NSIndexPath indexPath, Object model, int position, boolean isLongClick) {
        this.whenSelectedEvent(model, indexPath);
//        if (isLongClick) {
//            Toast.makeText(this, "#" + position + " - " + " (Long click)", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "#" + position + " - " + indexPath.toString(), Toast.LENGTH_SHORT).show();
//        }
    }
//
//    protected ParseModelAbstract getTransferedModel() {
//        return this.getTransferedModel(IntentCache.intentUUID);
//    }
//
//    protected ParseModelAbstract getTransferedModel(String name) {
//        String intentUUID = this.getIntent().getExtras().getString(name);
//        ParseModelAbstract transferedModel = IntentCache.sharedInstance.getIntentModel(intentUUID);
//        return transferedModel;
//    }
//
//    protected void setTransferedModel(Intent sender, ParseModelAbstract model) {
//        this.setTransferedModel(sender, model, IntentCache.intentUUID);
//    }
//
//    protected void setTransferedModel(Intent sender, ParseModelAbstract model, String name) {
//        IntentCache.sharedInstance.setIntentModel(model);
//        sender.putExtra(name, model.intentUUID);
//    }
//
//    protected void setTransferedModelForEdit(Intent sender, ParseModelAbstract model) {
//        this.setTransferedModelForEdit(sender, model, false);
//    }
//
//    protected void setTransferedModelForEdit(Intent sender, ParseModelAbstract model, boolean newModel) {
//        this.setTransferedModel(sender, model);
//        sender.putExtra(IntentCache.newModel, newModel);
//    }
}
