package com.ieatta.com.parse.engine.realm.utils;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.engine.realm.DBBuilder;

import java.util.List;

import bolts.Task;
import io.realm.RealmResults;

/**
 * Created by djzhang on 12/21/15.
 */
public class RMQuery<T extends ParseModelAbstract> {
    private DBBuilder builder;

    public RMQuery(DBBuilder builder) {
        this.builder = builder;
    }

    public Task<List<T>> findInBackground() {

//        RealmResults results =  builder.where.equalTo("restaurantRef", "1CE562A4-A978-4B75-9B7B-2F3CF9F42A04").findAll();
        RealmResults results = builder.buildAll().findAll();

        results = builder.sort(results);

        List<T> list = new DBModelReader().readRealmResults(results, builder.modelType);

        return Task.forResult(list);
    }


}
