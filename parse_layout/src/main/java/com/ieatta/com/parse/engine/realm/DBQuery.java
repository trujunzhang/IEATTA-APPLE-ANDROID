package com.ieatta.com.parse.engine.realm;

import com.ieatta.com.parse.engine.realm.utils.RMQuery;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import bolts.Task;

/**
 * Created by djzhang on 12/20/15.
 */
public class DBQuery<T extends ParseObject> extends ParseQuery {
    private DBBuilder builder = new DBBuilder();

    public DBQuery(Class subclass) {
        super(subclass);
    }

    public DBQuery(String theClassName, PQueryModelType modelType) {
        super(theClassName);
        this.builder.setModelType(modelType);
    }

    public static <T extends ParseObject> DBQuery<T> getDBQuery(String className, PQueryModelType modelType) {
        return new DBQuery<>(className,modelType);
    }

    @Override
    public ParseQuery<T> fromLocalDatastore() {
        super.fromLocalDatastore();
        this.builder.fromLocalDatastore();
        return this;
    }

    @Override
    public DBQuery<T> whereEqualTo(String key, Object value) {
        super.whereEqualTo(key, value);
        this.builder.whereEqualTo(key, value);
        return this;
    }

    @Override
    public DBQuery<T> orderByDescending(String key) {
        super.orderByDescending(key);
        this.builder.orderByDescending(key);
        return this;
    }

    @Override
    public DBQuery<T> orderByAscending(String key) {
        super.orderByAscending(key);
        this.builder.orderByAscending(key);
        return this;
    }

//    @Override
//    public DBQuery<T> whereContainedIn(String key, Collection<? extends Object> values) {
//        return this;
//    }

    public ParseQuery<T> whereWithinKilometers(String key, ParseGeoPoint point, double maxDistance) {
        super.whereWithinKilometers(key, point, maxDistance);
//        return whereWithinRadians(key, point, maxDistance / ParseGeoPoint.EARTH_MEAN_RADIUS_KM);
        return this;
    }

    public ParseQuery<T> setLimit(int newLimit) {
        super.setLimit(newLimit);
        this.builder.setLimit(newLimit);
        return this;
    }

    public Task<Integer> countInBackground() {
        return super.countInBackground();
//        return Task.forResult(null);
    }

    public Task<List<T>> findInBackground() {
        if(this.builder.isFromLocalDatastore == true){
            return new RMQuery(this.builder).findInBackground();
        }
        return super.findInBackground();
    }


}
