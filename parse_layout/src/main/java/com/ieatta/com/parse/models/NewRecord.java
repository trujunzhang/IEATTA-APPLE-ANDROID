package com.ieatta.com.parse.models;

import com.ieatta.com.parse.ParseModelSync;

import com.parse.ParseObject;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.parse.ParseQuery;
import com.ieatta.com.parse.ParseModelAbstract;

import java.util.Date;

/**
 * Created by djzhang on 11/27/15.
 */
public class NewRecord extends ParseModelSync {
    // Class key
    private static final String kPAPClassKey = "NewRecord";

    // Field keys
    private static final String kPAPFieldModelTypeKey = "modelType";
    private static final String kPAPFieldModelPointKey = "modelPoint";
    private static final String kPAPFieldModelCreatedDateKey = "modelCreatedDate";

    public PQueryModelType modelType = PQueryModelType.unkown;
    public String modelPoint = "";
    public Date modelCreatedDate;

    public NewRecord() {
        super();
    }

    public NewRecord(PQueryModelType modelType, String modelPoint) {
        super();
        this.modelType = modelType;
        this.modelPoint = modelPoint;
    }

    public NewRecord(PQueryModelType modelType, String modelPoint, Date modelCreatedDate) {
        super();
        this.modelType = modelType;
        this.modelPoint = modelPoint;
        this.modelCreatedDate = modelCreatedDate;
    }


    public static ParseModelAbstract getRecordedInstance( ParseObject pulledNewRecordObject)  {
        NewRecord newRecord = (NewRecord) ParseModelAbstract.convertToOnlineModel(pulledNewRecordObject,new NewRecord() );

        return newRecord.getRecordedModel();
    }

    public ParseModelAbstract getRecordedModel() {
        return ParseModelAbstract.getInstanceFromType(this.modelType, this.modelPoint);
    }

    // MARK: ParseModel

    public ParseQuery createQueryForDeletedModel() {
        ParseQuery query = this.makeParseQuery();

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
    public void writeCommonObject(ParseObject object) {
        object.put(kPAPFieldModelTypeKey, PQueryModelType.getInt(this.modelType));// ***Important***
        object.put(kPAPFieldModelPointKey, this.modelPoint);
        object.put(kPAPFieldModelCreatedDateKey, this.modelCreatedDate);
    }

    @Override
    public void readCommonObject(ParseObject object) {
        Object theModelType = this.getValueFromObject(object, kPAPFieldModelTypeKey);
        if (theModelType != null) {
            this.modelType = PQueryModelType.fromInteger(((int) theModelType));
        }

        Object theModelPoint = this.getValueFromObject(object, kPAPFieldModelPointKey);
        if (theModelPoint != null) {
            this.modelPoint = (String) theModelPoint;
        }

        Object themMdelCreatedDate = this.getValueFromObject(object, kPAPFieldModelCreatedDateKey);
        if (themMdelCreatedDate != null) {
            this.modelCreatedDate = (Date) themMdelCreatedDate;
        }
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
                ", modelCreatedDate=" + modelCreatedDate +
                '}';
    }

}
