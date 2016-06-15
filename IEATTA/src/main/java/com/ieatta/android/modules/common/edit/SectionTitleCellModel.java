package com.ieatta.android.modules.common.edit;

import com.ieatta.provide.IEAEditKey;
import com.tableview.model.EditBaseCellModel;

public class SectionTitleCellModel extends EditBaseCellModel {
    public int titleResId;

    public SectionTitleCellModel(IEAEditKey editKey, int titleResId) {
        super(editKey);
        this.titleResId = titleResId;
    }

}
