package com.ieatta.android.modules.view.edit.model;

/**
 * Created by djzhang on 12/4/15.
 */
class IEAEditRecipeManager  extends IEAEditAbstractManager{
    override func getRowsInSection(model:ParseModelAbstract,viewController: IEAEditBaseViewController) -> [AnyObject]{
        let _model:Recipe = model as! Recipe

        var costString = "\(_model.cost)"
        if(costString == "0.0"){
            costString = ""
        }

        return [
        [
        EditCellModel(editKey: IEAEditKey.recipe_name, editValue: _model.displayName, editPlaceHolder: L10n.RecipeSName.string),
        EditCellModel(editKey: IEAEditKey.recipe_cost, editValue: costString, editPlaceHolder: L10n.RecipeSCost.string)
        ]
        ]
    }
}
