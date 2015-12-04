package com.ieatta.android.modules.view.edit.model;

/**
 * Created by djzhang on 12/4/15.
 */

class IEAEditPeopleManager  extends IEAEditAbstractManager{
    override func getRowsInSection(model:ParseModelAbstract,viewController: IEAEditBaseViewController) -> [AnyObject]{
        let _model:Team = model as! Team
        return [
        [
        EditCellModel(editKey: IEAEditKey.person_name, editValue: _model.displayName, editPlaceHolder: L10n.DisplayName.string),
        EditCellModel(editKey: IEAEditKey.person_address, editValue: _model.address, editPlaceHolder: L10n.Address.string),
        EditCellModel(editKey: IEAEditKey.person_email, editValue: _model.email, editPlaceHolder: L10n.Email.string)
        ]
        ]
    }
}