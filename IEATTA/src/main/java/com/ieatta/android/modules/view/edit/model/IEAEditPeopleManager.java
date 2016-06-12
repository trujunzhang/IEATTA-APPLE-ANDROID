package com.ieatta.android.modules.view.edit.model;

import com.ieatta.android.R;
import com.ieatta.android.modules.common.edit.EditBaseCellModel;
import com.ieatta.android.modules.common.edit.EditCellModel;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.view.edit.IEAEditBaseViewController;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Team;

public class IEAEditPeopleManager extends IEAEditAbstractManager {
    @Override
    public Object[] getRowsInSection(ParseModelAbstract model, IEAEditBaseViewController viewController) {
        Team _model = (Team) model;


        EditBaseCellModel[] section1 = {
                new EditCellModel(IEAEditKey.person_name, _model.displayName, R.string.Display_Name),
                new EditCellModel(IEAEditKey.person_address, _model.address, R.string.Address),
                new EditCellModel(IEAEditKey.person_email, _model.email, R.string.Email)
        };
        Object[] sections = {section1};

        return sections;
    }

}