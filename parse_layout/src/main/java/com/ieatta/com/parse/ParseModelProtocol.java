package com.ieatta.com.parse;

import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.parse.ParseACL;
import com.ieatta.com.parse.engine.realm.DBObject;

import bolts.Task;

/**
 * Created by djzhang on 11/27/15.
 */
public interface ParseModelProtocol {

//    public NewRecord getNewRecord();

    public PQueryModelType getModelType();

    public String getParseTableName();

    // MARK: Save/Pin object methonds.
    public void writeCommonObject(DBObject object);

    public void writeObject(DBObject object);

    public void writeLocalObject(DBObject object);

    public void readCommonObject(DBObject object);

    public void readObject(DBObject object);

    public void readObjectLocal(DBObject object);

    public ParseACL getACL();

    // MARK: Async methods.

    /**
     * Step1: getFirstLocalObject
     * Step2: convertToLocalModel
     * Step3: saveInBackground(on the parse.com);
     * <p/>
     * - returns: task's instance
     */
    public Task<Object> pushToServer();

    public Task beforePullFromServer();

    public Task<Boolean> afterPushToServer();

    public Task<Void> pullFromServerAndPin();

    /**
     * Get the offline models array.
     * <p/>
     * - returns: task's instance, task's result is [ParseModelAbstract]
     */
    public Task<ParseModelAbstract> getFirstLocalModelArrayTask();

    public Task<Boolean> queryBelongToTask(ParseModelAbstract belongTo);
}
