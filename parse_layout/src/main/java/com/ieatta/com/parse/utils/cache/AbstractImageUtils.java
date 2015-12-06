package com.ieatta.com.parse.utils.cache;

import android.graphics.Bitmap;
import android.media.Image;
import android.view.View;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Photo;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.File;
import java.io.IOException;
import java.util.List;

import bolts.Task;
import bolts.TaskCompletionSource;

/**
 * Created by djzhang on 11/30/15.
 */

/// How to store a taken photo to decrease client storage.
///   1.  When taken a photo, save it as original and thumbnail formats.
///   2.1 When pushing to server, push original and thumbnail images to server.
///   2.2 When pushed successfully, delete offline original image.
///   3.  When pulling from server, just download a thumbnail image from server.
public abstract class AbstractImageUtils {


    protected abstract UnlimitedDiskCache getImageCache() ;


    /**
     Query the disk cache's url path.

     - parameter objectUUID: Photo's objectUUID

     - returns: Image Cache's url
     */
    public File getCacheImageUrl(String objectUUID) {
        return this.getImageCache().get(objectUUID);
    }

    public File getCacheImageUrl(Photo model) {
        return this.getCacheImageUrl(ParseModelAbstract.getPoint(model));
    }

    public boolean diskImageExistsWithKey(Photo model) {
        File file = this.getImageCache().get(ParseModelAbstract.getPoint(model));
        if(file == null || file.exists() == false){
            return false;
        }
        return true;
    }

    /**
     Query the disk cache synchronously after checking the memory cache.

     - parameter objectUUID: photo's objectUUID

     - returns: Image Cache
     */
    public File getTakenPhoto(String objectUUID) {
        if(objectUUID == null || objectUUID.equals("") == true){
            return null;
        }

//        return this.getImageCache().imageFromDiskCacheForKey(objectUUID);
        return null;
    }

    /**
     Query the disk cache synchronously after checking the memory cache.

     - parameter model: Photo's instance

     - returns: Image Cache
     */
    public File getTakenPhoto(Photo model) {
        return this.getTakenPhoto(ParseModelAbstract.getPoint(model));
    }

    public List listCacheImageNames(){
        return this.getImageCache().getCacheFileList();
    }

    /**
     Save photo's image as a offline file.

     - parameter image:           saved image
     - parameter model:           photo's instance
     - parameter completionBlock: callback variable
     */
    public Task<Bitmap> saveTakenPhoto(Bitmap image,Photo model) {

        // ** Important ** Must store to Disk.
        try {
            this.getImageCache().save(ParseModelAbstract.getPoint(model),image);
        } catch (IOException e) {
            return Task.forError(e);
        }

        return Task.forResult(image);
    }

    /**
     Generate a specail type image, then save it as the offline image.

     - parameter image: taken photo
     - parameter model: photo's instance

     - returns: task's instance
     */
    public Task<Bitmap> generateTakenPhoto(Bitmap image, Photo model) {
        return Task.forResult(null);
    }

    public Task<Bitmap> downloadImageWithURL(String URL) {
        final Task.TaskCompletionSource tcs = Task.create();

        ImageLoader.getInstance().loadImage(URL, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                tcs.setError(new Exception("Download image failed!",failReason.getCause()));
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                tcs.setResult(loadedImage);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                tcs.setError(new Exception("Loading cancelled!"));
            }
        });

        return tcs.getTask();
    }

    public Task<Object> downloadImageFromServer(Photo model,String url) {

        /// If the image already exist on the cache folder, we don't download it from the Parse.com.
//        this.getTakenPhoto(model);
//        if let _image = this.getTakenPhoto(model){
//            return BFTask(result: _image)
//        }
//        else if(url.isEmpty == true){
//            return BFTask(error: NSError.getError(IEAErrorType.EmptyURL, description: "\(model.printDescription())"))
//        }
//        if let URL = NSURL(string: url){
//            return this.downloadImageWithURL(URL).continueWithBlock { (task) -> AnyObject? in
//
//                if let _ = task.error{
//                    return BFTask(error: NSError.getError(IEAErrorType.OnlineImage, description: "\(model.printDescription())"))
//                }else{
//                    return this.saveTakenPhoto(task.result as! UIImage, model: model)
//                }
//            }
//        }else{
//            return BFTask(error: NSError.getError(IEAErrorType.OnlineImage, description: "\(model.printDescription())"))
//        }

        return null;
    }


}
