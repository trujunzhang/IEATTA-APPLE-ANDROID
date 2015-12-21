package com.ieatta.com.parse.engine.realm.utils;

import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.engine.realm.DBBuild;
import com.ieatta.com.parse.engine.realm.models.DBTeam;
import com.ieatta.com.parse.models.Team;
import com.parse.ParseObject;

import java.util.List;
import java.util.WeakHashMap;

import bolts.Task;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by djzhang on 12/21/15.
 */
public class RMQuery<T extends ParseModelAbstract> {
    private DBBuild build;

    public RMQuery(DBBuild build) {
        this.build = build;
    }

    public Task<List<T>> findInBackground() {
        RealmResults results = build.findAll();

        DBAccessUtils.readRealmResults(results,build.modelType);

        return null;
    }
}
