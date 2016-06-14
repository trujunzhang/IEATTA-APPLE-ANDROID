package com.ieatta.android.modules.common.edit;

import com.ieatta.android.modules.common.edit.enums.IEAEditKey;

public class EditBaseCellModel {
    public IEAEditKey editKey = IEAEditKey.Unknow;
    public String editValue;

    public EditBaseCellModel(IEAEditKey editKey) {
        this.editKey = editKey;
    }

    public EditBaseCellModel(IEAEditKey editKey, String editValue) {
        this.editKey = editKey;
        this.editValue = editValue;
    }
}


