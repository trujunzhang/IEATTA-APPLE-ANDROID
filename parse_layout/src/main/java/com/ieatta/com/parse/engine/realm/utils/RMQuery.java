package com.ieatta.com.parse.engine.realm.utils;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.engine.realm.DBBuilder;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import bolts.Task;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by djzhang on 12/21/15.
 */
public class RMQuery<T extends RealmObject> {
    private DBBuilder<T> builder;

    public RMQuery(DBBuilder<T> builder) {
        this.builder = builder;
    }

    public Task<List<ParseModelAbstract>> findInBackground() {
        List<ParseModelAbstract> list = null;

        try {
            RealmResults results = builder.buildAll().findAll();
            results = builder.sort(results);
            list = new DBModelReader().readRealmResults(results, builder.modelType, builder.limit, builder.containedMap);
        } catch (Exception e) {
            return Task.forError(e);
        } finally {
            builder.closeDatabase();
        }

        return Task.forResult(list);
    }

    public Task<Integer> countInBackground() {
        long count = 0;

        try {
            count = builder.buildAll().count();
        } catch (Exception e) {
            return Task.forError(e);
        } finally {
            builder.closeDatabase();
        }

        return Task.forResult(new Long(count).intValue());
    }

    public Task<Void> deleteInBackground() {
        try {
            RealmResults results = builder.buildAll().findAll();
            if (results.size() >= 1) {
                builder.delete(results);
            }
        } catch (Exception e) {
            return Task.forError(e);
        } finally {
            builder.closeDatabase();
        }

        return Task.forResult(null);
    }

    public Task<Void> updateLocalInBackground() {
        return Task.forResult(null);
    }

    public Task<ParseModelAbstract> findFirstInBackground() {
        ParseModelAbstract model = null;

        try {
            RealmObject realmObject = builder.buildAll().findFirst();
            model = new DBModelReader().reader(realmObject, builder.modelType);
        } catch (Exception e) {
            return Task.forError(e);
        } finally {
            builder.closeDatabase();
        }

        return Task.forResult(model);
    }
}
