package org.ieatta.activity.nearby;


import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.ieatta.activity.restaurant.IEARestaurantDetailActivity;

import com.ieatta.android.IEAApplication;
import com.ieatta.android.R;
import com.ieatta.android.modules.cells.IEANearRestaurantsCell;
import com.ieatta.android.modules.cells.NearRestaurantMoreCell;
import com.ieatta.android.modules.cells.model.IEANearRestaurantMore;
import com.ieatta.android.modules.common.MainSegueIdentifier;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.provide.IEAEditKey;
import com.ieatta.android.modules.tools.CollectionUtils;
import com.tableview.adapter.NSIndexPath;

import org.ieatta.database.models.DBRestaurant;
import org.ieatta.database.query.LocalDatabaseQuery;
import org.ieatta.tasks.FragmentTask;

import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;
import io.realm.RealmResults;

public class NearRestaurantsTask extends FragmentTask {

    enum NearRestaurantSection {
        section_more_items,//= 0
        section_restaurants, //= 1
    }

    public RealmResults<DBRestaurant> restaurants;

    public NearRestaurantsTask(Activity activity, RecyclerView recyclerView) {
        super(activity, recyclerView);
    }

    @Override
    public void onItemClick(View view, NSIndexPath indexPath, Object model, int position, boolean isLongClick) {
        Intent intent = null;

        if (model instanceof IEANearRestaurantMore) {
            intent = new Intent(activity, ((IEANearRestaurantMore) model).identifier.getActivity());
        } else if (model instanceof DBRestaurant) {
            intent = new Intent(activity, IEARestaurantDetailActivity.class);
            intent.putExtra(EXTRA_ID, ((DBRestaurant)model).getUUID());
        }

        this.nextPage(intent);
    }


    public Task<Void> executeTask() {
        return LocalDatabaseQuery.queryNearRestaurants(IEAApplication.getInstance().lastLocation, realmList).onSuccess(new Continuation<RealmResults<DBRestaurant>, Void>() {
            @Override
            public Void then(Task<RealmResults<DBRestaurant>> task) throws Exception {
                NearRestaurantsTask.this.restaurants = task.getResult();
                return null;
            }
        });
    }

    public Task<Void> executeUpdateTask() {
        return LocalDatabaseQuery.queryNearRestaurants(IEAApplication.getInstance().lastLocation, realmList).onSuccess(new Continuation<RealmResults<DBRestaurant>, Void>() {
            @Override
            public Void then(Task<RealmResults<DBRestaurant>> task) throws Exception {
                NearRestaurantsTask.this.restaurants = task.getResult();
                NearRestaurantsTask.this.manager.setSectionItems(NearRestaurantsTask.this.restaurants, NearRestaurantSection.section_restaurants.ordinal());
                NearRestaurantsTask.this.manager.reloadTableView();
                return null;
            }
        }, Task.UI_THREAD_EXECUTOR);
    }

    @Override
    public void prepareUI() {
        super.prepareUI();

        //set section header title
        this.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.More), NearRestaurantSection.section_more_items.ordinal());
        this.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Nearby_Restaurants), NearRestaurantSection.section_restaurants.ordinal());

        this.manager.setRegisterCellClassWhenSelected(NearRestaurantMoreCell.getType(), NearRestaurantSection.section_more_items.ordinal());
        this.manager.setSectionItems(new LinkedList(getNearRestaurantMoresItems()), NearRestaurantSection.section_more_items.ordinal());
    }

    @NonNull
    private List getNearRestaurantMoresItems() {
        // "Manager Restaurant"
        IEANearRestaurantMore managerRestaurantItem = new IEANearRestaurantMore(R.drawable.restaurants_icon, R.string.Add_a_Restaurant, MainSegueIdentifier.editRestaurantSegueIdentifier);
        // "Search Restaurant"
        IEANearRestaurantMore searchRestaurant = new IEANearRestaurantMore(R.drawable.nav_search, R.string.Search_Restaurants, MainSegueIdentifier.searchRestaurantSegueIdentifier);
        // "Manager People"
        IEANearRestaurantMore managerPeople = new IEANearRestaurantMore(R.drawable.nav_add_friends, R.string.Manage_Friends, MainSegueIdentifier.managerPeopleSegueIdentifier);
        // "Read Reviews"
        IEANearRestaurantMore readReviews = new IEANearRestaurantMore(R.drawable.nav_passport_reviews, R.string.Read_Reviews, MainSegueIdentifier.readReviewsSegueIdentifier);

        IEANearRestaurantMore[] mores = {managerRestaurantItem, searchRestaurant, managerPeople, readReviews};
        return CollectionUtils.createList(mores);
    }

    @Override
    public void postUI() {
        this.manager.setRegisterCellClassWhenSelected(IEANearRestaurantsCell.getType(), NearRestaurantSection.section_restaurants.ordinal());
        this.manager.setAndRegisterSectionItems(IEANearRestaurantsCell.getType(), this.restaurants, NearRestaurantSection.section_restaurants.ordinal());
    }

}
