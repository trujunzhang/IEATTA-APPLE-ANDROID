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

        return ParseModelLocalQuery.findInBackgroundFromRealm(query)
                .onSuccessTask(new Continuation<List<ParseModelAbstract>, Task<Void>>() {
                    @Override
                    public Task<Void> then(Task<List<ParseModelAbstract>> task) throws Exception {
                        return executeSerialTasks(task);
                    }
                });
    }

    private static Task executeSerialTasks(Task<List<ParseModelAbstract>> previous) {
        List<ParseModelAbstract> results = previous.getResult();
        LogUtils.debug("{ count in Push objects to Server }: " + results.size());

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
        LogUtils.debug(" [ newRecord in push to server ]: " + newRecord.printDescription());

        // 1. Get the recoreded emptyModel instance from NewRecord, by the modelType and the modelPoint.
        // (such as photo,restaurant,event etc)
        final ParseModelAbstract emptyModel = newRecord.getRecordedModel();
        LogUtils.debug(" [ emptyModel in push to server ]: " + emptyModel.printDescription());

        // Step1: Get the first model by the emptyModel's uuid.
        //        And Save it's ParseObject to Parse.com.
        return emptyModel.pushToServer()
                .onSuccessTask(new Continuation<Void, Task<Void>>() {
                    @Override
                    public Task<Void> then(Task<Void> task) throws Exception {
                        // Step2: Save the newRecord's ParseObject to Parse.com
                        return newRecord.saveParseObjectToServer();
                    }
                }).onSuccessTask(new Continuation() {
                    @Override
                    public Object then(Task task) throws Exception {
                        // Step3: Delete the newRrecord on the localdate.
                        return newRecord.deleteInBackground();
                    }
                }).onSuccessTask(new Continuation() {
                    @Override
                    public Object then(Task task) throws Exception {
                        // For the specail photo here.
                        // Finally, Post some events.
                        return emptyModel.afterPushToServer();
                    }
                });

    }

}
