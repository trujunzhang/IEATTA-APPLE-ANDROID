package com.ieatta.com.parse;

import com.ieatta.com.parse.engine.realm.DBObject;
import com.ieatta.com.parse.engine.realm.LocalQuery;
import com.ieatta.com.parse.models.NewRecord;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 11/27/15.
 */
public abstract class ParseModelQuery extends ParseModelLocalQuery {
    private ParseModelQuery self = this;


    public ParseModelQuery(String objectUUID) {
        super(objectUUID);
    }

    public ParseModelQuery() {
        super();
    }



}
