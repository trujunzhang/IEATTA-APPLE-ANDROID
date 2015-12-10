package com.ieatta.android.modules;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.android.cache.IntentCache;
import com.ieatta.android.modules.adapter.NSIndexPath;
import com.ieatta.android.modules.adapter.RecyclerItemClickListener;
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
import com.ieatta.com.parse.ParseModelAbstract;

/**
 * Created by djzhang on 12/1/15.
 */
public class IEAAppSegureTableViewController extends IEAAppTableViewController implements RecyclerItemClickListener {
    private IEAAppSegureTableViewController self = this;

    public void performSegueWithIdentifier(MainSegueIdentifier identifier, IEAAppTableViewController sender) {
        Intent intent = new Intent(EnvironmentUtils.sharedInstance.getGlobalContext(), identifier.getActivity());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        self.prepareForSegue(identifier, intent);
        self.startActivity(intent);
    }

    public void prepareForSegue(MainSegueIdentifier identifier, Intent sender) {
        switch (identifier) {
            // Four menus in the near restaurant page.
            case editRestaurantSegueIdentifier:
                self.segueForEditRestaurantViewController(null, sender);
                break;
            case searchRestaurantSegueIdentifier:
                break;
            case managerPeopleSegueIdentifier:
                break;
            case readReviewsSegueIdentifier:
                break;
            // Four detail pages.
            case detailRestaurantSegueIdentifier:
                self.segueForRestaurantDetailViewController(null, sender);
                break;
            case detailEventSegueIdentifier:
                self.segueForEventDetailViewController(null, sender);
                break;
            case detailOrderedRecipesSegueIdentifier:
                self.segueForOrderedRecipesViewController(null, sender);
                break;
            case detailRecipeSegueIdentifier:
                self.segueForRecipeDetailViewController(null, sender);
                break;

            // Show all posted reviews for restaurant,recipe.
            case detailSeeReviewSegueIdentifier:
                self.segueForSeeReviewsInDetailViewController(null, sender);
                break;
            // Show detail review from review list.
            case detailReviewSegueIdentifier:
                self.segueForReviewDetailViewController(null, sender);
                break;
            // Four new/edit model pages.(the following three, and restaurant)
            case editEventSegueIdentifier:
                self.segueForEditEventViewController(null, sender);
                break;
            case editPeopleSegueIdentifier:
                self.segueForEditPeopleViewController(null, sender);
                break;
            case editRecipeSegueIdentifier:
                self.segueForEditRecipeViewController(null, sender);
                break;
            // Choice Person in the event page.
            case choicePeopleSegueIdentifier:
                self.segueForChoicePeopleViewController(null, sender);
                break;
        }
    }

    protected void segueForEditRestaurantViewController(IEAEditRestaurantViewController destination, Intent sender) {
    }

    protected void segueForRestaurantDetailViewController(IEARestaurantDetailViewController destination, Intent sender) {
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
        self.whenSelectedEvent(model, indexPath);
        if (isLongClick) {
            Toast.makeText(self, "#" + position + " - " + " (Long click)", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(self, "#" + position + " - " + indexPath.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    protected ParseModelAbstract getTransferedModel() {
        String intentUUID = self.getIntent().getExtras().getString(IntentCache.intentUUID);
        ParseModelAbstract transferedModel = IntentCache.sharedInstance.getIntentModel(intentUUID);
        return transferedModel;
    }

    protected void setTransferedModel(Intent sender, ParseModelAbstract model) {
        IntentCache.sharedInstance.setIntentModel(model);
        sender.putExtra(IntentCache.intentUUID, model.intentUUID);
    }

    protected void setTransferedModelForEdit(Intent sender, ParseModelAbstract model) {
        self.setTransferedModelForEdit(sender,model,false);
    }
    protected void setTransferedModelForEdit(Intent sender, ParseModelAbstract model, boolean newModel) {
        self.setTransferedModel(sender, model);
        sender.putExtra(IntentCache.newModel, true);
    }
}
