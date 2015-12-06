package com.ieatta.com.parse;

import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.com.parse.models.NewRecord;
import com.ieatta.com.parse.models.Team;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.ieatta.com.parse.models.enums.ParseModelFlag;
import com.ieatta.com.parse.models.enums.PhotoUsedType;
import com.ieatta.com.parse.models.enums.ReviewType;
import com.lukazakrajsek.timeago.TimeAgo;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import bolts.Task;
import bolts.TaskCompletionSource;

/**
 * Created by djzhang on 11/27/15.
 */
public abstract class ParseModelAbstract implements ParseModelProtocol {
    protected ParseModelAbstract self = this;

    static public ParseModelAbstract convertToModel(Object object, ParseModelAbstract instance) {
        instance.readObjectLocal((ParseObject) object);

        return instance;
    }

    // MARK: Convert methonds.
    public abstract ParseModelAbstract getNewInstance();

    public String getRestaurantRef() {
        return "";
    }

    public PQueryModelType getModelType()

    {
        return PQueryModelType.unkown;
    }

    public ReviewType getReviewType()

    {
        return ReviewType.Review_Unknow;
    }

    public PhotoUsedType getPhotoUsedType()

    {
        return PhotoUsedType.Photo_Used_Unknow;
    }

    public ParseObject createObject() {
        return new ParseObject(this.getParseTableName());
    }

    // MARK: Async methods.
    public abstract Task<Object> pinAfterPullFromServer();

    public abstract Task<Object> pushToServer();


    public Task<Object> eventBeforePullFromServer() {
        return null;
    }

    public Task<Boolean> eventAfterPushToServer() {
        return Task.forResult(true);
    }

    public abstract Task<Object> pullFromServerAndPin();

    public Task<Object> queryBelongToTask(ParseModelAbstract belongTo) {
        return null;
    }

    public Task<List<ParseModelAbstract>> queryParseModels(String keyword) {
        return null;
    }

    protected static final String kPAPFieldObjectUUIDKey = "UUID";
    protected static final String kPAPFieldObjectCreatedDateKey = "objectCreatedDate";
    protected static final String kPAPFieldDisplayNameKey = "displayName";
    protected static final String kPAPFieldFlagKey = "flag";

    public String objectId = "";
    public String objectUUID = "";
    public String displayName = "";
    public ParseModelFlag modelFlag = ParseModelFlag.Normal;

    public Date objectCreatedDate = new Date();

    public int sampleFileName ;// Image's offline Image Path.

    public ParseModelAbstract(String objectUUID) {
        this.objectUUID = objectUUID;
    }

    public ParseModelAbstract() {
        this.makeObjectUUID();
    }

    // MARK: Common methond.

    public String getTimeAgoString() {
        TimeAgo timeAgo = new TimeAgo(EnvironmentUtils.sharedInstance.getGlobalContext());

        return timeAgo.timeAgo(this.objectCreatedDate);
    }

    static public String getPoint(ParseModelAbstract model) {
        return model.objectUUID;
    }

    public void updateCreatedDate() {
        this.objectCreatedDate = new Date();
    }

    public Object getValueFromObject(ParseObject object, String key) {
        return object.get(key);
    }

    private String getUUID() {
        UUID uuid = UUID.randomUUID();

        return uuid.toString();
    }

    private void makeObjectUUID() {
        if (this.objectUUID.isEmpty() == true) {
            this.objectUUID = getUUID();
        }
    }

    // MARK: Common methonds.
    public NewRecord getNewRecord() {
        return new NewRecord(getModelType(), this.objectUUID, this.objectCreatedDate);
    }

    // MARK: Support common methonds for writing ParseObject.
    public void writeAbstractCommon(ParseObject object) {
        this.checkAndMakeObjectUUID(object);

        object.put(kPAPFieldObjectCreatedDateKey, this.objectCreatedDate);
        object.put(kPAPFieldFlagKey, ParseModelFlag.getInt(this.modelFlag));
    }

