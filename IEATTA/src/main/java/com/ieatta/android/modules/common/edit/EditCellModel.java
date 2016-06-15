package com.ieatta.android.modules.common.edit;

import com.ieatta.provide.IEAEditKey;
import com.tableview.model.EditBaseCellModel;


public class EditCellModel extends EditBaseCellModel {
    public int editPlaceHolderResId;


    public EditCellModel(IEAEditKey editKey, String editValue, int editPlaceHolderResId) {
        super(editKey, editValue);

        this.editPlaceHolderResId = editPlaceHolderResId;
    }
}


