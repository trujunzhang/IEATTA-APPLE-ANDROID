package com.ieatta.com.parse.models;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.ParseModelConvert;
import com.ieatta.com.parse.ParseModelSync;
import com.ieatta.com.parse.engine.realm.LocalQuery;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.parse.ParseObject;

import java.util.Date;

import bolts.Task;

/**
 * Created by djzhang on 11/27/15.
 */
public class NewRecord extends ParseModelSync {
    // Class key
    private static final String kPAPClassKey = "NewRecord";

    // Field keys
    private static final String kPAPFieldModelTypeKey = "modelType";
    private static final String kPAPFieldModelPointKey = "modelPoint";

    public PQueryModelType modelType = PQueryModelType.unkown;
    public String modelPoint = "";

    public NewRecord() {
        super();
    }

    public NewRecord(PQueryModelType modelType, String modelPoint) {
        super();
        this.modelType = modelType;
        this.modelPoint = modelPoint;
    }

    public static ParseModelAbstract getRecordedInstance(ParseObject pulledNewRecordObject) {
        NewRecord newRecord = (NewRecord) ParseModelConvert.convertToOnlineModel(pulledNewRecordObject, new NewRecord());

        return newRecord.getRecordedModel();
    }

    public ParseModelAbstract getRecordedModel() {
        return ParseModelAbstract.getInstanceFromType(this.modelType, this.modelPoint);
    }

    // MARK: ParseModel

    public LocalQuery createQueryForDeletedModel() {
        LocalQuery query = this.makeLocalQuery();

        query.whereEqualTo(kPAPFieldModelTypeKey, PQueryModelType.getInt(this.modelType));
        query.whereEqualTo(kPAPFieldModelPointKey, this.modelPoint);

        return query;
    }

    @Override
    public String getParseTableName() {
        return kPAPClassKey;
    }

    @Override
    public PQueryModelType getModelType() {
        return PQueryModelType.NewRecord;
    }

    @Override
    public Task<Void> writeCommonObject(ParseObject object) {
        object.put(kPAPFieldModelTypeKey, PQueryModelType.getInt(this.modelType));// ***Important***
        object.put(kPAPFieldModelPointKey, this.modelPoint);

        return Task.forResult(null);
    }

    @Override
    public Task<Void> readCommonObject(ParseObject object) {
        Object theModelType = this.getValueFromObject(object, kPAPFieldModelTypeKey);
        if (theModelType != null) {
            this.modelType = PQueryModelType.fromInteger(((int) theModelType));
        }

        Object theModelPoint = this.getValueFromObject(object, kPAPFieldModelPointKey);
        if (theModelPoint != null) {
            this.modelPoint = (String) theModelPoint;
        }

        return Task.forResult(null);
    }


    @Override
    public ParseModelAbstract getNewInstance() {
        return new NewRecord();
    }

    // MARK: Description
    @Override
    public String printDescription() {
        return "NewRecord{" +
                "objectUUID='" + objectUUID + '\'' +
                ", modelType=" + modelType +
                ", modelPoint='" + modelPoint + '\'' +
                '}';
    }

}
