package com.ieatta.com.parse;


import com.ieatta.com.parse.engine.realm.DBObject;
import com.ieatta.com.parse.engine.realm.LocalQuery;
import com.ieatta.com.parse.models.NewRecord;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 12/21/15.
 */
public abstract class ParseModelLocalQuery extends ParseModelOnlineQuery {
    private ParseModelLocalQuery self = this;

    public ParseModelLocalQuery(String objectUUID) {
        super(objectUUID);
    }

    public ParseModelLocalQuery() {
        super();
    }

    public LocalQuery createQueryForBatching(List<String> points) {
        LocalQuery query = this.getLocalQueryInstance();
        query.orderByDescending(kPAPFieldObjectCreatedDateKey);

        query.whereContainedIn(kPAPFieldObjectUUIDKey, points);

        return query;
    }

    public LocalQuery createSearchDisplayNameForLocalQuery(String keyword) {
        LocalQuery query = this.makeLocalQuery();

        query.whereMatches(kPAPFieldDisplayNameKey, keyword, "i");

        return query;
    }


    public Task<List<ParseModelAbstract>> queryParseModels(PQueryModelType type, List<String> points) {
        return ParseModelLocalQuery.queryFromRealm(type, this.createQueryForBatching(points));
    }

    // =============================================================================
    //        offline Store
    // =============================================================================
    public Task<Void> saveInBackground() {
        return DBObject.pinInBackground(this);
    }

    public Task<Void> saveInBackgroundWithNewRecord() {
        return this.saveInBackground().onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                return getNewRecord().saveInBackground();
            }
        });
    }

    /**
     * Unpin the first offline object itself with NewRecord by query instance.
     * <p/>
     * - parameter deletedModel:    ParseModelAbstract's instance that want to delete
     */
    public Task<Void> unpinInBackgroundWithNewRecord() {
//        final LocalQuery newRecordQuery = new NewRecord(this.getModelType(), ParseModelAbstract.getPoint(this)).createQueryForDeletedModel();
//
//        return this.deleteInBackground()
//                .onSuccessTask(new Continuation<Void, Task<Void>>() {
//                    @Override
//                    public Task<Void> then(Task<Void> task) throws Exception {
//                        return deleteInBackground(newRecordQuery);
//                    }
//                });
        return Task.forResult(null);
    }

    @Override
    public Task<Void> updateLocalInBackground() {
        return self.deleteInBackground()
                .onSuccessTask(new Continuation<Void, Task<Void>>() {
                    @Override
                    public Task<Void> then(Task<Void> task) throws Exception {
                        return self.saveInBackground();
                    }
                });
    }

    protected LocalQuery getLocalQueryInstance() {
        LocalQuery query = LocalQuery.getDBQuery(this.getParseTableName(), self.getModelType());
        return query;
    }

    public LocalQuery makeLocalQuery() {
        LocalQuery query = this.getLocalQueryInstance();
        query.orderByDescending(kPAPFieldObjectCreatedDateKey);
        return query;
    }

    public LocalQuery createLocalQueryByUUID() {
        LocalQuery query = this.makeLocalQuery();

        query.whereEqualTo(kPAPFieldObjectUUIDKey, this.objectUUID);

        return query;
    }

    /**
     * Get count of objects on the offline datastore.
     * <p/>
     * - parameter query:           query's instance
     */
    public Task<Integer> countLocalObjects(LocalQuery query) {
        // *** Important ***
        query.fromLocalDatastore();

        return query.countInBackground();
    }

    /**
     * if not found the first object,
     * will return Exception: 'no results found for query'(code: com.parse.ParseException.OBJECT_NOT_FOUND)
     *
     * @param query
     * @return
     */
    protected static Task<List<ParseModelAbstract>> findFirstLocalObjectInBackground(LocalQuery query) {
        // *** Important ***
        query.fromLocalDatastore();
        // *** Just get the first object.
        query.setLimit(1);

        return query.findInBackground();
    }

    public static Task<List<ParseModelAbstract>> findInBackgroundFromRealm(LocalQuery query) {
        // *** Important ***
        query.fromLocalDatastore();

        return query.findInBackground();
    }

    public static Task<List<ParseModelAbstract>> queryFromRealm(final PQueryModelType type, final LocalQuery query) {
        return ParseModelLocalQuery.findInBackgroundFromRealm(query);
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
    public Task<ParseModelAbstract> getFirstLocalObjectArrayInBackground(LocalQuery query) {
        // **** Important ****
        // If not found Parse's findInBackgroundFromRealm
        return ParseModelLocalQuery.findFirstLocalObjectInBackground(query)
                .onSuccessTask(new Continuation<List<ParseModelAbstract>, Task<ParseModelAbstract>>() {
                    @Override
                    public Task<ParseModelAbstract> then(Task<List<ParseModelAbstract>> task) throws Exception {
                        return self.getFirstParseObjectTask(task);
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
    public static Task<ParseModelAbstract> getFirstParseObjectTask(Task<List<ParseModelAbstract>> previous) {
        LinkedList<ParseModelAbstract> objects = new LinkedList<>(previous.getResult());
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
     * Unpin the first offline object by query instance.
     * <p/>
     * - parameter query:           query's instance
     */
    public Task<Void> deleteInBackground() {
        LocalQuery query = this.createLocalQueryByUUID();
        return query.deleteInBackground();
    }

    /**
     * Query the offline table. and sort from oldest to newest.
     * <p/>
     * - returns: query's instance
     */
    public LocalQuery createQueryForPushObjectsToServer(int limit) {
        LocalQuery query = new LocalQuery(self.getParseTableName(), self.getModelType());
        query.setLimit(limit);

        // *** Important (used orderByAscending) ***
        query.orderByAscending(kPAPFieldObjectCreatedDateKey);

        return query;
    }


    public Task<ParseModelAbstract> getFirstLocalModelArrayTask() {
        return self.getFirstLocalObjectArrayInBackground(self.createLocalQueryByUUID());
    }

    public Task<ParseModelAbstract> getFirstModelTaskFromRealm() {
        LocalQuery query = self.createLocalQueryByUUID();
        // *** Important ***
        query.fromLocalDatastore();

        // *** Just get the first object.
        query.setLimit(1);

        return query.findFirstInBackground();
    }

    /**
     * This methond supported for the function,
     * that get the local first model to push to service.
     * And Resturant will override it.
     */
    protected Task<ParseModelAbstract> getFirstModelTask() {
        return self.getFirstModelTaskFromRealm();
    }

}
