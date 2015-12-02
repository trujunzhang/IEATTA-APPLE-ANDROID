package com.ieatta.com.parse;

import com.ieatta.com.parse.models.NewRecord;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.parse.ParseACL;
import com.parse.ParseQuery;

import bolts.Continuation;
import bolts.Task;

import com.parse.ParseObject;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import bolts.TaskCompletionSource;

/**
 * Created by djzhang on 11/27/15.
 */
public abstract class ParseModelQuery extends ParseJsoner {


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

    ParseQuery createQueryByObjectUUID() {
        ParseQuery query = this.makeParseQuery();

        query.whereEqualTo(kPAPFieldObjectUUIDKey, this.objectUUID);

        return query;
    }

    ParseQuery createQueryForBatching(LinkedList<String> points) {
        ParseQuery query = this.getParseQueryInstance();
        query.orderByDescending(kPAPFieldObjectCreatedDateKey);

        query.whereContainedIn(kPAPFieldObjectUUIDKey, points);

        return query;
    }

    protected ParseQuery createSearchDisplayNameQuery(String keyword) {
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

    public static Task<Object> queryFromDatabase(final PQueryModelType type, final ParseQuery query) {
        final TaskCompletionSource<Object> queryTask = new TaskCompletionSource<>();

        ParseModelQuery.findLocalObjectsInBackground(query)
                .continueWith(new Continuation<List<ParseObject>, Object>() {
                    @Override
                    public Object then(Task<List<ParseObject>> task) throws Exception {

                        LinkedList<ParseModelAbstract> array = ParseModelAbstract.getInstanceFromType(type).convertToParseModelArray(task.getResult(), true);

                        TaskCompletionSource<Object> nextTask = new TaskCompletionSource<>();
                        nextTask.setResult(array);

                        return nextTask;
                    }
                }).continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
                if (task.isFaulted() == true) {
                    queryTask.setError(task.getError());
                } else {
                    queryTask.setResult(task.getResult());
                }
                return null;
            }
        });


        return queryTask.getTask();
    }

    protected Task<Object> queryParseModels(PQueryModelType type, LinkedList<String> points) {
        return ParseModelQuery.queryFromDatabase(type, this.createQueryForBatching(points));
    }

    @Override public Task<Object> getFirstLocalModelArrayTask()   {
         return this.getFirstLocalObjectArrayInBackground(this.createQueryByObjectUUID()).continueWith(new Continuation<Object, Object>() {
             @Override
             public Object then(Task<Object> task) throws Exception {
                 return convertToLocalModelTask(task);
             }
         });
    }

     @Override     public Task<Object> getFirstOnlineObjectTask()   {
        return this.createQueryByObjectUUID().getFirstInBackground();
    }

    /**
     * Get count of objects on the offline datastore.
     * <p/>
     * - parameter query:           query's instance
     */
    protected Task<Integer> countLocalObjects(ParseQuery query) {
        // *** Important ***
        query.fromLocalDatastore();

        return query.countInBackground();
    }

    Task<Integer> countObjects(ParseQuery query) {
        return query.countInBackground();
    }

    /**
     * Get first PFObject from the offline database.
     * <p/>
     * - parameter query: ParseQuery's instance
     * <p/>
     * - returns: the first object's array,like [PFObject's instance].
     */
    public static Task<Object> getFirstLocalObjectArrayInBackground(ParseQuery query) {
        final TaskCompletionSource findTask = new TaskCompletionSource();

        ParseModelQuery.findLocalObjectsInBackground(query).continueWith(new Continuation<List<ParseObject>, Object>() {
            @Override
            public Object then(Task<List<ParseObject>> task) throws Exception {
                if (task.getError() != null) {
                    findTask.setError(task.getError());
                } else {
                    LinkedList<ParseObject> objects = new LinkedList<ParseObject>(task.getResult());
                    if (objects.size() == 0) {
                        findTask.setResult(new LinkedList<ParseObject>());
                    } else {
                        findTask.setResult(objects);
                    }
                }
                return null;
            }
        });

        return findTask.getTask();
    }

    public static Task<List<ParseObject>> findLocalObjectsInBackground(ParseQuery query) {
        // *** Important ***
        query.fromLocalDatastore();

        return query.findInBackground();
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

    public Task<Object> pinInBackgroundWithNewRecord() {
        return this.pinInBackgroundForModel().continueWith(new Continuation<Void, Object>() {
            @Override
            public Object then(Task<Void> task) throws Exception {
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
     Unpin the first offline object by query instance.

     - parameter query:           query's instance
     */

    Task<Object> unpinInBackground(ParseQuery query)     {
        final TaskCompletionSource unpinTask = new TaskCompletionSource();

        this.getFirstLocalObjectArrayInBackground(query).continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
                if(task.getError()!= null){
                    unpinTask.setResult(task.getError());
                }else {
                    Object result = task.getResult();
                    LinkedList<Object> value = new LinkedList<Object>((Collection<?>) result);
                    if(value.size() >= 1){
                        ParseObject object = (ParseObject) value.get(0);
                        ParseModelQuery.unpinObjectInBackground(object).continueWith(new Continuation<Void, Object>() {
                            @Override
                            public Object then(Task<Void> task) throws Exception {
                                if(task.getError() != null){
                                    unpinTask.setError(task.getError());
                                }else{
                                    unpinTask.setResult(true);
                                }
                                return null;
                            }
                        });
                    }else{
                        // **** Important ****
                        // Here, return value is false means that not found object.
                        // For example, if all newrecord objects already pushed to server.
                        // No newrecord rows on the local table. So not found newrecord here.
                        unpinTask.setResult(false);
                    }

                }

                return null;
            }
        });

        return unpinTask.getTask();
    }

    /**
     Unpin the first offline object itself with NewRecord by query instance.

     - parameter deletedModel:    ParseModelAbstract's instance that want to delete
     */
    Task<Object> unpinInBackgroundWithNewRecord()     {
        final ParseQuery newRecordQuery = new NewRecord(this.getModelType(), ParseModelAbstract.getPoint(this)).createQueryForDeletedModel();
        return this.unpinInBackground(this.createQueryByObjectUUID()).continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
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
    static public Task<Void> unpinObjectInBackground(ParseObject object) {
        return object.unpinInBackground("Offline");
    }

    /**
     Delete the first online object, after creating a query instance by objectUUID.

     - parameter type:            ParseModel's type
     - parameter model:           insance of query
     */
    Task<Object> deleteOnlineObjectInBackground(PQueryModelType type,ParseModelAbstract model)     {
        return this.deleteOnlineObjectInBackground(ParseModelQuery.createQuery(type,model));
    }

    /**
     Delete the first online object by query instance.

     - parameter query:           query's instance
     */
    Task<Object> deleteOnlineObjectInBackground(ParseQuery query)     {
        return this.getFirstLocalObjectArrayInBackground(query).continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
                ParseObject object = (ParseObject) task.getResult();
                return deleteOnlineObjectBackgroundForObject(object);
            }
        });
    }

    /**
     * Delete the online object by a given PFObject's instance.
     * <p/>
     * - parameter object:          PFObject's instance
     */
    Task<Void> deleteOnlineObjectBackgroundForObject(ParseObject object) {
        return object.deleteInBackground();
    }
}
