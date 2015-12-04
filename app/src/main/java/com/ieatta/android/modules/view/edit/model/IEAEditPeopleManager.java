package com.ieatta.android.modules.view.edit.model;

import com.ieatta.android.modules.common.edit.IEAEditKey;
import com.ieatta.android.modules.view.edit.IEAEditBaseViewController;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Team;

/**
 * Created by djzhang on 12/4/15.
 */

class IEAEditPeopleManager extends IEAEditAbstractManager {
    @Override
    public Object[] getRowsInSection(ParseModelAbstract model, IEAEditBaseViewController viewController) {
        Team _model = (Team) model;

//        return [
//        [
//        EditCellModel(editKey: IEAEditKey.person_name, editValue: _model.displayName, editPlaceHolder: L10n.DisplayName.string),
//        EditCellModel(editKey: IEAEditKey.person_address, editValue: _model.address, editPlaceHolder: L10n.Address.string),
//        EditCellModel(editKey: IEAEditKey.person_email, editValue: _model.email, editPlaceHolder: L10n.Email.string)
//        ]
//        ]
        return new Object[0];
    }

}