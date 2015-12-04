package com.ieatta.android.modules.common.edit;

/**
 * Created by djzhang on 12/1/15.
 */
public class EditCellModel extends EditBaseCellModel{
    int editPlaceHolderResId;


    public EditCellModel(IEAEditKey editKey, String editValue, int editPlaceHolderResId) {
        super(editKey,editValue);

        this.editPlaceHolderResId = editPlaceHolderResId;
    }
}


