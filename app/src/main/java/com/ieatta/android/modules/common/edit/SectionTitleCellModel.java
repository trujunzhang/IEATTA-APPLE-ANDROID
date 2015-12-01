package com.ieatta.android.modules.common.edit;

public class SectionTitleCellModel extends EditBaseCellModel {
    int titleResId;

    public SectionTitleCellModel(IEAEditKey editKey, int titleResId) {
        super(editKey);
        this.titleResId = titleResId;
    }

}
