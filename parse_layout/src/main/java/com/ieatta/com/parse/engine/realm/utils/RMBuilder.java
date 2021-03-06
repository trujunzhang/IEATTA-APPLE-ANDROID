package com.ieatta.com.parse.engine.realm.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import io.realm.Case;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by djzhang on 12/21/15.
 */
public class RMBuilder<E extends RealmObject> {


    // boolean, short, ìnt, long, float, double, String, Date
    public RealmQuery<E> buildEqualTo(RealmQuery<E> where, HashMap<String, Object> equalMap) {

        for (String key : equalMap.keySet()) {
            Object value = equalMap.get(key);

            if (value.getClass().equals(String.class)) {
                where = where.equalTo(key, (String) value);
            } else if (value.getClass().equals(Integer.class)) {
                where = where.equalTo(key, (int) value);
            }else {
                Class<?> aClass = value.getClass();
                int x = 0;
            }
        }

        return where;
    }

    public RealmQuery<E> buildGreaterThan(RealmQuery<E> where, HashMap<String, Object> greaterMap) {
        for (String key : greaterMap.keySet()) {
            Object value = greaterMap.get(key);

            if (value.getClass().equals(Integer.class)) {
                where = where.greaterThan(key, (int) value);
            } else if (value.getClass().equals(Date.class)) {
                where = where.greaterThan(key, (Date) value);
            } else {
                Class<?> aClass = value.getClass();
                int x = 0;
            }
        }
        return where;
    }

    public RealmQuery<E> buildMatcheredIn(RealmQuery<E> where, HashMap<String, String> matchersMap) {

        for (String key : matchersMap.keySet()) {
            String keyword = matchersMap.get(key);
            where = where.contains(key, keyword, Case.INSENSITIVE);
        }

        return where;
    }


    public RealmQuery<E> buildContainedIn(RealmQuery<E> where, HashMap<String, List<String>> containedMap) {
        for (String key : containedMap.keySet()) {
            List<String> value = containedMap.get(key);
            for (String item : value) {
                where.contains(key, item);
            }
        }
        return where;
    }

    public RealmResults orderBy(RealmResults results, List<String> orderedByAscendingList, List<String> orderedByDescendingList) {
        for (String key : orderedByAscendingList) {
            results.sort(key, Sort.ASCENDING);
        }
        for (String key : orderedByDescendingList) {
            results.sort(key, Sort.DESCENDING);
        }
        return results;
    }


}
