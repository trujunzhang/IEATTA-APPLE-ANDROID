package com.ieatta.com.parse;

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
    public Task<Object> pullFromServerAndPin() {
        // 1. Retrieve object from parse.com.
        return this.createQueryFromRecord().getFirstInBackground().continueWith(new Continuation() {
            @Override
            public Object then(Task task) throws Exception {
                // 2.1 Convert to the online Model.
                return convertToOnlineModelTask(task);
            }
        }).continueWith(new Continuation() {
            @Override
            public Object then(Task task) throws Exception {
                return pinAfterPullFromServer();
            }
        });
    }

    /**
     * Pull Fetched online object to offline database.
     * If offline already exist, delete it first.
     */
    @Override
    public Task<Object> pinAfterPullFromServer() {
        // 1. Check wheather exist.
        final ParseQuery query = ParseModelQuery.createQuery(this.getModelType(), this);

        return this.eventAfterPushToServer().continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
                return unpinInBackground(query);
            }
        }).continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
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
