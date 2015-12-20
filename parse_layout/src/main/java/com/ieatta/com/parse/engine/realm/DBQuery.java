package com.ieatta.com.parse.engine.realm;

import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by djzhang on 12/20/15.
 */
public class DBQuery<T extends DBObject> extends ParseQuery {
    public DBQuery(Class subclass) {
        super(subclass);
    }

    public DBQuery(String theClassName) {
        super(theClassName);
    }

    public static <T extends DBObject> DBQuery<T> getDBQuery(String className) {
        return new DBQuery<>(className);
    }
}
