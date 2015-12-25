package com.ieatta.com.parse.engine.realm;

import com.ieatta.com.parse.ParseModelAbstract;

import com.ieatta.com.parse.engine.realm.utils.DBAccessUtils;
import com.parse.ParseObject;

import bolts.Task;

/**
 * Created by djzhang on 12/20/15.
 */

public class DBObject extends Object {

    public DBObject() {
    }

    public static Task<Void> pinInBackground(ParseModelAbstract model) {
        return DBAccessUtils.pinInBackground(model);
    }

}
