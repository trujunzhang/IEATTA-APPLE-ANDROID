package com.ieatta.com.parse.engine.realm;

import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.com.parse.engine.realm.utils.RMBuilder;
import com.ieatta.com.parse.engine.realm.utils.RMQueryUtils;
import com.ieatta.com.parse.models.enums.PQueryModelType;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import bolts.Task;
import io.realm.Realm;
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
    private HashMap<String, String> matchersMap = new LinkedHashMap<>();
    public HashMap<String, List<String>> containedMap = new LinkedHashMap<>();
    private List<String> orderedByDescendingList = new LinkedList<>();
    private List<String> orderedByAscendingList = new LinkedList<>();
    public int limit = -1; // negative limits mean, do not send a limit

    private Realm realm;
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

    public void whereContainedIn(String key, List<String> list) {
        self.containedMap.put(key, list);
    }

    public void whereMatchers(String key, String keyword) {
        self.matchersMap.put(key, keyword);
    }

    public void orderByDescending(String key) {
        self.orderedByDescendingList.add(key);
    }

    public void orderByAscending(String key) {
        self.orderedByAscendingList.add(key);
    }

    public void setLimit(int newLimit) {
        self.limit = newLimit;
    }

    public void setModelType(PQueryModelType modelType) {
        self.modelType = modelType;
        self.realm = Realm.getInstance(EnvironmentUtils.sharedInstance.getGlobalContext());
        self.where = new RMQueryUtils().getRealmQuery(self.realm, modelType);
    }

    public RealmQuery<T> buildAll() {
        self.where = self.rmBuilder.buildEqualTo(self.where, self.equalMap);
        self.where = self.rmBuilder.buildGreaterThan(self.where, self.greaterMap);
        self.where = self.rmBuilder.buildMatcheredIn(self.where, self.matchersMap);

        return self.where;
    }

    public RealmResults sort(RealmResults results) {
        return self.rmBuilder.orderBy(results, self.orderedByAscendingList, self.orderedByDescendingList);
    }

    public void delete(RealmResults results) {
        // All changes to data must happen in a transaction
        realm.beginTransaction();
        results.removeLast();
        realm.commitTransaction();
    }

    public void closeDatabase() {
        self.realm.close();
    }

}
