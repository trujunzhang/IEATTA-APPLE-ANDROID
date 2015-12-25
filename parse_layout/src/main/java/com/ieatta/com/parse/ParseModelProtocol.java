package com.ieatta.com.parse;

import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.parse.ParseACL;
import com.parse.ParseObject;

import bolts.Task;

/**
 * Created by djzhang on 11/27/15.
 */
public interface ParseModelProtocol {

    //NewRecord getNewRecord();

    PQueryModelType getModelType();

    String getParseTableName();

    // MARK: Save/Pin object methonds.
    void writeCommonObject(ParseObject object);

    void writeObject(ParseObject object);

    void writeLocalObject(ParseObject object);

    void readCommonObject(ParseObject object);

    void readObject(ParseObject object);

    void readObjectLocal(ParseObject object);

    ParseACL getACL();

    // MARK: Async methods.
    /**
     * Step1: getFirstLocalObject
     * Step2: convertToLocalModel
     * Step3: saveInBackground(on the parse.com);
     * <p/>
     * - returns: task's instance
     */
    Task<Object> pushToServer();

    Task beforePullFromServer();

    Task<Boolean> afterPushToServer();

    Task<Void> pullFromServerAndPin();

    /**
     * Get the offline models array.
     * <p/>
     * - returns: task's instance, task's result is [ParseModelAbstract]
     */
    //Task<ParseModelAbstract> getFirstLocalModelArrayTask();

    Task<ParseModelAbstract> queryBelongToTask(ParseModelAbstract belongTo);

    Task<Void> updateLocalInBackground();
}
