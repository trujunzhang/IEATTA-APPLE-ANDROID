package com.ieatta.android.modules.common.edit;

/**
 * Created by djzhang on 12/1/15.
 */
public class EditCellModel extends EditBaseCellModel{
    int editPlaceHolderResId;

    public EditCellModel(IEAEditKey editKey, int editValueResId, int editPlaceHolderResId) {
        super(editKey,editValueResId);

        this.editPlaceHolderResId = editPlaceHolderResId;
    }
}

