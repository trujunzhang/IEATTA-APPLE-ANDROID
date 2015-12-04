package com.ieatta.com.parse.async.tasks;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.ParseModelQuery;
import com.ieatta.com.parse.models.NewRecord;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Collection;
import java.util.List;
import java.util.List;

import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;

/**
 * Created by djzhang on 11/30/15.
 */
public class PushNewRecordToServerTask {

    public static Task PushToServerSeriesTask(ParseQuery query) {
        final TaskCompletionSource seriesTask = new TaskCompletionSource();

        ParseModelQuery.findLocalObjectsInBackground(query).continueWith(new Continuation<List<ParseObject>, Object>() {
            @Override
            public Object then(Task<List<ParseObject>> task) throws Exception {
                if (task.getError() != null) {
                    TaskCompletionSource finalTask = new TaskCompletionSource();
                    finalTask.setError(task.getError());
                    return finalTask;
                }
                List<ParseObject> results = new List<ParseObject>((Collection<? extends ParseObject>) task.getResult());
//                print("Push objects to Server: \(results.count)")

                // Create a trivial completed task as a base case.
                Task<Void> _task = Task.forResult(null);
                for (final ParseObject result : results) {
                    // For each item, extend the task with a function to delete the item.
                    _task = _task.continueWithTask(new Continuation<Void, Task<Void>>() {
                        public Task<Void> then(Task<Void> ignored) throws Exception {
                            // Return a task that will be marked as completed when the delete is finished.
                            return PushObjectToServerTask(result);
                        }
                    });
                }

                return null;
            }
        }).continueWith(new Continuation() {
            @Override
            public Object then(Task task) throws Exception {
                // Every offline objects was pushed to Parse.com.
                if (task.getError() != null) {
                    seriesTask.setError(task.getError());
                } else {
                    seriesTask.setResult(true);
                }
                return null;
            }
        });

        return seriesTask.getTask();
    }

    /**
     Push all offline objects to Parse.com.

     - parameter newRecordObject: A row data on the NewRecord table.
     */
    private static Task PushObjectToServerTask(final ParseObject newRecordObject) {
        // Convert newRecordObject to Model instance.
        NewRecord newRecord = (NewRecord) new NewRecord().convertToLocalModel(newRecordObject);

//        print("push to server: \(newRecord.printDescription())")

        // 1. Get the recoreded model instance from NewRecord, by the modelType and the modelPoint.
        // (such as photo,restaurant,event etc)
        final ParseModelAbstract model = newRecord.getRecordedModel();
//        print("push to server: \(model.printDescription())")

        return model.pushToServer().continueWith(new Continuation() {
            @Override
            public Object then(Task task) throws Exception {

                if (task.getError() != null) {
                    TaskCompletionSource finalTask = new TaskCompletionSource();
                    finalTask.setError(task.getError());
                    return finalTask;
                }

                // Save NewRecord to Parse.com
                return newRecordObject.saveInBackground();
            }
        }).continueWith(new Continuation() {
            @Override
            public Object then(Task task) throws Exception {

                // Unpin the offline NewRecord
                return ParseModelQuery.unpinObjectInBackground(newRecordObject);
            }
        }).continueWith(new Continuation() {
            @Override
            public Object then(Task task) throws Exception {
                // For the specail photo here.
                return model.eventAfterPushToServer();
            }
        });
    }



}
