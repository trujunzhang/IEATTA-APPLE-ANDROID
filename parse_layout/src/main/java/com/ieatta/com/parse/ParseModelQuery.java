package com.ieatta.com.parse;

import com.ieatta.com.parse.engine.realm.DBQuery;
import com.ieatta.com.parse.models.NewRecord;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.parse.ParseACL;

import bolts.Continuation;
import bolts.Task;

import com.parse.ParseObject;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by djzhang on 11/27/15.
 */
public abstract class ParseModelQuery extends ParseModelConvert {
    private ParseModelQuery self = this;

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
    public DBQuery createQueryForPullObjectsFromServer(Date lastAsyncDate, int limit) {
        DBQuery query = this.getDBQueryInstance();
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
    public DBQuery createQueryForPushObjectsToServer(int limit) {
        DBQuery query = this.getDBQueryInstance();
        query.setLimit(limit);

        // *** Important (used orderByAscending) ***
        query.orderByAscending(kPAPFieldObjectCreatedDateKey);

        return query;
    }

    public DBQuery createQueryByObjectUUID() {
        DBQuery query = this.makeDBQuery();

        query.whereEqualTo(kPAPFieldObjectUUIDKey, this.objectUUID);

        return query;
    }

    public DBQuery createQueryForBatching(List<String> points) {
        DBQuery query = this.getDBQueryInstance();
        query.orderByDescending(kPAPFieldObjectCreatedDateKey);

        query.whereContainedIn(kPAPFieldObjectUUIDKey, points);

        return query;
    }

    public DBQuery createSearchDisplayNameQuery(String keyword) {
        DBQuery query = this.makeDBQuery();

        query.whereMatches(kPAPFieldDisplayNameKey, keyword, "i");

        return query;
    }

    static DBQuery createQuery(PQueryModelType type, ParseModelAbstract model) {
        ParseModelQuery instance = (ParseModelQuery) ParseModelAbstract.getInstanceFromType(type, ParseModelAbstract.getPoint(model));
        return instance.createQueryByObjectUUID();
    }

    protected DBQuery getDBQueryInstance() {
        DBQuery<ParseObject> query = DBQuery.getDBQuery(this.getParseTableName());
        return  query;
    }

    public DBQuery makeDBQuery() {
        DBQuery query = this.getDBQueryInstance();
        query.orderByDescending(kPAPFieldObjectCreatedDateKey);
        return query;
    }

    DBQuery createQueryFromRecord() {
        DBQuery query = this.getDBQueryInstance();

        // *** Import *** The newest row in the table.
        query.orderByDescending(kPAPFieldObjectCreatedDateKey);
        query.whereEqualTo(kPAPFieldObjectUUIDKey, this.objectUUID);

        return query;
    }

    public static Task<List<ParseModelAbstract>> queryFromDatabase(final PQueryModelType type, final DBQuery query) {
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
        return self.getFirstLocalObjectArrayInBackground(this.createQueryByObjectUUID()).onSuccessTask(new Continuation<ParseObject, Task<ParseModelAbstract>>() {
            @Override
            public Task<ParseModelAbstract> then(Task<ParseObject> task) throws Exception {
                return convertToLocalModelTask(task);
            }
        });
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

    public Task<Integer> countObjects(DBQuery query) {
        return query.countInBackground();
    }

    /**
     * Because Task will return Exception,'no results found for query'(code is com.parse.ParseException.OBJECT_NOT_FOUND),
     * when no the first object found.
     *
     * @param previous
     * @return
     */
    private static Task<ParseObject> getFirstParseObjectTask(Task<List<ParseObject>> previous) {
        LinkedList<ParseObject> objects = new LinkedList<>(previous.getResult());
        if (objects.size() == 0) {

            // **** Important ****
            // Here, return value is 'null' means that not found object.
            // For example, if all newrecord objects already pushed to server.
            // No NewRecord rows on the local table. So not found NewRecord here.
            return Task.forResult(null);
        }

        return Task.forResult(objects.getFirst());
    }

    /**
     * **** Important ****
     * <p/>
     * Get first PFObject from the offline database.
     * <p/>
     * - parameter query: DBQuery's instance
     * <p/>
     * - returns: the first object's array,like [PFObject's instance].
     */
    public Task<ParseObject> getFirstLocalObjectArrayInBackground(DBQuery query) {
        // **** Important ****
        // If not found Parse's findLocalObjectsInBackground
        return ParseModelQuery.findFirstLocalObjectInBackground(query)
                .onSuccessTask(new Continuation<List<ParseObject>, Task<ParseObject>>() {
                    @Override
                    public Task<ParseObject> then(Task<List<ParseObject>> task) throws Exception {
                        return ParseModelQuery.getFirstParseObjectTask(task);
                    }
                });
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
    private static Task<List<ParseObject>> findFirstLocalObjectInBackground(DBQuery query) {
        // *** Important ***
        query.fromLocalDatastore();
        // *** Just get the first object.
        query.setLimit(1);

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
    public Task<Void> unpinInBackground(DBQuery query) {
        return this.getFirstLocalObjectArrayInBackground(query).onSuccessTask(new Continuation<ParseObject, Task<Void>>() {
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
        final DBQuery newRecordQuery = new NewRecord(this.getModelType(), ParseModelAbstract.getPoint(this)).createQueryForDeletedModel();

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
    public static Task<Void> unpinObjectInBackground(ParseObject object) {
        return object.unpinInBackground("Offline");
    }

}
