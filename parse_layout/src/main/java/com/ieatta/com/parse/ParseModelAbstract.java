package com.ieatta.com.parse;

import android.content.Context;

import com.ieatta.com.parse.models.NewRecord;
import com.ieatta.com.parse.models.enums.PQeuryModelType;
import com.ieatta.com.parse.models.enums.ParseModelFlag;
import com.ieatta.com.parse.models.enums.PhotoUsedType;
import com.ieatta.com.parse.models.enums.ReviewType;
import com.lukazakrajsek.timeago.TimeAgo;
import com.parse.ParseACL;
import com.parse.ParseObject;

import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;

import bolts.Task;

/**
 * Created by djzhang on 11/27/15.
 */
public abstract class ParseModelAbstract implements ParseModelProtocol {

    static public ParseModelAbstract convertToModel(Object object, ParseModelAbstract instance) {
        instance.readObjectLocal((ParseObject) object);

        return instance;
    }

    // MARK: Convert methonds.
    public abstract ParseModelAbstract getNewInstance();

    public String getRestaurantRef() {
        return "";
    }

    public PQeuryModelType getModelType()

    {
        return PQeuryModelType.unkown;
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
//    public void pinAfterPullFromServer()  BFTask{
//        fatalError("Must Override")
//    }
//
//    public void pushToServer()  BFTask{
//        fatalError("Must Override")
//    }
//
//    public void eventAfterPushToServer()  BFTask{
//        return BFTask(result: true)
//    }
//
//    public void pullFromServerAndPin()  BFTask{
//        fatalError("Must Override")
//    }
//
//    public Task<Object> queryBelongToTask(belongTo:ParseModelAbstract?) {
//        fatalError("Must Override")
//    }

    public Task<Object> queryParseModels(String keyword){
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

    public Date objectCreatedDate;

    public String sampleFileName = "";// Image's offline Image Path.

    public ParseModelAbstract(String objectUUID) {
        this.objectUUID = objectUUID;
    }

    public ParseModelAbstract() {
        this.makeObjectUUID();
    }


    // MARK: Common methond.

    public String getTimeAgoString(Context context)  {
        TimeAgo timeAgo = new TimeAgo(context);

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
//    public NewRecord getNewRecord()  {
//        return NewRecord(modelType: getModelType().rawValue, modelPoint: this.objectUUID, modelCreatedDate: this.objectCreatedDate)
//    }

    // MARK: Support common methonds for writing ParseObject.
    public void writeAbstractCommon(ParseObject object) {
        this.checkAndMakeObjectUUID(object);

//        object[kPAPFieldObjectCreatedDateKey] = this.objectCreatedDate
//        object[kPAPFieldFlagKey]              = this.modelFlag
    }

    public void checkAndMakeObjectUUID(ParseObject object) {
        this.makeObjectUUID();

//        object[kPAPFieldObjectUUIDKey] = this.objectUUID
    }

    public abstract void writeCommonObject(ParseObject object);

    public void writeObject(ParseObject object) {
        this.writeAbstractCommon(object);
    }

    public void writeLocalObject(ParseObject object) {
        this.writeAbstractCommon(object);
    }

//    public void getFirstLocalModelArrayTask()  BFTask{
//        fatalError("Must Override")
//    }
//    public void getFirstOnlineObjectTask()  BFTask{
//        fatalError("Must Override")
//    }

    // MARK: Support common methonds for reading ParseObject.


    public void readAbstractCommon(ParseObject object) {
//        if let theObjectUUID: AnyObject = getValueFromObject(object,key: kPAPFieldObjectUUIDKey){
//            this.objectUUID = theObjectUUID as! String
//        }
//
//        if let theObjectCreatedDate: AnyObject = getValueFromObject(object,key: kPAPFieldObjectCreatedDateKey){
//            this.objectCreatedDate = theObjectCreatedDate as! NSDate
//        }
//
//        if let theModelFlag: AnyObject = getValueFromObject(object,key: kPAPFieldFlagKey){
//            this.modelFlag = theModelFlag as! Int
//        }
    }

//    public void readCommonObject(ParseObject object){
//        fatalError("Must Override")
//    }

    public void readObject(ParseObject object) {
        this.readAbstractCommon(object);
    }

    public void readObjectLocal(ParseObject object) {
        this.readAbstractCommon(object);
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

//    class public [String] getModelPoints(BFTask previous)  {
//        let fetchedModels:NSArray = previous.result as! NSArray
//        var points:[String] = [String]()
//        for model in fetchedModels{
//            points.append(ParseModelAbstract.getPoint(model as! ParseModelAbstract))
//        }
//        return points
//    }

    // MARK: Description
    public String printDescription() {
        return "ParseModelAbstrct{" +
                "objectId='" + objectId + '\'' +
                ", objectUUID='" + objectUUID + '\'' +
                ", objectCreatedDate=" + objectCreatedDate +
                '}';
    }

    public static ParseModelAbstract getInstanceFromType(PQeuryModelType type, String objectUUID) {
        ParseModelAbstract model = getInstanceFromType(type);
        model.objectUUID = objectUUID;

        return model;
    }

    public static ParseModelAbstract getInstanceFromType(PQeuryModelType type) {
        switch (type) {
            case Recipe:
                return new com.ieatta.com.parse.models.Recipe();
            case Photo:
                return new com.ieatta.com.parse.models.Photo();
            case ParseUser:
                return new com.ieatta.com.parse.models.ParseUser();
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


    /**
     * Convert fetched result's value as ParseObject array to Model array.
     * <p/>
     * - parameter value: result's value
     * - parameter offline: offline is true,online is false
     * <p/>
     * - returns: ParseModelAbstract's array
     */
    public LinkedList<ParseModelAbstract> convertToParseModelArray(Object value, boolean offline)  {
        LinkedList<ParseModelAbstract> array = new LinkedList<>();

//        for object in (value! as! NSArray){
//            if(offline){
//                array.append(ParseModelAbstract.convertToLocalModel((ParseObject)object,instance: getNewInstance()) as AnyObject)
//            }else{
//                array.append(ParseModelAbstract.convertToOnlineModel((ParseObject)object,instance: getNewInstance()) as AnyObject)
//            }
//        }

        return array;
    }

    public ParseModelAbstract convertToLocalModel(Object object) {
        ParseModelAbstract instance = this.getNewInstance(); // Alloc a new instance.
        instance.readObjectLocal((ParseObject)object  );
        return instance;
    }

    public ParseModelAbstract convertToLocalModel(Object object, ParseModelAbstract instance) {
        instance.readObjectLocal((ParseObject) object);
        return instance;
    }

    public static ParseModelAbstract convertToOnlineModel(Object object, ParseModelAbstract instance) {
        instance.readObject((ParseObject) object);
        return instance;
    }

//    public void convertToOnlineModelTask(getFirstObjectTask:BFTask) BFTask{
//        if let object = getFirstObjectTask.result{
//            this.readObject((ParseObject)object)
//            return BFTask(result: true)
//        }
//
//        return BFTask(error: NSError.getError(IEAErrorType.FirstObject, description: "\(this.printDescription())"))
//    }

//    public void convertToLocalModelTask(firstObjectArray:BFTask) BFTask{
//        if let value = firstObjectArray.result{
//            let array = value as! NSArray
//            if(array.count >= 1){
//                let firstObject = array[0] as! ParseObject
//                this.readObjectLocal(firstObject)
//                return BFTask(result: true)
//            }
//        }
//
//        return BFTask(error: NSError.getError(IEAErrorType.FirstObject, description: "\(this.printDescription())"))
//    }
//
//    class public ParseModelAbstract convertToFirstLocalModel(result:AnyObject?,ParseModelAbstract instance)  {
//        if((result! as! NSArray).count == 1){
//            return instance.convertToLocalModel((result! as! NSArray)[0])
//        }
//        return nil
//    }


}
