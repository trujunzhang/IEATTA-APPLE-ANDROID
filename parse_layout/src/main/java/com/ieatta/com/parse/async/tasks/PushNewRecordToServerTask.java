package com.ieatta.com.parse.async.tasks;

import android.util.Log;
import android.yelp.com.commonlib.LogUtils;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.ParseModelQuery;
import com.ieatta.com.parse.models.NewRecord;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Collection;
import java.util.List;
import java.util.List;
import java.util.Objects;

import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;

/**
 * Created by djzhang on 11/30/15.
 */
public class PushNewRecordToServerTask {

    public static Task PushToServerSeriesTask(ParseQuery query) {
        return ParseModelQuery.findLocalObjectsInBackground(query).onSuccessTask(new Continuation<List<ParseObject>, Task<Void>>() {
            @Override
            public Task<Void> then(Task<List<ParseObject>> task) throws Exception {
                List<ParseObject> results = task.getResult();
                LogUtils.debug("{ count in Pull objects from Server }: " + results.size());

                // Create a trivial completed task as a base case.
                Task<Void> singleTask = Task.forResult(null);
                for (final ParseObject result : results) {
                    // For each item, extend the task with a function to delete the item.
                    singleTask = singleTask.continueWithTask(new Continuation<Void, Task<Void>>() {
                        public Task<Void> then(Task<Void> ignored) throws Exception {
                            // Return a task that will be marked as completed when the delete is finished.
                            return PushObjectToServerTask(result);
                        }
                    });
                }

                return singleTask;
            }
        });
    }

    /**
     * Push all offline objects to Parse.com.
     * <p/>
     * - parameter newRecordObject: A row data on the NewRecord table.
     */
    private static Task PushObjectToServerTask(final ParseObject newRecordObject) {
        // Convert newRecordObject to Model instance.
        NewRecord newRecord = (NewRecord) new NewRecord().convertToLocalModel(newRecordObject);

        LogUtils.debug(" { newRecord in push to server }: " + newRecord.printDescription());

        // 1. Get the recoreded model instance from NewRecord, by the modelType and the modelPoint.
        // (such as photo,restaurant,event etc)
        final ParseModelAbstract model = newRecord.getRecordedModel();
        LogUtils.debug(" { model in push to server }: " + model.printDescription());

        return model.pushToServer().onSuccessTask(new Continuation<Object, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Object> task) throws Exception {
                // Save NewRecord to Parse.com
                return newRecordObject.saveInBackground();
            }
        }).onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                // Unpin the offline NewRecord
                return ParseModelQuery.unpinObjectInBackground(newRecordObject);
            }
        }).onSuccessTask(new Continuation<Void, Task<Boolean>>() {
            @Override
            public Task<Boolean> then(Task<Void> task) throws Exception {
                // For the specail photo here.
                return model.eventAfterPushToServer();
            }
        });
    }


}
