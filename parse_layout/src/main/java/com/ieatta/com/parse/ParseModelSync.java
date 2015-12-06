package com.ieatta.com.parse;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 11/27/15.
 */
public abstract class ParseModelSync extends ParseModelQuery {
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
        return ParseModelQuery.getFirstOnlineObjectTask(this.createQueryFromRecord()).onSuccessTask(new Continuation<ParseObject, Task<Boolean> >() {
            @Override
            public Task<Boolean>  then(Task<ParseObject> task) throws Exception {
                return convertToOnlineModelTask(task);
            }
        }).onSuccessTask(new Continuation<Boolean, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Boolean> task) throws Exception {
                return pinAfterPullFromServer();
            }
        });
    }

    /**
     * Pull Fetched online object to offline database.
     * If offline already exist, delete it first.
     */
    @Override
    public Task<Void> pinAfterPullFromServer() {
        // 1. Check wheather exist.
        final ParseQuery query = ParseModelQuery.createQuery(this.getModelType(), this);

        return this.eventAfterPushToServer().onSuccessTask(new Continuation<Boolean, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Boolean> task) throws Exception {
                return unpinInBackground(query);
            }
        }).onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
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
