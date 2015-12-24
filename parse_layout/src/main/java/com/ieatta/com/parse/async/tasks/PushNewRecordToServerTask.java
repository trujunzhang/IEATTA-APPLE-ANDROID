package com.ieatta.com.parse.async.tasks;

import android.yelp.com.commonlib.LogUtils;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.ParseModelQuery;
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

        // TODO: djzhang(fixing)
//        return ParseModelQuery.findInBackgroundFromRealm(query).onSuccessTask(new Continuation<List<ParseObject>, Task<Void>>() {
//            @Override
//            public Task<Void> then(Task<List<ParseObject>> task) throws Exception {
//                return executeSerialTasks(task);
//            }
//        });

        return Task.forResult(null);
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
        return PushObjectToServerTask(manager.next()).onSuccessTask(new Continuation() {
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
    private static Task PushObjectToServerTask(final ParseObject newRecordObject) {
        // Convert newRecordObject to Model instance.
        final NewRecord newRecord = (NewRecord) new NewRecord().convertToLocalModel(newRecordObject);

//        LogUtils.debug(" [ newRecord in push to server ]: " + newRecord.printDescription());

        // 1. Get the recoreded model instance from NewRecord, by the modelType and the modelPoint.
        // (such as photo,restaurant,event etc)
        final ParseModelAbstract model = newRecord.getRecordedModel();
//        LogUtils.debug(" [ model in push to server ]: " + model.printDescription());

        return model.pushToServer().onSuccessTask(new Continuation<Object, Task<Void>>() {
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
//                return ParseModelQuery.unpinObjectInBackground(newRecordObject,newRecord);
            }
        }).onSuccessTask(new Continuation<Void, Task<Boolean>>() {
            @Override
            public Task<Boolean> then(Task<Void> task) throws Exception {
                // For the specail photo here.
                return model.afterPushToServer();
            }
        });
    }


}
