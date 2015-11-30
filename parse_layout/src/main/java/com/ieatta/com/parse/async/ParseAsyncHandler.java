package com.ieatta.com.parse.async;

import com.ieatta.com.parse.async.tasks.PullNewRecordFromServerTask;
import com.ieatta.com.parse.async.tasks.PushNewRecordToServerTask;
import com.ieatta.com.parse.models.NewRecord;

/**
 * Created by djzhang on 11/27/15.
 */
public class ParseAsyncHandler {

    private static final int  PAGE_NUMBER_FETCH_NEW_RECORD = 10;
    private static final int  PAGE_NUMBER_PUSH_NEW_RECORD  = 2;

    public static final ParseAsyncHandler sharedInstance = new ParseAsyncHandler();


    private boolean didEndAsync = true;

    private void PullObjectsFromServer(){

        PullNewRecordFromServerTask.PullFromServerSeriesTask(new  AsyncCacheInfo(AsyncCacheInfo.TAG_NEW_RECORD_DATE).createQuery(PAGE_NUMBER_FETCH_NEW_RECORD)).continueWithBlock { (task) -> AnyObject? in

            if let _error = task.error{
                self.endAsyncTasks(_error)
            }else{
                self.PushLocalNewRecordToServer()
            }

            return nil
        }
    }

    private void PushLocalNewRecordToServer(){
        PushNewRecordToServerTask.PushToServerSeriesTask(new NewRecord().createQueryForPushObjectsToServer(PAGE_NUMBER_PUSH_NEW_RECORD)).continueWithBlock { (task) -> AnyObject? in

            if let _error = task.error{
                self.endAsyncTasks(_error)
            }else{
                self.endAsyncTasks()
            }

            return nil
        }
    }

    private void endAsyncTasks(Error error){
        if(error != null){
//            print("Error when async database: \(_error.userInfo)");
        }else {
//            print("Async database task end sucessfully!");
        }

        this.didEndAsync = true;
    }


    /**
     Start Point
     */
    public void executeParseAsyncHandler(){
        // Already have the same async handler.
        if(this.didEndAsync == false){
            return;
        }

        // 1. Prepare tasks.
        this.didEndAsync = false;
        this.PullObjectsFromServer();
    }




}
