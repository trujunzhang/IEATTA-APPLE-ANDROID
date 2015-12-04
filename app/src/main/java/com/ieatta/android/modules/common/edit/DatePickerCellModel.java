package com.ieatta.android.modules.common.edit;

import com.ieatta.android.modules.common.edit.enums.IEAEditKey;

import java.util.Date;

/**
 * Created by djzhang on 12/1/15.
 */
public class DatePickerCellModel extends EditBaseCellModel{
    int dateTitleResId;
    Date date = new Date();

    public DatePickerCellModel(IEAEditKey editKey,Date date, int dateTitleResId) {
        super(editKey);

        this.date = date;
        this.dateTitleResId = dateTitleResId;
    }
}


