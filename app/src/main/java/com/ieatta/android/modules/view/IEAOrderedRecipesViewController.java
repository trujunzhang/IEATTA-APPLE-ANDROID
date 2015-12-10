package com.ieatta.android.modules.view;

import android.content.Intent;
import android.os.Bundle;
import android.virtualbreak.com.manualdatabase.ActivityModelDebug;

import com.ieatta.android.R;
import com.ieatta.android.modules.IEASplitDetailViewController;
import com.ieatta.android.modules.adapter.NSIndexPath;
import com.ieatta.android.modules.cells.IEAOrderedPeopleCell;
import com.ieatta.android.modules.cells.IEAOrderedRecipeCell;
import com.ieatta.android.modules.cells.model.IEAOrderedPeople;
import com.ieatta.android.modules.common.MainSegueIdentifier;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.view.edit.IEAEditRecipeViewController;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Recipe;
import com.ieatta.com.parse.models.Team;

import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 12/1/15.
 */
enum OrderedRecipesSection {
    sectionOrderedPeople, //= 0
    sectionRecipes,       //= 1
}

public class IEAOrderedRecipesViewController extends IEASplitDetailViewController {
    private IEAOrderedRecipesViewController self = this;

    @Override
    public boolean shouldShowHUD() {
        return true;
    }

    // Transferd Model from previous page.
    private Team orderedPeople;
    // Selected model from tableview.
    private Recipe selectedModel;

    private List<ParseModelAbstract/*Recipe*/> fetchedOrderedRecipes = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        self.orderedPeople = (Team) self.getTransferedModel();

        // Do any additional setup after loading the view.
//        assert(self.orderedPeople?.belongToModel != nil, "Must setup Event's instance.")
//        assert(self.orderedPeople != nil, "Must setup OrderedPeople instance.")

//        NSNotificationCenter.defaultCenter().addObserver(self, selector: "TakenPhotoWasChanged:", name: PAModelTakenPhotoNotification, object: nil)
//        NSNotificationCenter.defaultCenter().addObserver(self, selector: "RecipeWasCreated:", name: PARecipeCreatedNotification, object: nil)

        self.queryOrderedRecipesList();
    }

    private void queryOrderedRecipesList() {


        Recipe.queryRecipes(self.orderedPeople, self.orderedPeople.belongToModel)
                .onSuccessTask(new Continuation<List<ParseModelAbstract>, Task<Boolean>>() {
                    @Override
                    public Task<Boolean> then(Task<List<ParseModelAbstract>> task) throws Exception {
                        fetchedOrderedRecipes = task.getResult();

                        // Next, fetch related photos
                        return self.getPhotosForModelsTask(task);
                    }
                }).onSuccess(new Continuation<Boolean, Void>() {
            @Override
            public Void then(Task<Boolean> task) throws Exception {
                // Finally, hide hud.
                self.hideHUD();

                self.setRegisterCellClass(IEAOrderedPeopleCell.getType(), OrderedRecipesSection.sectionOrderedPeople.ordinal());
                self.setRegisterCellClassWhenSelected(IEAOrderedRecipeCell.getType(), OrderedRecipesSection.sectionRecipes.ordinal());

                self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Ordered_People), OrderedRecipesSection.sectionOrderedPeople.ordinal());
                self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Ordered_Recipes), OrderedRecipesSection.sectionRecipes.ordinal());

                self.setSectionItems(IEAOrderedPeople.convertToOrderedPeople(self.orderedPeople, (self.orderedPeople.belongToModel), self), OrderedRecipesSection.sectionOrderedPeople.ordinal());

                self.setSectionItems(fetchedOrderedRecipes, OrderedRecipesSection.sectionRecipes.ordinal());

                return null;
            }
        }).continueWith(new Continuation<Void, Object>() {
            @Override
            public Object then(Task<Void> task) throws Exception {
                if (task.isFaulted() == true) {
                }
                return null;
            }
        });
    }

    @Override
    public void whenSelectedEvent(Object model, NSIndexPath indexPath) {
        Recipe recipe = (Recipe) model;

        recipe.belongToModel = self.orderedPeople;
        self.selectedModel = recipe;
        self.performSegueWithIdentifier(MainSegueIdentifier.detailRecipeSegueIdentifier, self);
    }


    @Override
    public void segueForRecipeDetailViewController(IEARecipeDetailViewController destination, Intent sender) {
        /// Show detailed recipe
        self.setTransferedModel(sender, self.selectedModel);
    }

    @Override
    public void segueForEditRecipeViewController(IEAEditRecipeViewController destination, Intent sender) {
        // Add recipe
        self.setTransferedModelForEdit(sender, new Recipe(self.orderedPeople), true);
    }

    // MARK: Cell's Events
    public void performSegueForAddingRecipe() {
        self.performSegueWithIdentifier(MainSegueIdentifier.editRecipeSegueIdentifier, self);
    }
}
