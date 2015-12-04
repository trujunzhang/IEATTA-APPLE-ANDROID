package com.ieatta.android.modules.common.edit;

import com.ieatta.android.modules.common.edit.enums.IEAEditKey;

/**
 * Created by djzhang on 12/1/15.
 */
public class EditCellModel extends EditBaseCellModel{
    public int editPlaceHolderResId;


    public EditCellModel(IEAEditKey editKey, String editValue, int editPlaceHolderResId) {
        super(editKey,editValue);

        this.editPlaceHolderResId = editPlaceHolderResId;
    }
}


