package com.tableview.model;

import com.ieatta.provide.IEAEditKey;

public class EditCellModel extends EditBaseCellModel {
    public int editPlaceHolderResId;


    public EditCellModel(IEAEditKey editKey, String editValue, int editPlaceHolderResId) {
        super(editKey, editValue);

        this.editPlaceHolderResId = editPlaceHolderResId;
    }
}


