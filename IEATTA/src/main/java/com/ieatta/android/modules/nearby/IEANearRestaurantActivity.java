package com.ieatta.android.modules.nearby;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.ieatta.android.R;
import com.ieatta.android.modules.adapter.NSIndexPath;
import com.ieatta.android.modules.cells.model.IEANearRestaurantMore;
import com.ieatta.android.modules.common.MainSegueIdentifier;
import com.ieatta.android.modules.tools.CollectionUtils;
import com.ieatta.android.notification.NSNotification;
import com.ieatta.android.observers.LocationObserver;
import com.parse.ParseGeoPoint;

import org.ieatta.tasks.NearRestaurantsTask;

import java.util.List;

public class IEANearRestaurantActivity extends LocationObserveActivity {
    private List fetchedRestaurants;

    private NearRestaurantsTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        task = new NearRestaurantsTask(this, this.recyclerView);
        task.makeActivePage();
    }

    /// Add rows for section "More".
    private void configModelsInMoreSection() {
//        this.setRegisterCellClassWhenSelected(NearRestaurantMoreCell.getType(), NearRestaurantSection.sectionMoreItems.ordinal());
//
//        this.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.More), NearRestaurantSection.sectionMoreItems.ordinal());
//
//        this.setSectionItems(new LinkedList(getNearRestaurantMoresItems()), NearRestaurantSection.sectionMoreItems.ordinal());
    }

//    @Override
//    public void whenSelectedEvent(Object model, NSIndexPath indexPath) {
////        if (indexPath.section == NearRestaurantSection.sectionMoreItems.ordinal()) {
////            this.whenSelectedCellTaped(((IEANearRestaurantMore) model).identifier);
////        } else {
//////            this.selectedModel = (Restaurant) model;
//////            this.whenSelectedCellTaped(MainSegueIdentifier.detailRestaurantSegueIdentifier);
////        }
//    }
//
//    // MARK: NSNotificationCenter notification handlers
//    @Override
//    protected void RestaurantWasCreated(NSNotification note) {
//        queryNearRestaurant(LocationObserver.sharedInstance.getCurrentPFGeoPoint());
//    }
//
//    @Override
//    protected void LocationDidChange(NSNotification note) {
//        queryNearRestaurant(LocationObserver.sharedInstance.getCurrentPFGeoPoint());
//    }

    @Override
    protected void notifyLocationChanged(Location location) {
        task.executeUpdateTask();
    }

    @Override
    protected boolean shouldLeftBarButtonItem() {
        return false;
    }
}
