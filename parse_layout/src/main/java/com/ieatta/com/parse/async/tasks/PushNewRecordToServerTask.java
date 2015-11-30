package com.ieatta.com.parse.async.tasks;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.ParseModelQuery;
import com.ieatta.com.parse.models.NewRecord;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;

/**
 * Created by djzhang on 11/30/15.
 */
public class PushNewRecordToServerTask {



    public static Task PushToServerSeriesTask(ParseQuery query) {

//        let seriesTask = BFTaskCompletionSource()
//
//        ParseModelQuery.findLocalObjectsInBackground(query).continueWithBlock {
//            (task: BFTask!) -> BFTask in
//            let results = task.result as! [PFObject]
//            print("Push objects to Server: \(results.count)")
//            // Create a trivial completed task as a base case.
//            var task = BFTask(result:nil)
//            for result : PFObject in results {
//                // For each item, extend the task with a function to delete the item.
//                task = task.continueWithBlock {
//                    (task: BFTask!) -> BFTask in
//                    return self.PushObjectToServerTask(result)
//                }
//            }
//            return task
//        }.continueWithBlock {
//            (task: BFTask!) -> AnyObject! in
//            // Every offline objects was pushed to Parse.com.
//            if let _error = task.error{
//                seriesTask.setError(_error)
//            }else{
//                seriesTask.setResult(true)
//            }
//            return nil
//        }

//        return seriesTask.task
        return null;
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
