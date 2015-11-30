package com.ieatta.com.parse.models;

import com.ieatta.com.parse.ParseModelQuery;
import com.ieatta.com.parse.ParseModelSync;

import bolts.Task;

import com.parse.ParseFile;
import com.parse.ParseObject;
import com.ieatta.com.parse.models.enums.PQeuryModelType;
import com.parse.ParseQuery;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.enums.PQeuryModelType;
import com.ieatta.com.parse.models.enums.PhotoUsedType;
import com.ieatta.com.parse.models.enums.ReviewType;

import java.util.LinkedList;

/**
 * Created by djzhang on 11/27/15.
 */
public class Photo extends ParseModelSync {
    private static final String OS_TYPE = "Android";

    // Class key
    private static final String kPAPClassKey = "Photo";

    // Field keys
    private static final String kPAPFieldOriginalImageKey = "originalFile";
    private static final String kPAPFieldThumbnailImageKey = "thumbnailFile";

    private static final String kPAPFieldOriginalUrlKey = "originalUrl";
    private static final String kPAPFieldThumbnailUrlKey = "thumbnailUrl";

    private static final String kPAPFieldUsedRefKey = "usedRef";
    private static final String kPAPFieldUsedTypeKey = "usedType";
    private static final String kPAPFieldLocalRestaurantKey = "restaurantRef";
    private static final String kPAPFieldOSTypeKey = "osType";

    // Required
    public String restaurantRef = "";

    public String usedRef = "";
    public PhotoUsedType usedType = PhotoUsedType.Photo_Used_Unknow;

    // MARK: Variable for pushing to server.
    public String originalUrl = "";
    public String thumbnailUrl = "";

    public Photo() {
        super();
    }

    public Photo(String usedRef) {
        super();
        this.usedRef = usedRef;
    }

//    init(photoPoint:String){
//        super.init()
//        this.objectUUID = photoPoint
//    }

    public Photo(ParseModelAbstract model) {
        this(ParseModelAbstract.getPoint(model), model.getPhotoUsedType());
    }

    public Photo(String usedRef, PhotoUsedType type) {
        super();

        this.usedRef = usedRef;
        this.usedType = type;
    }

    // MARK: ParseModel
    ParseQuery createQueryForUsedRefWithType() {
        ParseQuery query = this.makeParseQuery();
        query.whereEqualTo(kPAPFieldUsedRefKey, this.usedRef);
        query.whereEqualTo(kPAPFieldUsedTypeKey, this.usedType);

        return query;
    }

    ParseQuery   createQueryForUsedRef()    {
          ParseQuery query = this.makeParseQuery();
        query.whereEqualTo(kPAPFieldUsedRefKey,this.usedRef);
        return query;
    }

    ParseQuery   createQueryForRestaurantRef()    {
          ParseQuery query = this.makeParseQuery();
        query.whereEqualTo(kPAPFieldLocalRestaurantKey,this.restaurantRef);
        query.orderByDescending(kPAPFieldObjectCreatedDateKey);
        return query;
    }

    static ParseQuery createQueryForRestaurantRef(Restaurant restaurant)    {
        Photo photo = new Photo();
        photo.restaurantRef = ParseModelAbstract.getPoint(restaurant);
        return photo.createQueryForRestaurantRef();
    }

    @Override
    public String getParseTableName() {
        return kPAPClassKey;
    }

    @Override
    public PQeuryModelType getModelType() {
        return PQeuryModelType.Photo;
    }

    @Override
    public ParseModelAbstract getNewInstance() {
        return new Photo();
    }

    ParseQuery   createQueryByRestaurantRef(String restaurantRef)   {
          ParseQuery query = this.makeParseQuery();
        query.whereEqualTo(kPAPFieldUsedRefKey,restaurantRef);
        return query;
    }

