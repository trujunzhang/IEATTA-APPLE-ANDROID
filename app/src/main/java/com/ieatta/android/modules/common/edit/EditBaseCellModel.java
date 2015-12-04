package com.ieatta.android.modules.common.edit;

/**
 * Created by djzhang on 12/1/15.
 */
public class EditBaseCellModel {
    public IEAEditKey editKey  = IEAEditKey.Unknow;
    public int editValueResId;

    public EditBaseCellModel(IEAEditKey editKey) {
        this.editKey = editKey;
    }

    public EditBaseCellModel(IEAEditKey editKey, int editValueResId) {
        this.editKey = editKey;
        this.editValueResId = editValueResId;
    }
}


