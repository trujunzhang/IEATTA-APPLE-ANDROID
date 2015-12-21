package com.ieatta.com.parse.engine.realm;

import com.ieatta.com.parse.engine.realm.utils.RMBuilder;
import com.ieatta.com.parse.engine.realm.utils.RMQueryUtils;
import com.ieatta.com.parse.models.enums.PQueryModelType;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

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
    private HashMap<String, Object> greaterMap = new LinkedHashMap<>();
    private HashMap<String, List<String>> containedMap = new LinkedHashMap<>();
    private List<String> orderedByDescendingList = new LinkedList<>();
    private List<String> orderedByAscendingList = new LinkedList<>();
    public int limit = -1; // negative limits mean, do not send a limit

    public RealmQuery<T> where;

    private RMBuilder<T> rmBuilder = new RMBuilder<>();

    public void fromLocalDatastore() {
        self.isFromLocalDatastore = true;
    }

    public void whereEqualTo(String key, Object value) {
        self.equalMap.put(key, value);
    }

    public void whereGreaterThan(String key, Object value) {
        self.greaterMap.put(key, value);
    }
    public void orderByDescending(String key) {
        self.orderedByDescendingList.add(key);
    }

    public void whereContainedIn(String key, List<String> list) {
        self.containedMap.put(key,list);
    }
    public void orderByAscending(String key) {
        self.orderedByAscendingList.add(key);
    }

    public void setLimit(int newLimit) {
        self.limit = newLimit;
    }

    public void setModelType(PQueryModelType modelType) {
        self.modelType = modelType;
        self.where = new RMQueryUtils().getRealmQuery(modelType);
    }

    public RealmQuery<T> buildAll() {
        self.where = self.rmBuilder.buildEqualTo(self.where, self.equalMap);
        self.where = self.rmBuilder.buildGreaterThan(self.where, self.greaterMap);
        self.where = self.rmBuilder.buildContainedIn(self.where,self.containedMap);

        return self.where;
    }

    public RealmResults sort(RealmResults results) {
        return self.rmBuilder.orderBy(results, self.orderedByAscendingList, self.orderedByDescendingList);
    }



}
