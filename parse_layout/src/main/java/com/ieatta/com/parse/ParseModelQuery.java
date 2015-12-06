package com.ieatta.com.parse;

import com.ieatta.com.parse.models.NewRecord;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.parse.ParseACL;
import com.parse.ParseQuery;

import bolts.Continuation;
import bolts.Task;

import com.parse.ParseObject;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import bolts.TaskCompletionSource;

/**
 * Created by djzhang on 11/27/15.
 */
public abstract class ParseModelQuery extends ParseModelConvert {

    private static final String kPAPFieldModelOnlineCreatedAtKey = "createdAt";

    public ParseModelQuery(String objectUUID) {
        super(objectUUID);
    }

    public ParseModelQuery() {
        super();
    }

    /**
     * Query the server table from the last fetched date. and sort from oldest to newest.
     * <p/>
     * - parameter lastAsyncDate: the last fetched date
     * <p/>
     * - returns: query's instance
     */
    public ParseQuery createQueryForPullObjectsFromServer(Date lastAsyncDate, int limit) {
        ParseQuery query = this.getParseQueryInstance();
        query.setLimit(limit);

        // *** Important (used orderByAscending) ***
        query.orderByAscending(kPAPFieldModelOnlineCreatedAtKey);

        if (lastAsyncDate != null) {
            query.whereGreaterThan(kPAPFieldModelOnlineCreatedAtKey, lastAsyncDate);
        }

        return query;
    }

    /**
     * Query the offline table. and sort from oldest to newest.
     * <p/>
     * - returns: query's instance
     */
    public ParseQuery createQueryForPushObjectsToServer(int limit) {
        ParseQuery query = this.getParseQueryInstance();
        query.setLimit(limit);

        // *** Important (used orderByAscending) ***
        query.orderByAscending(kPAPFieldObjectCreatedDateKey);

        return query;
    }

    public ParseQuery createQueryByObjectUUID() {
        ParseQuery query = this.makeParseQuery();

        query.whereEqualTo(kPAPFieldObjectUUIDKey, this.objectUUID);

        return query;
    }

    public ParseQuery createQueryForBatching(List<String> points) {
        ParseQuery query = this.getParseQueryInstance();
        query.orderByDescending(kPAPFieldObjectCreatedDateKey);

        query.whereContainedIn(kPAPFieldObjectUUIDKey, points);

        return query;
    }

    public ParseQuery createSearchDisplayNameQuery(String keyword) {
        ParseQuery query = this.makeParseQuery();

        query.whereMatches(kPAPFieldDisplayNameKey, keyword, "i");

        return query;
    }

    static ParseQuery createQuery(PQueryModelType type, ParseModelAbstract model) {
        ParseModelQuery instance = (ParseModelQuery) ParseModelAbstract.getInstanceFromType(type, ParseModelAbstract.getPoint(model));
        return instance.createQueryByObjectUUID();
    }

    protected ParseQuery getParseQueryInstance() {
        return new ParseQuery(this.getParseTableName());
    }

    public ParseQuery makeParseQuery() {
        ParseQuery query = this.getParseQueryInstance();
        query.orderByDescending(kPAPFieldObjectCreatedDateKey);
        return query;
    }

    ParseQuery createQueryFromRecord() {
        ParseQuery query = this.getParseQueryInstance();

        // *** Import *** The newest row in the table.
        query.orderByDescending(kPAPFieldObjectCreatedDateKey);
        query.whereEqualTo(kPAPFieldObjectUUIDKey, this.objectUUID);

        return query;
    }

    public static Task<List<ParseModelAbstract>> queryFromDatabase(final PQueryModelType type, final ParseQuery query) {
        return ParseModelQuery.findLocalObjectsInBackground(query).onSuccessTask(new Continuation<List<ParseObject>, Task<List<ParseModelAbstract>>>() {
            @Override
            public Task<List<ParseModelAbstract>> then(Task<List<ParseObject>> task) throws Exception {
                ParseModelConvert instance = (ParseModelConvert) ParseModelAbstract.getInstanceFromType(type);
                return instance.convertToParseModelsTask(task, true);
            }
        });
    }

    protected Task<List<ParseModelAbstract>> queryParseModels(PQueryModelType type, List<String> points) {
        return ParseModelQuery.queryFromDatabase(type, this.createQueryForBatching(points));
    }

    @Override
    public Task<ParseModelAbstract> getFirstLocalModelArrayTask() {
        return ParseModelQuery.findFirstLocalObjectInBackground(this.createQueryByObjectUUID()).onSuccessTask(new Continuation<ParseObject, Task<ParseModelAbstract>>() {
            @Override
            public Task<ParseModelAbstract> then(Task<ParseObject> task) throws Exception {
                return convertToLocalModelTask(task);
            }
        });
    }

