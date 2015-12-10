package com.ieatta.android.extensions.storage;

import android.inputmethodservice.Keyboard;

import com.ieatta.android.extensions.storage.models.CellType;

/**
 * Created by djzhang on 12/2/15.
 */
public class RowModel {
    private RowModel self = this;

    public CellType cellType;
    public Object model;


    public RowModel(HeaderModel headerModel) {
        self.model = headerModel.item;
        self.cellType = headerModel.cellType;
    }

    public RowModel(Object model, CellType type) {
        this.model = model;
        this.cellType = type;
    }

    public RowModel(FooterModel footerModel) {
        self.model = footerModel.item;
        self.cellType = footerModel.cellType;
    }
}
