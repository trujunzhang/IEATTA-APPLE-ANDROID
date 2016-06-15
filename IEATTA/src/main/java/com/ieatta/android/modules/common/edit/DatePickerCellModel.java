package com.ieatta.android.modules.common.edit;

import com.ieatta.provide.IEAEditKey;
import com.tableview.model.EditBaseCellModel;

import java.util.Date;

public class DatePickerCellModel extends EditBaseCellModel {
    public int dateTitleResId;
    public Date date = new Date();

    public DatePickerCellModel(IEAEditKey editKey, Date date, int dateTitleResId) {
        super(editKey);

        this.date = date;
        this.dateTitleResId = dateTitleResId;
    }
}


