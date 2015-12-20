package com.ieatta.com.parse.engine.realm.models;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by djzhang on 12/20/15.
 */
public class DBRecipe extends RealmObject {

    private String UUID = "";
    private Date objectCreatedDate = new Date();
    private String displayName = "";

    private float cost = 0.0f;
    private int likeCount = 0;

    private String eventRef = "";
    private String orderedPeopleRef = "";

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

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getEventRef() {
        return eventRef;
    }

    public void setEventRef(String eventRef) {
        this.eventRef = eventRef;
    }

    public String getOrderedPeopleRef() {
        return orderedPeopleRef;
    }

    public void setOrderedPeopleRef(String orderedPeopleRef) {
        this.orderedPeopleRef = orderedPeopleRef;
    }
}
