package com.ieatta.android.modules.common.edit;

import com.ieatta.android.modules.common.edit.enums.IEAEditKey;

/**
 * Created by djzhang on 12/1/15.
 */
public class GPSCellModel extends EditBaseCellModel{

    private final String gpsValue;

    public GPSCellModel(IEAEditKey editKey, String gpsValue) {
        super(editKey);
        this.gpsValue = gpsValue;
    }
}


