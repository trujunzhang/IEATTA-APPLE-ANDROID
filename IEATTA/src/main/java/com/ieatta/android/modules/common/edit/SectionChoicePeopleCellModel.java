package com.ieatta.android.modules.common.edit;

import com.ieatta.provide.IEAEditKey;
import com.ieatta.android.modules.view.IEAChoicePeopleViewController;
import com.tableview.model.EditBaseCellModel;

public class SectionChoicePeopleCellModel extends EditBaseCellModel {
    IEAChoicePeopleViewController viewController;

    public SectionChoicePeopleCellModel(IEAEditKey editKey, IEAChoicePeopleViewController viewController) {
        super(editKey);
        this.viewController = viewController;
    }

}
