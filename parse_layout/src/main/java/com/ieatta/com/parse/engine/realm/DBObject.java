package com.ieatta.com.parse.engine.realm;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by djzhang on 12/20/15.
 */

@ParseClassName("DBObject")
public class DBObject extends ParseObject{

    public DBObject() {
    }

    public DBObject(String theClassName) {
        super(theClassName);
    }


}
