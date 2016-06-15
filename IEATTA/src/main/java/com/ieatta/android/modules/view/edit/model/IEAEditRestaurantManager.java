package com.ieatta.android.modules.view.edit.model;

import com.ieatta.android.R;
import com.ieatta.android.modules.common.edit.EditCellModel;
import com.ieatta.provide.IEAEditKey;
import com.ieatta.android.modules.view.edit.IEAEditBaseViewController;
import com.ieatta.android.observers.LocationObserver;

import org.ieatta.database.models.DBRestaurant;

import io.realm.Realm;

public class IEAEditRestaurantManager extends IEAEditAbstractManager {
    @Override
    public Realm convertToEditModel(Object[] rowModels, Realm model) {
//        DBRestaurant restaurant = (DBRestaurant) model;
//        if (restaurant.location != null) {
//            // Exist,Not update. **** Important ****
////            assert(restaurant.location != nil, "Here already has location!")
//        } else {
//            restaurant.location = LocationObserver.sharedInstance.getCurrentPFGeoPoint();
//        }
//        return super.convertToEditModel(rowModels, model);
        return null;
    }

    @Override
    public Object[] getRowsInSection(Realm model, IEAEditBaseViewController viewController) {
//        DBRestaurant _model = (DBRestaurant) model;
//
//        EditCellModel[] section1 = {
//                new EditCellModel(IEAEditKey.rest_name, _model.displayName, R.string.Restaurant_Name_info),
//        };
//        Object[] sections = {section1};
//
//        return sections;

        return null;
    }
}
