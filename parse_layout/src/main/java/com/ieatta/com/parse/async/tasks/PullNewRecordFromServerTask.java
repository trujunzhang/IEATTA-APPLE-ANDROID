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


    public static Task<Object> PullFromServerSeriesTask(ParseQuery query) {
        final Task.TaskCompletionSource tcs = Task.create();

        query.findInBackground().onSuccessTask(new Continuation<List<ParseObject>,Void>() {
            @Override
            public Void then(Task<List<ParseObject>> task) throws Exception {
                List<ParseObject> results =  task.getResult();
                LogUtils.debug("Pull objects from Server: "+results.size());

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

                return null;
            }
        }).onSuccess(new Continuation() {
            @Override
            public Object then(Task task) throws Exception {
                tcs.setResult(true);
                return null;
            }
        }).continueWith(new Continuation() {
            @Override
            public Object then(Task task) throws Exception {
                if (task.isFaulted()) {
                    tcs.setError(task.getError());
                }
                return null;
            }
        });

//        query.findInBackground().continueWithTask(new Continuation() {
//            @Override
//            public Object then(Task task) throws Exception {
//                if (task.getError() != null) {
//                    TaskCompletionSource finalTask = new TaskCompletionSource();
//                    finalTask.setError(task.getError());
//                    return finalTask;
//                }
//                List<ParseObject> results = (List<ParseObject>) task.getResult();
//                LogUtils.debug("Pull objects from Server: "+results.size());
//
//                // Create a trivial completed task as a base case.
//                Task<Void> _task = Task.forResult(null);
//                for (final ParseObject result : results) {
//                    // For each item, extend the task with a function to delete the item.
//                    _task = _task.continueWithTask(new Continuation<Void, Task<Void>>() {
//                        public Task<Void> then(Task<Void> ignored) throws Exception {
//                            // Return a task that will be marked as completed when the delete is finished.
//                            return PullObjectFromServerTask(result);
//                        }
//                    });
//                }
//
//                return null;
//            }
//        }).continueWith(new Continuation() {
//            @Override
//            public Object then(Task task) throws Exception {
//                // Every offline objects was pushed to Parse.com.
//                if (task.getError() != null) {
//                    seriesTask.setError(task.getError());
//                } else {
//                    seriesTask.setResult(true);
//                }
//                return null;
//            }
//        });

        return tcs.getTask();
    }


    /**
     * Pull online objects from Parse.com.
     * <p/>
     * - parameter pulledNewRecordObject: A row data on the NewRecord table.
     */
    private static Task PullObjectFromServerTask(ParseObject pulledNewRecordObject) {
        final TaskCompletionSource pullTask = new TaskCompletionSource();
        final Date lastRecordCreateAt = pulledNewRecordObject.getCreatedAt();

        // 1. Create model instance from record's modelType.
        final ParseModelAbstract model = NewRecord.getRecordedInstance(pulledNewRecordObject);
        LogUtils.debug("NewRecord from parse.com: " + model.printDescription());

        // 2. Pull from server.
        model.pullFromServerAndPin().continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
                if (task.getError() != null) {
                    pullTask.setError(task.getError());
                } else {

                    /// 1. Update last synched date.
                    new AsyncCacheInfo(AsyncCacheInfo.TAG_NEW_RECORD_DATE).storeNewRecordDate(lastRecordCreateAt);

                    /// 2. When pull from server successfully, sometimes need to notify have new parse models.
                    //  AsyncPullNotify.notify(model);

                    /// 3. Next task.
                    pullTask.setResult(true);

                }
                return null;
            }
        });

        return pullTask.getTask();
    }

}
