package com.ieatta.com.parse.engine.realm;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.engine.realm.utils.RMQuery;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.parse.ParseGeoPoint;

import java.util.Date;
import java.util.List;

import bolts.Task;

/**
 * Created by djzhang on 12/20/15.
 */
public class LocalQuery<T extends ParseModelAbstract> {
    private DBBuilder builder = new DBBuilder();

    public LocalQuery(String theClassName, PQueryModelType modelType) {
        this.builder.setModelType(modelType);
    }

    public static <T extends ParseModelAbstract> LocalQuery<T> getDBQuery(String className, PQueryModelType modelType) {
        return new LocalQuery<>(className, modelType);
    }

    public LocalQuery<T> fromLocalDatastore() {
        this.builder.fromLocalDatastore();
        return this;
    }

    public LocalQuery<T> whereEqualTo(String key, Object value) {
        this.builder.whereEqualTo(key, value);
        return this;
    }

    public LocalQuery<T> orderByDescending(String key) {
        this.builder.orderByDescending(key);
        return this;
    }

    public LocalQuery<T> orderByAscending(String key) {
        this.builder.orderByAscending(key);
        return this;
    }

    public LocalQuery<T> whereGreaterThan(String key, Date date) {
        this.builder.whereGreaterThan(key, date);
        return this;
    }

    public LocalQuery<T> whereMatches(String key, String keyword, String regExp) {
        this.builder.whereMatchers(key, keyword);
        return this;
    }

    public LocalQuery<T> whereContainedIn(String key, List<String> list) {
        this.builder.whereContainedIn(key, list);
        return this;
    }

    public LocalQuery<T> setLimit(int newLimit) {
        this.builder.setLimit(newLimit);
        return this;
    }

    public Task<Integer> countInBackground() {
        return new RMQuery(this.builder).countInBackground();
    }

    public Task<List<T>> findInBackground() {
        return new RMQuery(this.builder).findInBackground();
    }

    public Task<Void> deleteInBackground() {
        return new RMQuery(this.builder).deleteInBackground();
    }

    public Task<Void> updateLocalInBackground() {
        return new RMQuery(this.builder).updateLocalInBackground();
    }

    public Task<ParseModelAbstract> findFirstInBackground() {
        return new RMQuery(this.builder).findFirstInBackground();
    }
}