    public void checkAndMakeObjectUUID(ParseObject object) {
        this.makeObjectUUID();

        object.put(kPAPFieldObjectUUIDKey, this.objectUUID);
    }

    public abstract Task<ParseModelAbstract> getFirstLocalModelArrayTask();

    public abstract Task<ParseObject> getFirstOnlineObjectTask(ParseQuery query);

    // MARK: Support common methonds for reading ParseObject.

    public void readAbstractCommon(ParseObject object) {
        Object theObjectUUID = this.getValueFromObject(object, kPAPFieldObjectUUIDKey);
        if (theObjectUUID != null) {
            this.objectUUID = (String) theObjectUUID;
        }
        Object theObjectCreatedDate = this.getValueFromObject(object, kPAPFieldObjectCreatedDateKey);
        if (theObjectCreatedDate != null) {
            this.objectCreatedDate = (Date) theObjectCreatedDate;
        }
        Object theModelFlag = this.getValueFromObject(object, kPAPFieldFlagKey);
        if (theModelFlag != null) {
            int type = (int) theModelFlag;
            this.modelFlag = ParseModelFlag.fromInteger(type);
        }
    }

    public abstract void writeCommonObject(ParseObject object);

    public void writeObject(ParseObject object) {
        this.writeAbstractCommon(object);
        this.writeCommonObject(object);
    }

    public void writeLocalObject(ParseObject object) {
        this.writeAbstractCommon(object);
        this.writeCommonObject(object);
    }

    public abstract void readCommonObject(ParseObject object);

    public void readObject(ParseObject object) {
        this.readAbstractCommon(object);
        this.readCommonObject(object);
    }

    public void readObjectLocal(ParseObject object) {
        this.readAbstractCommon(object);
        this.readCommonObject(object);
    }

    public ParseACL getACL() {
        ParseACL acl = new ParseACL();

        acl.setPublicReadAccess(true);
        acl.setPublicWriteAccess(true);

        return acl;
    }

    // MARK: Support equal method.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParseModelAbstract that = (ParseModelAbstract) o;

        return objectUUID.equals(that.objectUUID);
    }

    @Override
    public int hashCode() {
        return objectUUID.hashCode();
    }

    /**
     *
     * @param previous List<Restaurant>
     * @return
     */
    static public List<String> getModelPoints(Task<List<ParseModelAbstract>> previous) {
        Object object = previous;
        List<ParseModelAbstract> fetchedModels =previous.getResult();

        LinkedList<String> points = new LinkedList<>();

        for (Object model : fetchedModels) {
            points.add(ParseModelAbstract.getPoint((ParseModelAbstract) model));
        }
        return points;
    }



    // MARK: Description
    public String printDescription() {
        return "ParseModelAbstrct{" +
                "objectId='" + objectId + '\'' +
                ", objectUUID='" + objectUUID + '\'' +
                ", objectCreatedDate=" + objectCreatedDate +
                '}';
    }

    public static ParseModelAbstract getInstanceFromType(PQueryModelType type, String objectUUID) {
        ParseModelAbstract model = getInstanceFromType(type);
        model.objectUUID = objectUUID;

        return model;
    }

    public static ParseModelAbstract getInstanceFromType(PQueryModelType type) {
        switch (type) {
            case Recipe:
                return new com.ieatta.com.parse.models.Recipe();
            case Photo:
                return new com.ieatta.com.parse.models.Photo();
            case Team:
                return new Team();
            case Review:
                return new com.ieatta.com.parse.models.Review();
            case Event:
                return new com.ieatta.com.parse.models.Event();
            case Restaurant:
                return new com.ieatta.com.parse.models.Restaurant();
            case NewRecord:
                return new com.ieatta.com.parse.models.NewRecord();
            case PeopleInEvent:
                return new com.ieatta.com.parse.models.PeopleInEvent();
            default:
                break;
        }

        return null;
    }


}
