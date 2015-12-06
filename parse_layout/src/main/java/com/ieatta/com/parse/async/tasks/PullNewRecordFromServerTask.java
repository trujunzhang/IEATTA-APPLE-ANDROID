package com.ieatta.com.parse.async.tasks;

import android.util.Log;
import android.yelp.com.commonlib.LogUtils;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.async.AsyncCacheInfo;
import com.ieatta.com.parse.async.AsyncPullNotify;
import com.ieatta.com.parse.models.NewRecord;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;

/**
 * Created by djzhang on 11/30/15.
 */
public class PullNewRecordFromServerTask {


    public static Task<Void> PullFromServerSeriesTask(ParseQuery query) {

        return query.findInBackground().onSuccessTask(new Continuation<List<ParseObject>, Task<Void>>() {
            @Override
            public Task<Void> then(Task<List<ParseObject>> task) throws Exception {
                List<ParseObject> results = task.getResult();
                LogUtils.debug("Pull objects from Server: " + results.size());

                // Create a trivial completed task as a base case.
                Task<Void> singleTask = Task.forResult(null);
                for (final ParseObject result : results) {
                    // For each item, extend the task with a function to delete the item.
                    singleTask = singleTask.continueWithTask(new Continuation<Void, Task<Void>>() {
                        public Task<Void> then(Task<Void> ignored) throws Exception {
                            // Return a task that will be marked as completed when the delete is finished.
                            return PullObjectFromServerTask(result);
                        }
                    });
                }

                return singleTask;
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
        LogUtils.debug("{ NewRecord from parse.com }: " + model.printDescription());

        // 2. Pull from server.
        return model.pullFromServerAndPin().onSuccess(new Continuation<Void, Void>() {
            @Override
            public Void then(Task<Void> task) throws Exception {

                /// 1. Update last synched date.
                new AsyncCacheInfo(AsyncCacheInfo.TAG_NEW_RECORD_DATE).storeNewRecordDate(lastRecordCreateAt);

                /// 2. When pull from server successfully, sometimes need to notify have new parse models.
                //  AsyncPullNotify.notify(model);

                /// 3. Next task.
                return null;
            }
        });

    }

}
