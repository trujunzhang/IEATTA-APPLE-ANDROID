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
        // TODO djzhang(fixiing)
//        return this.getImageCache().diskImageExistsWithKey(ParseModelAbstract.getPoint(model));
        return false;
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
//        return this.getImageCache().getCacheFileList();
        return null;
    }

    /**
     Save photo's image as a offline file.

     - parameter image:           saved image
     - parameter model:           photo's instance
     - parameter completionBlock: callback variable
     */
    public Task<Object> saveTakenPhoto(Bitmap image,Photo model) {

        // ** Important ** Must store to Disk.
//        this.getImageCache().storeImage(image, forKey: ParseModelAbstract.getPoint(model),toDisk: true);

        TaskCompletionSource finalTask = new TaskCompletionSource();
        finalTask.setResult(image);
        return finalTask.getTask();
    }

    /**
     Generate a specail type image, then save it as the offline image.

     - parameter image: taken photo
     - parameter model: photo's instance

     - returns: task's instance
     */
    public  Task<Object> generateTakenPhoto(Bitmap image, Photo model) {
        return null;
    }


    public Task<Object> downloadImageWithURL(String URL) {
        final TaskCompletionSource downloadTask = new TaskCompletionSource();

        ImageLoader.getInstance().loadImage(URL, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//                downloadTask.setError(failReason);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                downloadTask.setResult(loadedImage);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
//                downloadTask.setError(failReason);
            }
        });

        return downloadTask.getTask();
    }

    public Task<Object> downloadImageFromServer(Photo model,String url) {

        /// If the image already exist on the cache folder, we don't download it from the Parse.com.
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
