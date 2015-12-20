package com.ieatta.com.parse.engine.realm;

import com.parse.ParseQuery;

/**
 * Created by djzhang on 12/20/15.
 */
public class DBQuery extends ParseQuery{
    public DBQuery(Class subclass) {
        super(subclass);
    }

    public DBQuery(String theClassName) {
        super(theClassName);
    }


}
