package com.ieatta.android.modules.view.edit.model;

import com.ieatta.android.R;
import com.ieatta.android.modules.common.edit.EditCellModel;
import com.ieatta.android.modules.common.edit.IEAEditKey;
import com.ieatta.android.modules.view.edit.IEAEditBaseViewController;
import com.ieatta.android.observers.LocationObserver;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Restaurant;

/**
 * Created by djzhang on 12/4/15.
 */

class IEAEditRestaurantManager  extends IEAEditAbstractManager{
    @Override
    public ParseModelAbstract convertToEditModel(Object[] rowModels, ParseModelAbstract model) {
        Restaurant restaurant = (Restaurant)model;
        if(restaurant.location != null){
            // Exist,Not update. **** Important ****
//            assert(restaurant.location != nil, "Here already has location!")
        }else{
            restaurant.location = LocationObserver.sharedInstance.getCurrentPFGeoPoint();
        }
        return super.convertToEditModel(rowModels, model);
    }

    @Override
    public Object[] getRowsInSection(ParseModelAbstract model, IEAEditBaseViewController viewController) {
        Restaurant _model = (Restaurant) model;
//        return {
//                {
//new EditCellModel( IEAEditKey.rest_name,  _model.displayName, R.string.Restaurant_Name_info),
//                }
//        };

        return new Object[0];
    }
}