    /**
     * Fetch related photo for the model. Sort from newest to oldest.
     * <p/>
     * - parameter usedRefs: photo's usedRef,is the model's uuid.
     * <p/>
     * - returns: query's instance
     */
    private ParseQuery createQueryForBatchingPhoto(LinkedList<String> usedRefs)   {
          ParseQuery query = this.getParseQueryInstance();

        query.whereContainedIn(kPAPFieldUsedRefKey,usedRefs);

        // *** Important (used orderByAscending) ***
        query.orderByAscending(kPAPFieldObjectCreatedDateKey);


        return query;
    }

    @Override
    public void writeCommonObject(ParseObject object) {
        object.put(kPAPFieldOSTypeKey, OS_TYPE);
        object.put(kPAPFieldLocalRestaurantKey, this.restaurantRef);
        object.put(kPAPFieldUsedRefKey, this.usedRef);
        object.put(kPAPFieldUsedTypeKey, this.usedType.ordinal()); // *** Important ***
    }

    @Override
    public void writeObject(ParseObject object) {
         super.writeObject(object);

//        // Special: Used only for the online object.
//        object[kPAPFieldOriginalImageKey]    = ImageOptimizeUtils.getPFFileForOrginalImage(self);
//        object[kPAPFieldThumbnailImageKey]   = ImageOptimizeUtils.getPFFileForThumbnailImage(self);

        this.writeCommonObject(object);
    }

    @Override
    public void writeLocalObject(ParseObject object) {
         super.writeLocalObject(object);

        // Special: Used only for the offline object.
        object.put(kPAPFieldOriginalUrlKey, this.originalUrl);
        object.put(kPAPFieldThumbnailUrlKey, this.thumbnailUrl);

        this.writeCommonObject(object);
    }


    @Override
    public void readCommonObject(ParseObject object) {
        Object theRestaurantRef = this.getValueFromObject(object, kPAPFieldLocalRestaurantKey);
        if (theRestaurantRef != null) {
            this.restaurantRef = (String) theRestaurantRef;
        }

        Object theUsedRef = this.getValueFromObject(object, kPAPFieldUsedRefKey);
        if (theUsedRef != null) {
            this.usedRef = (String) theUsedRef;
        }

        Object theUsedType = this.getValueFromObject(object, kPAPFieldUsedTypeKey);
        if (theUsedType != null) {
            this.usedType = PhotoUsedType.fromInteger(((int) theUsedType));
        }
    }

    @Override
    public void readObject(ParseObject object) {
        super.readObject(object);

        // Special: Used only for the online object.
        Object _originalFile = this.getValueFromObject(object, kPAPFieldOriginalImageKey);
        if (_originalFile != null) {
            this.originalUrl = ((ParseFile)_originalFile).getUrl();
        }

        Object _thumbnailFile = this.getValueFromObject(object, kPAPFieldThumbnailImageKey);
        if (_thumbnailFile != null) {
            this.thumbnailUrl = ((ParseFile)_thumbnailFile).getUrl();
        }

        this.readCommonObject(object);
    }

    @Override
    public void readObjectLocal(ParseObject object) {
        super.readObject(object);

        // Special: Used only for the offline object.
        Object _originalUrl = this.getValueFromObject(object, kPAPFieldOriginalUrlKey);
        if (_originalUrl != null) {
            this.originalUrl = (String) _originalUrl;
        }
        Object _thumbnailUrl = this.getValueFromObject(object, kPAPFieldThumbnailUrlKey);
        if (_thumbnailUrl != null) {
            this.thumbnailUrl = (String) _thumbnailUrl;
        }

        this.readCommonObject(object);
    }

    static Task<Object> queryPhotosByRestaurant( Restaurant restaurant){
        return ParseModelQuery.queryFromDatabase(PQeuryModelType.Photo, new Photo().createQueryForRestaurantRef(restaurant));
    }

