package com.ieatta.com.parse.async.tasks;

import android.yelp.com.commonlib.LogUtils;

import com.ieatta.com.parse.ParseModelAbstract;

import com.ieatta.com.parse.ParseModelLocalQuery;
import com.ieatta.com.parse.async.SerialTasksManager;
import com.ieatta.com.parse.engine.realm.LocalQuery;
import com.ieatta.com.parse.models.NewRecord;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 11/30/15.
 */
public class PushNewRecordToServerTask {

    public static Task PushToServerSeriesTask(LocalQuery query) {

        return ParseModelLocalQuery.findInBackgroundFromRealm(query).onSuccessTask(new Continuation<List<ParseModelAbstract>, Task>() {
            @Override
            public Task then(Task<List<ParseModelAbstract>> task) throws Exception {
                return executeSerialTasks(task);
            }
        });
    }

    private static Task executeSerialTasks(Task<List<ParseModelAbstract>> previous) {
        List<ParseModelAbstract> results = previous.getResult();
        LogUtils.debug("{ count in Pull objects from Server }: " + results.size());

        SerialTasksManager<ParseModelAbstract> manager = new SerialTasksManager<>(results);
        if (manager.hasNext() == false) {
            return Task.forResult(true);
        }

        return startPullFromServerSingleTask(manager);
    }

    private static Task startPullFromServerSingleTask(final SerialTasksManager<ParseModelAbstract> manager) {
        return PushObjectToServerTask((NewRecord) manager.next())
                .onSuccessTask(new Continuation() {
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
     * Push all offline objects to Parse.com.
     * <p/>
     * - parameter newRecordObject: A row data on the NewRecord table.
     */
    private static Task PushObjectToServerTask(final NewRecord newRecord) {
//        LogUtils.debug(" [ newRecord in push to server ]: " + newRecord.printDescription());

        // 1. Get the recoreded emptyModel instance from NewRecord, by the modelType and the modelPoint.
        // (such as photo,restaurant,event etc)
        final ParseModelAbstract emptyModel = newRecord.getRecordedModel();
//        LogUtils.debug(" [ emptyModel in push to server ]: " + emptyModel.printDescription());



        return emptyModel.pushToServer().onSuccessTask(new Continuation<Object, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Object> task) throws Exception {
                // Save NewRecord to Parse.com
                return newRecordObject.saveInBackground();
            }
        }).onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                // TODO: djzhang:(fixing)
                return Task.forResult(null);
                // Unpin the offline NewRecord
//                return ParsexModelQuery.unpinObjectInBackground(newRecordObject,newRecord);
            }
        }).onSuccessTask(new Continuation<Void, Task<Boolean>>() {
            @Override
            public Task<Boolean> then(Task<Void> task) throws Exception {
                // For the specail photo here.
                return emptyModel.afterPushToServer();
            }
        });
    }


}
