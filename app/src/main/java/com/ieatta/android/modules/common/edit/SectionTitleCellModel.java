package com.ieatta.android.modules.common.edit;

public class SectionTitleCellModel extends EditBaseCellModel {
    String title = "";


    public SectionTitleCellModel(IEAEditKey editKey, String title) {
        super(editKey);
        this.title = title;
    }

}
