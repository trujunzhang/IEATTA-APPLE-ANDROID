package com.ieatta.android.extensions.storage;

import android.inputmethodservice.Keyboard;

/**
 * Created by djzhang on 12/2/15.
 */
public class RowModel {
    private RowModel self = this;

    public int layoutResId;
    public Class cellClass;
    public Object model;


    public RowModel(HeaderModel headerModel) {
        self.model = headerModel.item;
        self.cellClass = headerModel.cellClass;
        self.layoutResId = headerModel.layoutResId;
    }

    public RowModel(Object model, Class cellClass, int layoutResId) {
        this.layoutResId = layoutResId;
        this.cellClass = cellClass;
        this.model = model;
    }

    public RowModel(FooterModel footerModel) {
        self.model = footerModel.item;
        self.cellClass = footerModel.cellClass;
        self.layoutResId = footerModel.layoutResId;
    }
}
