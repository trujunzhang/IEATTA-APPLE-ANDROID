package com.ieatta.com.parse.engine.realm.models;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by djzhang on 12/20/15.
 */
public class DBTeam extends RealmObject {

    public String UUID = "";
    public Date objectCreatedDate = new Date();
    public String displayName = "";


    public String email = "";
    public String address = "";

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
