package com.ieatta.android.modules.common.edit;

import com.ieatta.android.modules.view.IEAChoicePeopleViewController;

public class SectionChoicePeopleCellModel extends EditBaseCellModel {
    IEAChoicePeopleViewController viewController;


    public SectionChoicePeopleCellModel(IEAEditKey editKey, IEAChoicePeopleViewController viewController) {
        super(editKey);
        this.viewController = viewController;
    }

}
