package com.ieatta.com.parse.engine.realm.models;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by djzhang on 12/20/15.
 */
public class DBPeopleInEvent extends RealmObject {


    public String UUID = "";
    public Date objectCreatedDate = new Date();

    public String userRef = "";
    public String eventRef = "";

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public Date getObjectCreatedDate() {
        return objectCreatedDate;
    }

    public void setObjectCreatedDate(Date objectCreatedDate) {
        this.objectCreatedDate = objectCreatedDate;
    }

    public String getUserRef() {
        return userRef;
    }

    public void setUserRef(String userRef) {
        this.userRef = userRef;
    }

    public String getEventRef() {
        return eventRef;
    }

    public void setEventRef(String eventRef) {
        this.eventRef = eventRef;
    }
}
