package com.ieatta.android.modules.cells.model;

import org.ieatta.activity.event.IEAEventActivity;
import org.ieatta.database.models.DBEvent;

public class IEAEventHeader {

    public String restaurantName;
    public String eventName;
    public String eventUUID;

    public IEAEventHeader(String restaurantName, String eventName, String eventUUID) {
        this.restaurantName = restaurantName;
        this.eventName = eventName;
        this.eventUUID = eventUUID;
    }
}