    static Task<Object> queryPhotosByModel(ParseModelAbstract model){
        return ParseModelQuery.queryFromDatabase(PQeuryModelType.Photo,new Photo(model).createQueryForUsedRefWithType());
    }

    static Task<Object> queryPhotosFromUsedRefs(LinkedList<String> usedRefs){
        return ParseModelQuery.queryFromDatabase(PQeuryModelType.Photo,new Photo().createQueryForBatchingPhoto(usedRefs));
    }

    /**
     * When have taken a image, save photo instance and save the image.
     * <p/>
     * Save photo's image to (com.virtualbreak.IEATTA.Upload),this namespace.
     * <p/>
     * - parameter newPhoto:        photo's instance
     * - parameter image:           taken image
     */
//    static  Task<Object> pinPhotoAndCacheImage(forNewPhoto newPhoto:Photo,image: UIImage)  {
//        return OriginalImageUtils.sharedInstance.generateTakenPhoto(image, model: newPhoto).continueWithSuccessBlock { (task) -> AnyObject? in
//
//            return ThumbnailImageUtils.sharedInstance.generateTakenPhoto(image, model: newPhoto)
//        }.continueWithSuccessBlock { (task) -> AnyObject? in
//            // 3. Save photo's instance.
//            return newPhoto.pinInBackgroundWithNewRecord()
//        }
//    }

    static Photo getTakenPhotoInstance(ParseModelAbstract model) {
        Photo photo = new Photo();

        photo.usedRef = ParseModelAbstract.getPoint(model);
        photo.usedType = model.getPhotoUsedType();

        // **** Important ****
        if(model.getPhotoUsedType() != PhotoUsedType.Photo_Used_People){
            photo.restaurantRef = model.getRestaurantRef();
        }

        return photo;
    }

    // MARK: Description
    @Override
    public String printDescription() {
        return "Photo{" +
                "objectUUID='" + objectUUID + '\'' +
                ", usedRef='" + usedRef + '\'' +
                ", usedType=" + usedType +
                '}';
    }
//     @Override     public Task<Object> pinAfterPullFromServer()   {
//        // 1. First of all,to decrease client storage,so just save online thumbnail image as offline file.
//
//        return this.downloadThumbnailImageFromServer().continueWithSuccessBlock { (task) -> AnyObject? in
//            return super.pinAfterPullFromServer()
//        }
//
//    }
//
//    Task<Object>   downloadThumbnailImageFromServer(){
//        return ThumbnailImageUtils.sharedInstance.downloadImageFromServer(forPhoto: self,url: this.thumbnailUrl)
//    }
//
//    Task<Object>   downloadOriginalImageFromServer(){
//        return OriginalImageUtils.sharedInstance.downloadImageFromServer(forPhoto: self,url: this.originalUrl)
//    }
//
//    Task<Object>   downloadCacheImageFromServer(){
//        return CacheImageUtils.sharedInstance.downloadImageFromServer(forPhoto: self,url: this.originalUrl)
//    }
//
//     @Override     Task<Object> void eventAfterPushToServer(){
//        return OriginalImageUtils.sharedInstance.removeOriginalImage(self)
//    }
//
//    Task<Object>   getRelatedPhoto(){
//        return this.getFirstLocalObjectArrayInBackground(this.createQueryForUsedRef()).continueWithSuccessBlock { (task) -> AnyObject? in
//            return this.convertToLocalModelTask(task)
//        }.continueWithBlock({ (task) -> AnyObject? in
//        return this.getThumbanilImage()
//        })
//    }
//
//    Task<Object>   getThumbanilImage() {
//        if let image = ThumbnailImageUtils.sharedInstance.getTakenPhoto(ParseModelAbstract.getPoint(self)){
//            return BFTask(result: image)
//        }else{
//            return BFTask(error: NSError.getError(IEAErrorType.LocalImage, description: "When fetching Image for NewPhoto, and the photo's UUID is \(objectUUID)"))
//        }
//    }

}
