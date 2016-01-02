package com.ieatta.android.extensions.storage.models;

import com.ieatta.android.modules.adapter.NSIndexPath;

/**
 * Created by djzhang on 12/2/15.
 */
public class RowModel {
    private RowModel self = this;

    public CellType cellType;
    public Object model;

    public NSIndexPath indexPath;

    public RowModel(HeaderModel headerModel) {
        self.model = headerModel.item;
        self.cellType = headerModel.cellType;
    }

    public RowModel(Object model, CellType type, NSIndexPath indexPath) {
        this.model = model;
        this.cellType = type;
        this.indexPath = indexPath;
    }

    public RowModel(FooterModel footerModel) {
        self.model = footerModel.item;
        self.cellType = footerModel.cellType;
    }
}
