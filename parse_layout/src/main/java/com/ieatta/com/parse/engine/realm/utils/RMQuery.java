package com.ieatta.com.parse.engine.realm.utils;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.engine.realm.DBBuilder;

import java.util.List;

import bolts.Task;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by djzhang on 12/21/15.
 */
public class RMQuery<T extends RealmObject> {
    private DBBuilder builder;

    public RMQuery(DBBuilder builder) {
        this.builder = builder;
    }

    public Task<List<ParseModelAbstract>> findInBackground() {

//        RealmResults results =  builder.where.equalTo("restaurantRef", "1CE562A4-A978-4B75-9B7B-2F3CF9F42A04").findAll();
        RealmResults results = builder.buildAll().findAll();

        results = builder.sort(results);

        List<ParseModelAbstract> list = new DBModelReader().readRealmResults(results, builder.modelType, builder.limit);

        return Task.forResult(list);
    }

    public Task<Integer> countInBackground() {
        RealmResults results = builder.buildAll().findAll();
        return Task.forResult(results.size());
    }

    public Task<Void> deleteInBackground() {
        RealmResults results = builder.buildAll().findAll();
        if (results.size() >= 1) {
            return builder.delete(results);
        }
        return Task.forResult(null);
    }

    public Task<Void> updateLocalInBackground() {

        return Task.forResult(null);
    }

    public Task<ParseModelAbstract> findFirstInBackground() {
        RealmObject realmObject = builder.buildAll().findFirst();
        ParseModelAbstract model = new DBModelReader().reader(realmObject, builder.modelType);
        return Task.forResult(model);
    }
}
