package com.ieatta.com.parse.utils.cache;

import android.graphics.Bitmap;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Photo;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

import bolts.Task;

/**
 * Created by djzhang on 11/30/15.
 */
public class OriginalImageUtils extends AbstractImageUtils{

public     final static OriginalImageUtils sharedInstance  = new  OriginalImageUtils();

    /**
     This SDImageCache constructor's namespace is "Original". Store images on the folder: "Original/com.virtualbreak.IEATTA.original"

     - returns: SDImageCache's instance
     */
    @Override
    protected UnlimitedDiskCache getImageCache() {
        File cacheDir = StorageUtils.getCacheDirectory(EnvironmentUtils.sharedInstance.getGlobalContext(), "original");
        return new  UnlimitedDiskCache(cacheDir);
    }

    /**
     Remove offline original image.

     - parameter photo: photo's instance
     */
    public Task<Object> removeOriginalImage(Photo photo) {
//        boolean exist = this.getImageCache().diskImageExistsWithKey(ParseModelAbstract.getPoint(photo));
//        if(exist == true){
//            this.getImageCache().removeImageForKey(ParseModelAbstract.getPoint(photo), fromDisk: true);
//            return BFTask(result:true);
//        }
//        return BFTask(error: NSError.getError(IEAErrorType.LocalImage, description: "\(photo.printDescription())"))

        return null;
    }

    @Override
    public Task<Object> generateTakenPhoto(Bitmap image,Photo model) {
//        Bitmap orignailImage = ImageOptimizeUtils.generateOriginalImage(image)

//        return super.saveTakenPhoto(orignailImage, model: model)
        return null;
    }



}
