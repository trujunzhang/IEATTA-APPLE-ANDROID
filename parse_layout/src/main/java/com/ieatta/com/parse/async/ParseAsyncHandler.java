package com.ieatta.com.parse.async;

import android.util.Log;
import android.yelp.com.commonlib.LogUtils;

import com.ieatta.com.parse.async.tasks.PullNewRecordFromServerTask;
import com.ieatta.com.parse.async.tasks.PushNewRecordToServerTask;
import com.ieatta.com.parse.models.NewRecord;

import java.util.logging.Logger;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 11/27/15.
 */
public class ParseAsyncHandler {

    private static final int PAGE_NUMBER_FETCH_NEW_RECORD = 10;
    private static final int PAGE_NUMBER_PUSH_NEW_RECORD = 2;

    public static final ParseAsyncHandler sharedInstance = new ParseAsyncHandler();

    private boolean didEndAsync = true;

    public void PullObjectsFromServer() {

        PullNewRecordFromServerTask.PullFromServerSeriesTask(new AsyncCacheInfo(AsyncCacheInfo.TAG_NEW_RECORD_DATE).createQuery(PAGE_NUMBER_FETCH_NEW_RECORD))
                .continueWith(new Continuation<Object, Object>() {
                    @Override
                    public Object then(Task<Object> task) throws Exception {
                        if (task.getError() != null) {
                            endAsyncTasks(task.getError());
                        } else {
                            PushLocalNewRecordToServer();
                        }
                        return null;
                    }
                });
    }

    private void PushLocalNewRecordToServer() {
        PushNewRecordToServerTask.PushToServerSeriesTask(new NewRecord().createQueryForPushObjectsToServer(PAGE_NUMBER_PUSH_NEW_RECORD))
                .continueWith(new Continuation<Object, Object>() {
                    @Override
                    public Object then(Task<Object> task) throws Exception {
                        if (task.getError() != null) {
                            endAsyncTasks(task.getError());
                        } else {
                            endAsyncTasks(null);
                        }
                        return null;
                    }
                });
    }

    private void endAsyncTasks(Exception error) {
        if (error != null) {
            LogUtils.debug("Error when async database:" + error.getLocalizedMessage());
        } else {
            LogUtils.debug("Async database task end sucessfully!");
        }

        this.didEndAsync = true;
    }


    /**
     * Start Point
     */
    public void executeParseAsyncHandler() {
        // Already have the same async handler.
        if (this.didEndAsync == false) {
            return;
        }

        // 1. Prepare tasks.
        this.didEndAsync = false;
        this.PullObjectsFromServer();
    }


}
