package com.ieatta.com.parse.engine.realm;

import com.parse.ParseQuery;

/**
 * Created by djzhang on 12/20/15.
 */
public class RMQuery extends ParseQuery{
    public RMQuery(Class subclass) {
        super(subclass);
    }

    public RMQuery(String theClassName) {
        super(theClassName);
    }
}
