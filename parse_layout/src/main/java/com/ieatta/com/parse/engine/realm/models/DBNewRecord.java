package com.ieatta.com.parse.engine.realm.models;

import com.ieatta.com.parse.models.enums.PQueryModelType;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by djzhang on 12/20/15.
 */
public class DBNewRecord extends RealmObject {

    public String UUID = "";
    public Date objectCreatedDate = new Date();

    public int modelType = PQueryModelType.unkown.ordinal();
    public String modelPoint = "";
    public Date modelCreatedDate;

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

    public int getModelType() {
        return modelType;
    }

    public void setModelType(int modelType) {
        this.modelType = modelType;
    }

    public String getModelPoint() {
        return modelPoint;
    }

    public void setModelPoint(String modelPoint) {
        this.modelPoint = modelPoint;
    }

    public Date getModelCreatedDate() {
        return modelCreatedDate;
    }

    public void setModelCreatedDate(Date modelCreatedDate) {
        this.modelCreatedDate = modelCreatedDate;
    }
}
