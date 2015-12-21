package com.ieatta.com.parse.engine.realm.utils;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.engine.realm.DBBuild;
import com.parse.ParseObject;

import java.util.List;

import bolts.Task;

/**
 * Created by djzhang on 12/21/15.
 */
public class RMQuery<T extends ParseModelAbstract> {
    private DBBuild build;

    public RMQuery(DBBuild build) {
        this.build = build;
    }

    public Task<List<T>> findInBackground() {

        return null;
    }
}
