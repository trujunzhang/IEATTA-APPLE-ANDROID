package com.ieatta.android.modules.cells.model;

import org.ieatta.activity.event.IEAEventActivity;
import org.ieatta.database.models.DBEvent;

public class IEAEventHeader {

    public DBEvent model;

    public IEAEventHeader(DBEvent model) {
        this.model = model;
    }
}
