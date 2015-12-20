package com.ieatta.com.parse.engine.realm;

import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import bolts.Task;

/**
 * Created by djzhang on 12/20/15.
 */
public class DBQuery<T extends ParseObject> extends ParseQuery {
    public DBQuery(Class subclass) {
        super(subclass);
    }

    public DBQuery(String theClassName) {
        super(theClassName);
    }

    public static <T extends ParseObject> DBQuery<T> getDBQuery(String className) {
        return new DBQuery<>(className);
    }

    @Override
    public ParseQuery<T> fromLocalDatastore() {
        return this;
    }

    @Override
    public DBQuery<T> whereEqualTo(String key, Object value) {

        return this;
    }

    @Override
    public DBQuery<T> orderByDescending(String key) {
        return this;
    }

    @Override
    public DBQuery<T> orderByAscending(String key) {
        return this;
    }

//    @Override
//    public DBQuery<T> whereContainedIn(String key, Collection<? extends Object> values) {
//        return this;
//    }

    public ParseQuery<T> whereWithinKilometers(String key, ParseGeoPoint point, double maxDistance) {

//        return whereWithinRadians(key, point, maxDistance / ParseGeoPoint.EARTH_MEAN_RADIUS_KM);
        return  this;
    }

    public ParseQuery<T> setLimit(int newLimit) {
        return this;
    }

    public Task<Integer> countInBackground() {
        return Task.forResult(null);
    }

    public Task<List<T>> findInBackground() {
        return Task.forResult(null);
    }

    public Task<Void> pinInBackground(String name) {
        return Task.forResult(null);
    }

    public Task<Void> unpinInBackground(String name) {
        return Task.forResult(null);
    }



}
