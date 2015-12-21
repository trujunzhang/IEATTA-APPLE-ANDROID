package com.ieatta.com.parse.engine.realm;

import com.ieatta.com.parse.engine.realm.utils.RMBuilder;
import com.ieatta.com.parse.engine.realm.utils.RMQueryUtils;
import com.ieatta.com.parse.models.enums.PQueryModelType;

import java.util.HashMap;
import java.util.LinkedHashMap;

import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by djzhang on 12/21/15.
 */
public class DBBuilder<T extends RealmObject> {
    private DBBuilder self = this;

    public PQueryModelType modelType;
    public boolean isFromLocalDatastore = false;
    private HashMap<String, Object> equalMap = new LinkedHashMap<>();
    private int limit = -1; // negative limits mean, do not send a limit

    public RealmQuery<T> where;

    private RMBuilder<T> rmBuilder = new RMBuilder<>();

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

    public RealmQuery<T> buildAll() {
        self.where = self.rmBuilder.buildEqualTo(self.where,self.equalMap);

        return self.where;
    }
}
