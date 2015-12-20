package com.ieatta.com.parse.async.tasks;

import android.yelp.com.commonlib.LogUtils;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.async.AsyncCacheInfo;
import com.ieatta.com.parse.async.SerialTasksManager;
import com.ieatta.com.parse.engine.realm.DBQuery;
import com.ieatta.com.parse.models.NewRecord;
import com.parse.ParseObject;

import java.util.Date;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 11/30/15.
 */
public class PullNewRecordFromServerTask {
    private PullNewRecordFromServerTask self = this;

    public static Task PullFromServerSeriesTask(DBQuery query) {

        return query.findInBackground().onSuccessTask(new Continuation() {
            @Override
            public Object then(Task task) throws Exception {
                return executeSerialTasks(task);
            }
        });
    }

    private static Task executeSerialTasks(Task previous) {
        List<ParseObject> results = (List<ParseObject>) previous.getResult();
        LogUtils.debug("{ count in Pull objects from Server }: " + results.size());

        SerialTasksManager manager = new SerialTasksManager(results);
        if (manager.hasNext() == false) {
            return Task.forResult(true);
        }

        return startPullFromServerSingleTask(manager);
    }

    private static Task startPullFromServerSingleTask(final SerialTasksManager manager) {
        return PullObjectFromServerTask(manager.next()).onSuccessTask(new Continuation() {
            @Override
            public Object then(Task task) throws Exception {
                if (manager.hasNext() == false) {
                    return Task.forResult(true);
                }
                return startPullFromServerSingleTask(manager);
            }
        });
    }

    /**
     * Pull online objects from Parse.com.
     * <p/>
     * - parameter pulledNewRecordObject: A row data on the NewRecord table.
     */
    private static Task PullObjectFromServerTask(ParseObject pulledNewRecordObject) {
        final Date lastRecordCreateAt = pulledNewRecordObject.getCreatedAt();

        // 1. Create model instance from record's modelType.
        final ParseModelAbstract model = NewRecord.getRecordedInstance(pulledNewRecordObject);
//        LogUtils.debug(" [NewRecord from parse.com]: " + model.printDescription());

        // 2. Pull from server.
        return model.pullFromServerAndPin().onSuccess(new Continuation<Void, Void>() {
            @Override
            public Void then(Task<Void> task) throws Exception {

                /// 1. Update last synched date.
                new AsyncCacheInfo(AsyncCacheInfo.TAG_NEW_RECORD_DATE).storeNewRecordDate(lastRecordCreateAt);

                /// 2. When pull from server successfully, sometimes need to notify have new parse models.
                //  AsyncPullNotify.notify(model);
                return null;
            }
        });

    }

}
