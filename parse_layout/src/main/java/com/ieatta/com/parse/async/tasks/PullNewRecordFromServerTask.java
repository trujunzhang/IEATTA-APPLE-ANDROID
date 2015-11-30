package com.ieatta.com.parse.async.tasks;

import com.ieatta.com.parse.models.NewRecord;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Collection;
import java.util.LinkedList;

import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;

/**
 * Created by djzhang on 11/30/15.
 */
public class PullNewRecordFromServerTask {


    public static Task<Object> PullFromServerSeriesTask(ParseQuery query) {
        TaskCompletionSource seriesTask = new TaskCompletionSource();

        query.findInBackground().continueWithTask(new Continuation() {
            @Override
            public Object then(Task task) throws Exception {
                if (task.getError() != null) {
                    TaskCompletionSource finalTask = new TaskCompletionSource();
                    finalTask.setError(task.getError());
                    return finalTask;
                }
                LinkedList<Object> results = new LinkedList<Object>((Collection<?>) task.getResult());
//                print("Pull objects from Server: \(results.count)");


                return null;
            }
        });

//        query.findObjectsInBackground().continueWithBlock {
//            (task: BFTask!) -> BFTask in
//            // When network is not avaiable, will return error.
//            if let _error = task.error{
//                return BFTask(error:_error)
//            }
//
//            let results = task.result as! [PFObject]
//            print("Pull objects from Server: \(results.count)")
//            // Create a trivial completed task as a base case.
//            var task = BFTask(result:nil)
//            for result : PFObject in results {
//                // For each item, extend the task with a function to delete the item.
//                task = task.continueWithBlock {
//                    (task: BFTask!) -> BFTask in
//                    return self.PullObjectFromServerTask(result)
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

        return seriesTask.getTask();
    }


    /**
     Pull online objects from Parse.com.

     - parameter pulledNewRecordObject: A row data on the NewRecord table.
     */
    private static Task PullObjectFromServerTask(ParseObject pulledNewRecordObject) {
//        let pullTask = BFTaskCompletionSource()
//
//        let lastRecordCreateAt = pulledNewRecordObject.createdAt!
//
//                // 1. Create model instance from record's modelType.
//                let model = NewRecord.getRecordedInstance(pulledNewRecordObject)
//
//        //        print("NewRecord from parse.com: \(model.printDescription())")
//
//        // 2. Pull from server.
//        model.pullFromServerAndPin()
//                .continueWithBlock { (task) -> AnyObject? in
//            if let _error = task.error{
//                pullTask.setError(_error)
//            }else{
//                /// 1. Update last synched date.
//                AsyncCacheInfo(key: TAG_NEW_RECORD_DATE).storeNewRecordDate(lastRecordCreateAt)
//
//                /// 2. When pull from server successfully, sometimes need to notify have new parse models.
//                AsyncPullNotify.notify(model)
//
//                /// 3. Next task.
//                pullTask.setResult(true)
//            }
//            return nil
//        }

//        return pullTask.task
        return null;
    }

}
