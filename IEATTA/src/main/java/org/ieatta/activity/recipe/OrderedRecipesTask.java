package org.ieatta.activity.recipe;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ieatta.android.R;
import com.ieatta.android.modules.cells.IEAOrderedRecipeCell;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.provide.IEAEditKey;
import com.tableview.adapter.NSIndexPath;

import org.ieatta.database.models.DBEvent;
import org.ieatta.database.models.DBPhoto;
import org.ieatta.database.models.DBRecipe;
import org.ieatta.database.models.DBRestaurant;
import org.ieatta.database.models.DBTeam;
import org.ieatta.database.provide.PhotoUsedType;
import org.ieatta.database.query.LocalDatabaseQuery;
import org.ieatta.database.realm.RealmModelReader;
import org.ieatta.tasks.FragmentTask;


import bolts.Continuation;
import bolts.Task;
import io.realm.RealmResults;

public class OrderedRecipesTask extends FragmentTask {
    public OrderedRecipesTask(Activity activity, RecyclerView recyclerView) {
        super(activity, recyclerView);
    }

    private DBRestaurant restaurant;
    private DBEvent event;
    public DBTeam team;
    public RealmResults<DBRecipe> recipes;

    @Override
    public void onItemClick(View view, NSIndexPath indexPath, Object model, int position, boolean isLongClick) {
        if (model instanceof DBRecipe) {
            DBRecipe item = (DBRecipe) model;
        }
    }

    enum OrderedRecipesSection {
        section_ordered_people, //= 0
        section_recipes,       //= 1
    }


    /**
     * Execute Task for ordered recipes.
     *
     * @return
     */
    public Task<Void> executeTask() {
        Bundle extras = this.activity.getIntent().getExtras();
        final String _eventUUID = extras.getString(EXTRA_ID);
        final String _teamUUID = extras.getString(TEAM_ID);

        return new RealmModelReader<DBEvent>(DBEvent.class).getFirstObject(LocalDatabaseQuery.get(_eventUUID), false, realmList).onSuccessTask(new Continuation<DBEvent, Task<DBRestaurant>>() {
            @Override
            public Task<DBRestaurant> then(Task<DBEvent> task) throws Exception {
                DBEvent event = task.getResult();
                OrderedRecipesTask.this.event = event;
                OrderedRecipesTask.this.mRestaurantUUID = event.getRestaurantRef();
                return new RealmModelReader<DBRestaurant>(DBRestaurant.class).getFirstObject(LocalDatabaseQuery.get(OrderedRecipesTask.this.mRestaurantUUID), false, realmList);
            }
        }).onSuccessTask(new Continuation<DBRestaurant, Task<RealmResults<DBPhoto>>>() {
            @Override
            public Task<RealmResults<DBPhoto>> then(Task<DBRestaurant> task) throws Exception {
                OrderedRecipesTask.this.restaurant = task.getResult();
                return LocalDatabaseQuery.queryPhotosByModel(OrderedRecipesTask.this.mRestaurantUUID, PhotoUsedType.PU_Restaurant.getType(), realmList);
            }
        }).onSuccessTask(new Continuation<RealmResults<DBPhoto>, Task<RealmResults<DBRecipe>>>() {
            @Override
            public Task<RealmResults<DBRecipe>> then(Task<RealmResults<DBPhoto>> task) throws Exception {
//                OrderedRecipesTask.this.leadImageCollection = DBConvert.toLeadImageCollection(task.getResult());
                return new RealmModelReader<DBRecipe>(DBRecipe.class).fetchResults(LocalDatabaseQuery.getForRecipes(_teamUUID, _eventUUID), false, realmList);
            }
        }).onSuccess(new Continuation<RealmResults<DBRecipe>, Void>() {
            @Override
            public Void then(Task<RealmResults<DBRecipe>> task) throws Exception {
                OrderedRecipesTask.this.recipes = task.getResult();
                return null;
            }
        });
    }


    @Override
    public void prepareUI() {
        super.prepareUI();

        this.manager.setRegisterCellClass(IEAOrderedRecipeCell.getType(), OrderedRecipesSection.section_recipes.ordinal());

        this.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Ordered_Recipes), OrderedRecipesSection.section_recipes.ordinal());
    }

    @Override
    public void postUI() {
        this.manager.setSectionItems(this.recipes, OrderedRecipesSection.section_recipes.ordinal());
    }

}
