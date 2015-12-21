package com.ieatta.com.parse.engine.realm;

import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.com.parse.engine.realm.models.DBTeam;
import com.ieatta.com.parse.engine.realm.utils.RMQueryUtils;
import com.ieatta.com.parse.models.enums.PQueryModelType;

import java.util.HashMap;
import java.util.LinkedHashMap;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;

/**
 * Created by djzhang on 12/21/15.
 */
public class DBBuild<T extends RealmObject> {
    private DBBuild self = this;

    public PQueryModelType modelType;
    public boolean isFromLocalDatastore = false;
    private HashMap<String, Object> equalMap = new LinkedHashMap<>();
    private int limit = -1; // negative limits mean, do not send a limit

    public RealmQuery<T> where;

    public void fromLocalDatastore() {
        self.isFromLocalDatastore = true;
    }

    public void whereEqualTo(String key, Object value) {
        equalMap.put(key, value);
    }

    public void orderByDescending(String key) {

    }

    public void orderByAscending(String key) {

    }

    public void setLimit(int newLimit) {
        self.limit = newLimit;
    }

    public void setModelType(PQueryModelType modelType) {
        self.modelType = modelType;
        self.where = new RMQueryUtils().getRealmQuery(modelType);
    }
}
