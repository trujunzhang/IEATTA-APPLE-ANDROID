package org.ieatta.tasks;


import android.app.Activity;
import android.location.Location;
import android.support.annotation.VisibleForTesting;
import android.view.View;

import com.ieatta.android.IEAApplication;
import com.ieatta.android.modules.common.MainSegueIdentifier;
import com.tableview.adapter.NSIndexPath;

import org.ieatta.database.models.DBRestaurant;
import org.ieatta.database.query.LocalDatabaseQuery;

import bolts.Continuation;
import bolts.Task;
import io.realm.RealmResults;

public class NearRestaurantsTask extends FragmentTask {
    //
//    @Override
//    public boolean isMainPage() {
//        return true;
//    }
//
    public RealmResults<DBRestaurant> restaurants;

    @Override
    public void onItemClick(View view, NSIndexPath indexPath, Object model, int position, boolean isLongClick) {
        if (model instanceof DBRestaurant) {
            DBRestaurant item = (DBRestaurant) model;

//            ((PageActivity) NearRestaurantsTask.this.activity).loadPage(
//                    new HistoryEntry(MainSegueIdentifier.detailRestaurantSegueIdentifier, item.getUUID()));
        }
    }

    enum NearRestaurantSection {
        section_more_items,//= 0
        section_restaurants, //= 1
    }

    /**
     * Execute Task for nearby restaurants.
     *
     * @return
     */
    public Task<Void> executeTask() {

        return LocalDatabaseQuery.queryNearRestaurants(IEAApplication.getInstance().lastLocation, this.realmList).onSuccess(new Continuation<RealmResults<DBRestaurant>, Void>() {
            @Override
            public Void then(Task<RealmResults<DBRestaurant>> task) throws Exception {
                NearRestaurantsTask.this.restaurants = task.getResult();
                return null;
            }
        });
    }

//    @Override
//    public Task<Void> executeUpdateTask(UpdateEntry entry) {
//
//        return LocalDatabaseQuery.queryNearRestaurants(IEAApp.getInstance().lastLocation, this.realmList).onSuccess(new Continuation<RealmResults<DBRestaurant>, Void>() {
//            @Override
//            public Void then(Task<RealmResults<DBRestaurant>> task) throws Exception {
//                NearRestaurantsTask.this.restaurants = task.getResult();
//                NearRestaurantsTask.this. manager.setSectionItems(NearRestaurantsTask.this.restaurants, NearRestaurantSection.section_restaurants.ordinal());
//                NearRestaurantsTask.this.manager.reloadTableView();
//                return null;
//            }
//        }, Task.UI_THREAD_EXECUTOR);
//    }
//
//    @Override
//    public void prepareUI() {
//        super.prepareUI();
//
//        this.manager.setHeaderItem(new IEAHeaderViewModel(this.model.getActionbarHeight()), IEAHeaderView.getType());
//        this.manager.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Nearby_Restaurants), NearRestaurantSection.section_restaurants.ordinal());
//    }
//
//    @Override
//    public void postUI() {
//        this.manager.setAndRegisterSectionItems(IEANearRestaurantsCell.getType(), this.restaurants, NearRestaurantSection.section_restaurants.ordinal());
//    }

}
