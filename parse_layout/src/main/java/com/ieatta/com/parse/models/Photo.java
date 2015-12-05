package com.ieatta.com.parse.models;

import android.graphics.Bitmap;

import com.ieatta.com.parse.ParseModelQuery;
import com.ieatta.com.parse.ParseModelSync;

import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;

import com.ieatta.com.parse.utils.cache.CacheImageUtils;
import com.ieatta.com.parse.utils.cache.ImageOptimizeUtils;
import com.ieatta.com.parse.utils.cache.OriginalImageUtils;
import com.ieatta.com.parse.utils.cache.ThumbnailImageUtils;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.parse.ParseQuery;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.enums.PhotoUsedType;

import java.io.File;
import java.util.List;

/**
 * Created by djzhang on 11/27/15.
 */
public class Photo extends ParseModelSync {
    private Photo self = this;

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
    public ParseQuery createQueryForUsedRefWithType() {
        ParseQuery query = this.makeParseQuery();
        query.whereEqualTo(kPAPFieldUsedRefKey, this.usedRef);
        query.whereEqualTo(kPAPFieldUsedTypeKey, this.usedType);

        return query;
    }

    public ParseQuery createQueryForUsedRef() {
        ParseQuery query = this.makeParseQuery();
        query.whereEqualTo(kPAPFieldUsedRefKey, this.usedRef);
        return query;
    }

    public ParseQuery createQueryForRestaurantRef() {
        ParseQuery query = this.makeParseQuery();
        query.whereEqualTo(kPAPFieldLocalRestaurantKey, this.restaurantRef);
        query.orderByDescending(kPAPFieldObjectCreatedDateKey);
        return query;
    }

    public static ParseQuery createQueryForRestaurantRef(Restaurant restaurant) {
        Photo photo = new Photo();
        photo.restaurantRef = ParseModelAbstract.getPoint(restaurant);
        return photo.createQueryForRestaurantRef();
    }

    @Override
    public String getParseTableName() {
        return kPAPClassKey;
    }

    @Override
    public PQueryModelType getModelType() {
        return PQueryModelType.Photo;
    }

    @Override
    public ParseModelAbstract getNewInstance() {
        return new Photo();
    }

    ParseQuery createQueryByRestaurantRef(String restaurantRef) {
        ParseQuery query = this.makeParseQuery();
        query.whereEqualTo(kPAPFieldUsedRefKey, restaurantRef);
        return query;
    }

    /**
     * Fetch related photo for the model. Sort from newest to oldest.
     * <p/>
     * - parameter usedRefs: photo's usedRef,is the model's uuid.
     * <p/>
     * - returns: query's instance
     */
    private ParseQuery createQueryForBatchingPhoto(List<String> usedRefs) {
        ParseQuery query = this.getParseQueryInstance();

        query.whereContainedIn(kPAPFieldUsedRefKey, usedRefs);

        // *** Important (used orderByAscending) ***
        query.orderByAscending(kPAPFieldObjectCreatedDateKey);

        return query;
    }

    @Override
    public void writeCommonObject(ParseObject object) {
        object.put(kPAPFieldOSTypeKey, OS_TYPE);
        object.put(kPAPFieldLocalRestaurantKey, this.restaurantRef);
        object.put(kPAPFieldUsedRefKey, this.usedRef);
        object.put(kPAPFieldUsedTypeKey, PhotoUsedType.getInt(this.usedType)); // *** Important ***
    }

    @Override
    public void writeObject(ParseObject object) {
        super.writeObject(object);

        // Special: Used only for the online object.
        object.put(kPAPFieldOriginalImageKey, ImageOptimizeUtils.getPFFileForOrginalImage(this));
        object.put(kPAPFieldThumbnailImageKey, ImageOptimizeUtils.getPFFileForThumbnailImage(this));
    }

    @Override
    public void writeLocalObject(ParseObject object) {
        super.writeLocalObject(object);

        // Special: Used only for the offline object.
        object.put(kPAPFieldOriginalUrlKey, this.originalUrl);
        object.put(kPAPFieldThumbnailUrlKey, this.thumbnailUrl);
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
            this.originalUrl = ((ParseFile) _originalFile).getUrl();
        }

