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

    public ParseQuery createQueryFromRecord() {
        ParseQuery query = ParseQuery.getQuery(this.getParseTableName());

        // *** Import *** The newest row in the table.
        query.orderByDescending(kPAPFieldObjectCreatedDateKey);
        query.whereEqualTo(kPAPFieldObjectUUIDKey, this.objectUUID);

        return query;
    }

    public Task<List<ParseModelAbstract>> queryParseModels(PQueryModelType type, List<String> points) {
        return ParseModelQuery.queryFromRealm(type, this.createQueryForBatching(points));
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
    public Task<Void> saveInBackground() {
        ParseObject object = makeObject();
        this.writeLocalObject(object);

        return DBObject.pinInBackground("Offline", object, this);
    }


    public Task<Void> saveInBackgroundWithNewRecord() {
        return this.saveInBackground().onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                return getNewRecord().saveInBackground();
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
     * Unpin the first offline object itself with NewRecord by query instance.
     * <p/>
     * - parameter deletedModel:    ParseModelAbstract's instance that want to delete
     */
    public Task<Void> unpinInBackgroundWithNewRecord() {
        final LocalQuery newRecordQuery = new NewRecord(this.getModelType(), ParseModelAbstract.getPoint(this)).createQueryForDeletedModel();

        return this.deleteInBackground(this.createQueryByObjectUUID()).onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                return deleteInBackground(newRecordQuery);
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

    public Task<ParseModelAbstract> getFirstLocalModelArrayTask() {
        return self.getFirstLocalObjectArrayInBackground(self.createQueryByObjectUUID());
    }

    public Task<Void> updateLocalInBackground(){
        return self.deleteInBackground(self.createQueryByObjectUUID())
                .onSuccessTask(new Continuation<Void, Task<Void>>() {
                    @Override
                    public Task<Void> then(Task<Void> task) throws Exception {
                        return self.saveInBackground();
                    }
                });
    }
}
