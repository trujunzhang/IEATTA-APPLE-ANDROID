package com.ieatta.android.modules.view.edit.model;

import com.ieatta.android.R;
import com.tableview.model.EditBaseCellModel;
import com.ieatta.android.modules.common.edit.EditCellModel;
import com.ieatta.provide.IEAEditKey;
import com.ieatta.android.modules.view.edit.IEAEditBaseViewController;

import org.ieatta.database.models.DBTeam;

import io.realm.Realm;

public class IEAEditPeopleManager extends IEAEditAbstractManager {
    @Override
    public Object[] getRowsInSection(Realm model, IEAEditBaseViewController viewController) {
//        DBTeam _model = (DBTeam) model;
//
//
//        EditBaseCellModel[] section1 = {
//                new EditCellModel(IEAEditKey.person_name, _model.displayName, R.string.Display_Name),
//                new EditCellModel(IEAEditKey.person_address, _model.address, R.string.Address),
//                new EditCellModel(IEAEditKey.person_email, _model.email, R.string.Email)
//        };
//        Object[] sections = {section1};
//
//        return sections;

        return null;
    }

}