package com.ieatta.com.parse.engine.realm;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.ParseModelQuery;
import com.ieatta.com.parse.engine.realm.utils.DBAccessUtils;
import com.ieatta.com.parse.engine.realm.utils.DBModelConvert;
import com.parse.ParseObject;

import bolts.Task;

/**
 * Created by djzhang on 12/20/15.
 */

public class DBObject extends Object {

    public DBObject() {
    }

    public static Task<Void> pinInBackground(String name, ParseObject object, ParseModelQuery model) {
        DBAccessUtils.pinInBackground(model);
        return object.pinInBackground(name);
    }

    public static Task<Void> unpinInBackground(String name, ParseObject object, ParseModelAbstract model) {
        return object.unpinInBackground(name);
    }


}