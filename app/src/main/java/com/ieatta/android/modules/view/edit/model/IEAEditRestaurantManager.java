package com.ieatta.android.modules.view.edit.model;

/**
 * Created by djzhang on 12/4/15.
 */

class IEAEditRestaurantManager  extends IEAEditAbstractManager{
    override func getRowsInSection(model:ParseModelAbstract,viewController: IEAEditBaseViewController) -> [AnyObject]{
        let _model:Restaurant = model as! Restaurant
        return [
        [
        EditCellModel(editKey: IEAEditKey.rest_name, editValue: _model.displayName, editPlaceHolder: L10n.RestaurantSName.string),
        ]
        ]
    }

    override func convertToEditModel(from rowModels:[AnyObject], to model:ParseModelAbstract) -> ParseModelAbstract{
        let restaurant = (model as! Restaurant)
        if let _ = restaurant.location{
            // Exist,Not update. **** Important ****
            assert(restaurant.location != nil, "Here already has location!")
        }else{
            restaurant.location = LocationObserver.sharedInstance.getCurrentPFGeoPoint()
        }
        return super.convertToEditModel(from: rowModels, to: model)
    }
}
