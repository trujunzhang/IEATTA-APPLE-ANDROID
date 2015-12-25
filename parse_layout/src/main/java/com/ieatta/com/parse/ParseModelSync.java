package com.ieatta.com.parse;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 11/27/15.
 */
public abstract class ParseModelSync extends ParseModelLocalQuery {
    private ParseModelSync self = this;

    public ParseModelSync(String objectUUID) {
        super(objectUUID);
    }

    public ParseModelSync() {
        super();
    }

    public ParseQuery createQueryFromRecord() {
        ParseQuery query = ParseQuery.getQuery(this.getParseTableName());

        // *** Import *** The newest row in the table.
        query.orderByDescending(kPAPFieldObjectCreatedDateKey);
        query.whereEqualTo(kPAPFieldObjectUUIDKey, this.objectUUID);

        return query;
    }

    /**
     * Pull a model(such as restaurant, photo etc) from Parse.com. The model's objectUUID recorded in the NewRecord's modelPoint.
     * So retrieve the first online object by objectUUID, Found it then save it to offline database.
     * <p/>
     * if not found, must return Result.Failure().
     */
    @Override
    public Task<Void> pullFromServerAndPin() {
        // 1. Retrieve object from parse.com.
        return this.createQueryFromRecord().getFirstInBackground()
                .onSuccessTask(new Continuation<ParseObject, Task<Boolean>>() {
                    @Override
                    public Task<Boolean> then(Task<ParseObject> task) throws Exception {
                        return convertToOnlineModelTask(task);
                    }
                }).onSuccessTask(new Continuation<Boolean, Task<Void>>() {
                    @Override
                    public Task<Void> then(Task<Boolean> task) throws Exception {
                        return self.beforePullFromServer();
                    }
                }).onSuccessTask(new Continuation<Void, Task<Void>>() {
                    @Override
                    public Task<Void> then(Task<Void> task) throws Exception {
                        // 1. Check wheather exist, and update it.
                        return updateLocalInBackground();
                    }
                });
    }

    /**
     * Pull the offline model to Parse.com
     */
    @Override
    public Task<Void> pushToServer() {
        // Step1: Get the first model by the emptyModel's uuid.
        return self.getFirstModelTask()
                .onSuccessTask(new Continuation() {
                    @Override
                    public Object then(Task task) throws Exception {
                        ParseModelAbstract backModel = (ParseModelAbstract) task.getResult();
                        if (backModel != null) {
                            return backModel.saveParseObjectToServer();//(on the parse.com)
                        }
                        return Task.forError(new NullPointerException("Not found the first Model!"));
                    }
                });
    }


}
