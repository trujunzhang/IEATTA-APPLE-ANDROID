package com.ieatta.com.parse;

import com.ieatta.com.parse.engine.realm.DBObject;
import com.ieatta.com.parse.engine.realm.DBQuery;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 11/27/15.
 */
public abstract class ParseModelSync extends ParseModelQuery {
    private ParseModelSync self = this;

    public ParseModelSync(String objectUUID) {
        super(objectUUID);
    }

    public ParseModelSync() {
        super();
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
                .onSuccessTask(new Continuation<DBObject, Task<Boolean>>() {
                    @Override
                    public Task<Boolean> then(Task<DBObject> task) throws Exception {
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
                        // 1. Check wheather exist.
                        return unpinInBackground(self.createQueryByObjectUUID());
                    }
                }).onSuccessTask(new Continuation<Void, Task<Void>>() {
                    @Override
                    public Task<Void> then(Task<Void> task) throws Exception {
//                if ("67499179-6AA8-4C5A-B59E-F5B2D0B57302".equals(ParseModelAbstract.getPoint(self))) {
//                    return Task.forError(new NullPointerException("djzhang's pinAfterPullFromServer"));
//                }
                        return pinInBackgroundForModel();
                    }
                });
    }

    /**
     * Pull the offline model to Parse.com
     */
    @Override
    public Task<Object> pushToServer() {
        return this.getFirstLocalModelArrayTask().continueWith(new Continuation<ParseModelAbstract, Object>() {
            @Override
            public Object then(Task<ParseModelAbstract> task) throws Exception {
                return saveInBackgroundTask(); //(on the parse.com)
            }
        });
    }


}
