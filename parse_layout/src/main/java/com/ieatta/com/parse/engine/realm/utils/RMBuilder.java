package com.ieatta.com.parse.engine.realm.utils;

import java.util.HashMap;

import io.realm.RealmObject;
import io.realm.RealmQuery;

/**
 * Created by djzhang on 12/21/15.
 */
public class RMBuilder<E extends RealmObject> {


    public RealmQuery<E> buildEqualTo(RealmQuery<E> where, HashMap<String, Object> equalMap) {
        equalMap.keySet();

        for(String key : equalMap.keySet()){
            Object value = equalMap.get(key);

        }


        return where;
    }
}
