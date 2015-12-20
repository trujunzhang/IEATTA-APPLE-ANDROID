package com.ieatta.com.parse.engine.realm;

import com.parse.ParseObject;

import bolts.Task;

/**
 * Created by djzhang on 12/20/15.
 */

public class DBObject extends Object {

    public DBObject() {
    }

    public static Task<Void> pinInBackground(String name,ParseObject object) {
        return object.pinInBackground(name);
    }

    public static Task<Void> unpinInBackground(String name,ParseObject object) {
        return object.unpinInBackground(name);
    }


}
