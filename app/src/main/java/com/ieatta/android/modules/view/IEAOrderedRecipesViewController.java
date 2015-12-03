package com.ieatta.android.modules.view;

import android.os.Bundle;
import android.virtualbreak.com.manualdatabase.ActivityModelDebug;

import com.ieatta.android.modules.IEASplitDetailViewController;
import com.ieatta.android.modules.common.edit.IEAEditKey;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Recipe;
import com.ieatta.com.parse.models.Team;
import com.nostra13.universalimageloader.utils.L;

import java.util.Collection;
import java.util.LinkedList;

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

    private LinkedList<ParseModelAbstract/*Recipe*/> fetchedOrderedRecipes = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO djzhang(test)
        self.orderedPeople = ActivityModelDebug.getOrderedPeople();
        self.orderedPeople.belongToModel = ActivityModelDebug.getEventForEventDetail();

        // Do any additional setup after loading the view.
//        assert(self.orderedPeople?.belongToModel != nil, "Must setup Event's instance.")
//        assert(self.orderedPeople != nil, "Must setup OrderedPeople instance.")

//        NSNotificationCenter.defaultCenter().addObserver(self, selector: "TakenPhotoWasChanged:", name: PAModelTakenPhotoNotification, object: nil)
//        NSNotificationCenter.defaultCenter().addObserver(self, selector: "RecipeWasCreated:", name: PARecipeCreatedNotification, object: nil)

        self.queryOrderedRecipesList();
    }

    private void queryOrderedRecipesList() {


        Recipe.queryRecipes(self.orderedPeople, self.orderedPeople.belongToModel)
                .onSuccessTask(new Continuation<LinkedList<ParseModelAbstract>, Task<Boolean>>  ()
        {
            @Override
            public Task<Boolean> then (Task < LinkedList < ParseModelAbstract >> task)throws Exception {
            fetchedOrderedRecipes = task.getResult();

            // Next, fetch related photos
            return self.getPhotosForModelsTask(task);
        }
        }).onSuccess(new Continuation<Boolean, Void>() {
            @Override
            public Void then(Task<Boolean> task) throws Exception {
                    // Finally, hide hud.
                    self.hideHUD();

//                    self.setRegisterCellClass(IEAOrderedPeopleCell);
//                    self.setRegisterCellClassWhenSelected(IEAOrderedRecipeCell.self);

//                    self.appendSectionTitleCell(SectionTitleCellModel(editKey: IEAEditKey.Section_Title, title: L10n.OrderedPeople.string), forSectionIndex: OrderedRecipesSection.sectionOrderedPeople.ordinal());
//                    self.appendSectionTitleCell(SectionTitleCellModel(editKey: IEAEditKey.Section_Title, title: L10n.OrderedRecipes.string), forSectionIndex: OrderedRecipesSection.sectionRecipes.ordinal());

//                    self.setSectionItems(IEAOrderedPeople.convertToOrderedPeople(self.orderedPeople!, forEvent: (self.orderedPeople?.belongToModel!)!,viewController:self), forSectionIndex: OrderedRecipesSection.sectionOrderedPeople.rawValue)

//                    self.setSectionItems(fetchedOrderedRecipes, forSectionIndex: OrderedRecipesSection.sectionRecipes.ordinal());

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
}