        Object _thumbnailFile = this.getValueFromObject(object, kPAPFieldThumbnailImageKey);
        if (_thumbnailFile != null) {
            this.thumbnailUrl = ((ParseFile) _thumbnailFile).getUrl();
        }
    }

    @Override
    public void readObjectLocal(ParseObject object) {
        super.readObjectLocal(object);

        // Special: Used only for the offline object.
        Object _originalUrl = this.getValueFromObject(object, kPAPFieldOriginalUrlKey);
        if (_originalUrl != null) {
            this.originalUrl = (String) _originalUrl;
        }
        Object _thumbnailUrl = this.getValueFromObject(object, kPAPFieldThumbnailUrlKey);
        if (_thumbnailUrl != null) {
            this.thumbnailUrl = (String) _thumbnailUrl;
        }
    }

    public static Task<List<ParseModelAbstract>> queryPhotosByRestaurant(Restaurant restaurant) {
        return ParseModelQuery.queryFromDatabase(PQueryModelType.Photo, new Photo().createQueryForRestaurantRef(restaurant));
    }

    public static Task<List<ParseModelAbstract>> queryPhotosByModel(ParseModelAbstract model) {
        return ParseModelQuery.queryFromDatabase(PQueryModelType.Photo, new Photo(model).createQueryForUsedRefWithType());
    }

    public static Task<List<ParseModelAbstract>> queryPhotosFromUsedRefs(List<String> usedRefs) {
        return ParseModelQuery.queryFromDatabase(PQueryModelType.Photo, new Photo().createQueryForBatchingPhoto(usedRefs));
    }

    /**
     * When have taken a image, save photo instance and save the image.
     * <p/>
     * Save photo's image to (com.virtualbreak.IEATTA.Upload),this namespace.
     * <p/>
     * - parameter newPhoto:        photo's instance
     * - parameter image:           taken image
     */
    public static Task<Object> pinPhotoAndCacheImage(final Photo newPhoto, final Bitmap image) {

        return OriginalImageUtils.sharedInstance.generateTakenPhoto(image, newPhoto).continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
                return ThumbnailImageUtils.sharedInstance.generateTakenPhoto(image, newPhoto);
            }
        }).continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
                return newPhoto.pinInBackgroundWithNewRecord();
            }
        });
    }

    public static Photo getTakenPhotoInstance(ParseModelAbstract model) {
        Photo photo = new Photo();

        photo.usedRef = ParseModelAbstract.getPoint(model);
        photo.usedType = model.getPhotoUsedType();

        // **** Important ****
        if (model.getPhotoUsedType() != PhotoUsedType.Photo_Used_People) {
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

//    @Override
//    public Task<Object> eventBeforePullFromServer() {
//        // 1. First of all,to decrease client storage,so just save online thumbnail image as offline file.
//        return this.downloadThumbnailImageFromServer();
//    }

    Task<Object> downloadThumbnailImageFromServer() {
        return ThumbnailImageUtils.sharedInstance.downloadImageFromServer(this, this.thumbnailUrl);
    }

    Task<Object> downloadOriginalImageFromServer() {
        return OriginalImageUtils.sharedInstance.downloadImageFromServer(this, this.originalUrl);
    }

    Task<Object> downloadCacheImageFromServer() {
        return CacheImageUtils.sharedInstance.downloadImageFromServer(this, this.originalUrl);
    }

//    @Override
//    public Task<Object> eventAfterPushToServer() {
//        return OriginalImageUtils.sharedInstance.removeOriginalImage(this);
//    }

    public Task<Object> getRelatedPhoto() {
//        return this.getFirstLocalObjectArrayInBackground(this.createQueryForUsedRef()).continueWith(new Continuation<Object, Object>() {
//            @Override
//            public Object then(Task<Object> task) throws Exception {
//                return self.convertToLocalModelTask(task);
//            }
//        }).continueWith(new Continuation<Object, Object>() {
//            @Override
//            public Object then(Task<Object> task) throws Exception {
//                return self.getThumbanilImage();
//            }
//        });

        return null;
    }

    public Task<Object> getThumbanilImage() {
        TaskCompletionSource finalTask = new TaskCompletionSource();

        File image = ThumbnailImageUtils.sharedInstance.getTakenPhoto(ParseModelAbstract.getPoint(this));
        if (image != null) {
            finalTask.setResult(image);

        } else {
//            return BFTask(error: NSError.getError(IEAErrorType.LocalImage, description: "When fetching Image for NewPhoto, and the photo's UUID is \(objectUUID)"))
//            finalTask.setError();
        }

        return finalTask.getTask();
    }

}
