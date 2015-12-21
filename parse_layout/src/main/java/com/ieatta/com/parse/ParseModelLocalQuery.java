package com.ieatta.com.parse;


import com.ieatta.com.parse.engine.realm.DBObject;
import com.ieatta.com.parse.engine.realm.DBQuery;
import com.ieatta.com.parse.models.NewRecord;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 12/21/15.
 */
public abstract class ParseModelLocalQuery extends ParseModelConvert{
    public ParseModelLocalQuery(String objectUUID) {
        super(objectUUID);
    }

    public ParseModelLocalQuery() {
        super();
    }

    /**
     *  Query the local database and convert to ParseObject.
     * @param query
     * @return
     */
    public static Task<List<ParseObject>> findLocalParseObjectsInBackground(ParseQuery query) {
        // *** Important ***
        query.fromLocalDatastore();

        return query.findInBackground();
    }

    /**
     * Get count of objects on the offline datastore.
     * <p/>
     * - parameter query:           query's instance
     */
    public Task<Integer> countLocalObjects(DBQuery query) {
        // *** Important ***
        query.fromLocalDatastore();

        return query.countInBackground();
    }

    public static Task<List<ParseObject>> findLocalObjectsInBackground(DBQuery query) {
        // *** Important ***
        query.fromLocalDatastore();

        return query.findInBackground();
    }

    /**
     * if not found the first object,
     * will return Exception: 'no results found for query'(code: com.parse.ParseException.OBJECT_NOT_FOUND)
     *
     * @param query
     * @return
     */
    protected static Task<List<ParseObject>> findFirstLocalObjectInBackground(DBQuery query) {
        // *** Important ***
        query.fromLocalDatastore();
        // *** Just get the first object.
        query.setLimit(1);

        return query.findInBackground();
    }

    public static Task<List<ParseModelAbstract>> queryFromDatabase(final PQueryModelType type, final DBQuery query) {
        return ParseModelQuery.findLocalObjectsInBackground(query)
                .onSuccessTask(new Continuation<List<ParseObject>, Task<List<ParseModelAbstract>>>() {
                    @Override
                    public Task<List<ParseModelAbstract>> then(Task<List<ParseObject>> task) throws Exception {
                        ParseModelConvert instance = (ParseModelConvert) ParseModelAbstract.getInstanceFromType(type);
                        return instance.convertToParseModelsTask(task, true);
                    }
                });
    }

}
