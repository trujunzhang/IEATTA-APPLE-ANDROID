package com.ieatta.com.parse;

import com.ieatta.com.parse.engine.realm.DBObject;
import com.ieatta.com.parse.engine.realm.LocalQuery;
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
 * Created by djzhang on 11/27/15.
 */
public abstract class ParseModelQuery extends ParseModelLocalQuery {
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
    public LocalQuery createQueryForPullObjectsFromServer(Date lastAsyncDate, int limit) {
        LocalQuery query = this.getDBQueryInstance();
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
//        ParseQuery query = this.getDBQueryInstance();
        ParseQuery query = ParseQuery.getQuery(self.getParseTableName());
        query.setLimit(limit);

        // *** Important (used orderByAscending) ***
        query.orderByAscending(kPAPFieldObjectCreatedDateKey);

        return query;
    }

    public LocalQuery createQueryByObjectUUID() {
        LocalQuery query = this.makeDBQuery();

        query.whereEqualTo(kPAPFieldObjectUUIDKey, this.objectUUID);

        return query;
    }

    public LocalQuery createQueryForBatching(List<String> points) {
        LocalQuery query = this.getDBQueryInstance();
        query.orderByDescending(kPAPFieldObjectCreatedDateKey);

        query.whereContainedIn(kPAPFieldObjectUUIDKey, points);

        return query;
    }

    public LocalQuery createSearchDisplayNameQuery(String keyword) {
        LocalQuery query = this.makeDBQuery();

        query.whereMatches(kPAPFieldDisplayNameKey, keyword, "i");

        return query;
    }

    static LocalQuery createQuery(PQueryModelType type, ParseModelAbstract model) {
        ParseModelQuery instance = (ParseModelQuery) ParseModelAbstract.getInstanceFromType(type, ParseModelAbstract.getPoint(model));
        return instance.createQueryByObjectUUID();
    }

    protected LocalQuery getDBQueryInstance() {
        LocalQuery query = LocalQuery.getDBQuery(this.getParseTableName(), self.getModelType());
        return query;
    }

    public LocalQuery makeDBQuery() {
        LocalQuery query = this.getDBQueryInstance();
        query.orderByDescending(kPAPFieldObjectCreatedDateKey);
        return query;
    }

    LocalQuery createQueryFromRecord() {
        LocalQuery query = this.getDBQueryInstance();

        // *** Import *** The newest row in the table.
        query.orderByDescending(kPAPFieldObjectCreatedDateKey);
        query.whereEqualTo(kPAPFieldObjectUUIDKey, this.objectUUID);

        return query;
    }

    protected Task<List<ParseModelAbstract>> queryParseModels(PQueryModelType type, List<String> points) {
        return ParseModelQuery.queryFromDatabase(type, this.createQueryForBatching(points));
    }

    @Override
    public Task<ParseModelAbstract> getFirstLocalModelArrayTask() {
        return self.getFirstLocalObjectArrayInBackground(this.createQueryByObjectUUID())
                .onSuccessTask(new Continuation<ParseObject, Task<ParseModelAbstract>>() {
                    @Override
                    public Task<ParseModelAbstract> then(Task<ParseObject> task) throws Exception {
                        return convertToLocalModelTask(task);
                    }
                });
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
     * - parameter query: LocalQuery's instance
     * <p/>
     * - returns: the first object's array,like [PFObject's instance].
     */
    public Task<ParseObject> getFirstLocalObjectArrayInBackground(LocalQuery query) {
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

        return DBObject.pinInBackground("Offline", object, this);
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
    public Task<Void> saveInBackgroundTask() {
        ParseObject object = makeObject();
        this.writeObject(object);

        return object.saveInBackground();
    }

    /**
     * Unpin the first offline object by query instance.
     * <p/>
     * - parameter query:           query's instance
     */
    public Task<Void> unpinInBackground(LocalQuery query) {
        return this.getFirstLocalObjectArrayInBackground(query).onSuccessTask(new Continuation<ParseObject, Task<Void>>() {
            @Override
            public Task<Void> then(Task<ParseObject> task) throws Exception {
                ParseObject object = task.getResult();
                if (object != null) {
                    return ParseModelQuery.unpinObjectInBackground(object, self);
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
        final LocalQuery newRecordQuery = new NewRecord(this.getModelType(), ParseModelAbstract.getPoint(this)).createQueryForDeletedModel();

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
    public static Task<Void> unpinObjectInBackground(ParseObject object, ParseModelAbstract model) {
        return DBObject.unpinInBackground("Offline", object, model);
    }

}
