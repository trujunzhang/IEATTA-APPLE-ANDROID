package com.ieatta.com.parse.models;

import android.graphics.Bitmap;
import android.system.ErrnoException;
import android.yelp.com.commonlib.io.FileUtils;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.ParseModelLocalQuery;

import com.ieatta.com.parse.ParseModelSync;
import com.ieatta.com.parse.engine.realm.LocalQuery;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.ieatta.com.parse.models.enums.PhotoUsedType;
import com.ieatta.com.parse.utils.cache.CacheImageUtils;
import com.ieatta.com.parse.utils.cache.ImageOptimizeUtils;
import com.ieatta.com.parse.utils.cache.OriginalImageUtils;
import com.ieatta.com.parse.utils.cache.ThumbnailImageUtils;
import com.parse.ParseFile;
import com.parse.ParseObject;

import org.apache.commons.io.FileExistsException;

import java.io.FileNotFoundException;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

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

    // Mark: The following variables just for Android.
    private ParseFile orginalImageFile;
    private ParseFile thumbnailImageFile;

    public Photo() {
        super();
    }

    public Photo(String usedRef) {
        super();
        this.usedRef = usedRef;
    }

    public Photo(ParseModelAbstract model) {
        this(ParseModelAbstract.getPoint(model), model.getPhotoUsedType());
    }

    public Photo(String usedRef, PhotoUsedType type) {
        super();

        this.usedRef = usedRef;
        this.usedType = type;
    }

    // MARK: ParseModel
    public LocalQuery createQueryForUsedRefWithType() {
        LocalQuery query = this.makeLocalQuery();

        query.whereEqualTo(kPAPFieldUsedRefKey, this.usedRef);
        query.whereEqualTo(kPAPFieldUsedTypeKey, PhotoUsedType.getInt(this.usedType));

        return query;
    }

    public LocalQuery createQueryForUsedRef() {
        LocalQuery query = this.makeLocalQuery();
        query.whereEqualTo(kPAPFieldUsedRefKey, this.usedRef);
        return query;
    }

    public LocalQuery createQueryForRestaurantRef() {
        LocalQuery query = this.makeLocalQuery();
        query.whereEqualTo(kPAPFieldLocalRestaurantKey, this.restaurantRef);
        query.orderByDescending(kPAPFieldObjectCreatedDateKey);
        return query;
    }

    public static LocalQuery createQueryForRestaurantRef(Restaurant restaurant) {
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

    LocalQuery createQueryByRestaurantRef(String restaurantRef) {
        LocalQuery query = this.makeLocalQuery();
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
    private LocalQuery createQueryForBatchingPhoto(List<String> usedRefs) {
        LocalQuery query = this.getLocalQueryInstance();

        query.whereContainedIn(kPAPFieldUsedRefKey, usedRefs);

        // *** Important (used orderByAscending) ***
        query.orderByAscending(kPAPFieldObjectCreatedDateKey);

        return query;
    }

    @Override
    public Task<Void> writeCommonObject(ParseObject object) {
        object.put(kPAPFieldOSTypeKey, OS_TYPE);
        object.put(kPAPFieldLocalRestaurantKey, this.restaurantRef);
        object.put(kPAPFieldUsedRefKey, this.usedRef);
        object.put(kPAPFieldUsedTypeKey, PhotoUsedType.getInt(this.usedType)); // *** Important ***

        return Task.forResult(null);
    }

    @Override
    public Task<Void> writeObject(final ParseObject object) {
        final Task.TaskCompletionSource tcs = Task.create();

        // Special: Used only for the online object.
        super.writeObject(object).onSuccessTask(new Continuation<Void, Task<ParseFile>>() {
            @Override
            public Task<ParseFile> then(Task<Void> task) throws Exception {
                return ImageOptimizeUtils.getPFFileForThumbnailImage(self); // (Thumbnail)
            }
        }).onSuccessTask(new Continuation<ParseFile, Task<Void>>() {
            @Override
            public Task<Void> then(Task<ParseFile> task) throws Exception {
                self.thumbnailImageFile = task.getResult();// (Thumbnail)
                return self.thumbnailImageFile.saveInBackground();// (Thumbnail)
            }
        }).onSuccessTask(new Continuation<Void, Task<ParseFile>>() {
            @Override
            public Task<ParseFile> then(Task<Void> task) throws Exception {
                return ImageOptimizeUtils.getPFFileForOrginalImage(self); // (Original)
            }
        }).onSuccessTask(new Continuation<ParseFile, Task<Void>>() {
            @Override
            public Task<Void> then(Task<ParseFile> task) throws Exception {
                self.orginalImageFile = task.getResult();// (Original)
                return self.orginalImageFile.saveInBackground();// (Original)
            }
        }).onSuccess(new Continuation<Void, Void>() {
            @Override
            public Void then(Task<Void> task) throws Exception {

                object.put(kPAPFieldOriginalImageKey, orginalImageFile);// (Original)
                object.put(kPAPFieldThumbnailImageKey, thumbnailImageFile);// (Thumbnail)

                return null;
            }
        }).continueWith(new Continuation<Void, Object>() {
            @Override
            public Object then(Task<Void> task) throws Exception {
                if (task.isFaulted()) {
                    tcs.setError(task.getError());
                } else {
                    tcs.setResult(null);
                }
                return null;
            }
        });

        return tcs.getTask();
    }

    @Override
    public Task<Void> writeLocalObject(final ParseObject object) {
        return super.writeLocalObject(object)
                .onSuccess(new Continuation<Void, Void>() {
                    @Override
                    public Void then(Task<Void> task) throws Exception {
                        // Special: Used only for the offline object.
                        object.put(kPAPFieldOriginalUrlKey, self.originalUrl);
                        object.put(kPAPFieldThumbnailUrlKey, self.thumbnailUrl);

                        return null;
                    }
                });
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
        return ParseModelLocalQuery.queryFromRealm(PQueryModelType.Photo, new Photo().createQueryForRestaurantRef(restaurant));
    }

    public static Task<List<ParseModelAbstract>> queryPhotosByModel(ParseModelAbstract model) {
        return ParseModelLocalQuery.queryFromRealm(PQueryModelType.Photo, new Photo(model).createQueryForUsedRefWithType());
    }

    public static Task<List<ParseModelAbstract>> queryPhotosFromUsedRefs(List<String> usedRefs) {
        return ParseModelLocalQuery.queryFromRealm(PQueryModelType.Photo, new Photo().createQueryForBatchingPhoto(usedRefs));
    }

    /**
     * When have taken a image, save photo instance and save the image.
     * <p/>
     * Save photo's image to (com.virtualbreak.IEATTA.Upload),this namespace.
     * <p/>
     * - parameter newPhoto:        photo's instance
     * - parameter image:           taken image
     */
    public static Task<Void> pinPhotoAndCacheImage(final Photo newPhoto, final Bitmap image) {

        return OriginalImageUtils.sharedInstance.generateTakenPhoto(image, newPhoto)
                .onSuccessTask(new Continuation<Bitmap, Task<Bitmap>>() {
                    @Override
                    public Task<Bitmap> then(Task<Bitmap> task) throws Exception {
                        return ThumbnailImageUtils.sharedInstance.generateTakenPhoto(image, newPhoto);
                    }
                }).onSuccessTask(new Continuation<Bitmap, Task<Void>>() {
                    @Override
                    public Task<Void> then(Task<Bitmap> task) throws Exception {
                        return newPhoto.saveInBackgroundWithNewRecord();
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
                ", restaurantRef='" + restaurantRef + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", originalUrl='" + originalUrl + '\'' +
                ", usedType=" + usedType +
                '}';
    }

    @Override
    public Task<Void> beforePullFromServer() {
        final Task.TaskCompletionSource tcs = Task.create();

        // 1. First of all,to decrease client storage.
        //   So just save online thumbnail image as offline file.
        this.downloadThumbnailImageFromServer()
                .continueWith(new Continuation<Void, Object>() {
                    @Override
                    public Object then(Task<Void> task) throws Exception {
                        if (task.isFaulted()) {
                            tcs.setError(task.getError());
                        } else {
                            tcs.setResult(null);
                        }
                        return null;
                    }
                });

        return tcs.getTask();
    }


    public Task<Void> downloadThumbnailImageFromServer() {
        return ThumbnailImageUtils.sharedInstance.downloadImageFromServer(this, this.thumbnailUrl);
    }

    public Task<Void> downloadOriginalImageFromServer() {
        return OriginalImageUtils.sharedInstance.downloadImageFromServer(this, this.originalUrl);
    }

    public Task<Void> downloadCacheImageFromServer() {
        return CacheImageUtils.sharedInstance.downloadImageFromServer(this, this.originalUrl);
    }

    @Override
    public Task<Boolean> afterPushToServer() {
        boolean isRemove = OriginalImageUtils.sharedInstance.removeOriginalImage(this);
        if (isRemove == false) {
            return Task.forError(new FileExistsException(self.printDescription()));
        }

        return Task.forResult(true);
    }

    public Task<Bitmap> getThumbanilImage() {
        Bitmap image = ThumbnailImageUtils.sharedInstance.getTakenPhoto(ParseModelAbstract.getPoint(this));
        if (image != null) {
            return Task.forResult(image);
        }
        return Task.forError(new FileNotFoundException(self.printDescription()));
    }

    public static Photo getInstanceFromPhotoPoint(String photoPoint) {
        Photo photo = new Photo();
        photo.objectUUID = photoPoint;
        return photo;
    }
}