    @Override
    public Task<Object> getFirstOnlineObjectTask() {
        return this.createQueryByObjectUUID().getFirstInBackground();
    }

    /**
     * Get count of objects on the offline datastore.
     * <p/>
     * - parameter query:           query's instance
     */
    public Task<Integer> countLocalObjects(ParseQuery query) {
        // *** Important ***
        query.fromLocalDatastore();

        return query.countInBackground();
    }

    public Task<Integer> countObjects(ParseQuery query) {
        return query.countInBackground();
    }

    /**
     * **** Important ****
     * <p/>
     * Get first PFObject from the offline database.
     * <p/>
     * - parameter query: ParseQuery's instance
     * <p/>
     * - returns: the first object's array,like [PFObject's instance].
     */
    public static Task<ParseObject> getFirstLocalObjectArrayInBackground(ParseQuery query) {
        final Task.TaskCompletionSource tcs = Task.create();

        // **** Important ****
        // If not found Parse's findLocalObjectsInBackground
        ParseModelQuery.findFirstLocalObjectInBackground(query).continueWith(new Continuation<ParseObject, Object>() {
            @Override
            public Object then(Task<ParseObject> task) throws Exception {
                if (task.isFaulted()) {
                    com.parse.ParseException exception = (com.parse.ParseException) task.getError();
                    if (exception.getCode() == com.parse.ParseException.OBJECT_NOT_FOUND) {
                        // **** Important ****
                        // Here, return value is 'null' means that not found object.
                        // For example, if all newrecord objects already pushed to server.
                        // No NewRecord rows on the local table. So not found NewRecord here.
                        tcs.setResult(null);
                    } else {
                        tcs.setError(task.getError());
                    }
                } else {
                    tcs.setResult(task.getResult());
                }
                return null;
            }
        });

        return tcs.getTask();
    }

    public static Task<List<ParseObject>> findLocalObjectsInBackground(ParseQuery query) {
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
    private static Task<ParseObject> findFirstLocalObjectInBackground(ParseQuery query) {
        // *** Important ***
        query.fromLocalDatastore();

        return query.getFirstInBackground();
    }

    private ParseObject makeObject() {
        ParseObject object = this.createObject();

        ParseACL acl = this.getACL();
        object.setACL(acl);

        return object;
    }

    // =============================================================================
    //        offline Store
    // =============================================================================
    public Task<Void> pinInBackgroundForModel() {
        ParseObject object = makeObject();
        this.writeLocalObject(object);

        return object.pinInBackground("Offline");
    }

    public Task<Void> pinInBackgroundWithNewRecord() {
        return this.pinInBackgroundForModel().onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                return getNewRecord().pinInBackgroundForModel();
            }
        });
    }

    // =============================================================================
    //        Online Store
    // =============================================================================
    Task<Void> saveInBackgroundTask() {
        ParseObject object = makeObject();
        this.writeObject(object);

        return object.saveInBackground();
    }

    /**
     * Unpin the first offline object by query instance.
     * <p/>
     * - parameter query:           query's instance
     */
    public Task<Void> unpinInBackground(ParseQuery query) {
        return ParseModelQuery.getFirstLocalObjectArrayInBackground(query).onSuccessTask(new Continuation<ParseObject, Task<Void>>() {
            @Override
            public Task<Void> then(Task<ParseObject> task) throws Exception {
                ParseObject object = task.getResult();
                if (object != null) {
                    return ParseModelQuery.unpinObjectInBackground(object);
                }
                // **** Important ****
                // Here, return value is 'null' means that not found object.
                // For example, if all newrecord objects already pushed to server.
                // No NewRecord rows on the local table. So not found NewRecord here.
                return Task.forResult(null);
            }
        });
    }

    /**
     * Unpin the first offline object itself with NewRecord by query instance.
     * <p/>
     * - parameter deletedModel:    ParseModelAbstract's instance that want to delete
     */
    public Task<Void> unpinInBackgroundWithNewRecord() {
        final ParseQuery newRecordQuery = new NewRecord(this.getModelType(), ParseModelAbstract.getPoint(this)).createQueryForDeletedModel();

        return this.unpinInBackground(this.createQueryByObjectUUID()).onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                return unpinInBackground(newRecordQuery);
            }
        });
    }

    /**
     * Unpin the offline object by a given PFObject's instance.
     * <p/>
     * - parameter object:          PFObject's instance
     * - parameter completionBlock: callback variable
     */
    public static  Task<Void> unpinObjectInBackground(ParseObject object) {
        return object.unpinInBackground("Offline");
    }

}
