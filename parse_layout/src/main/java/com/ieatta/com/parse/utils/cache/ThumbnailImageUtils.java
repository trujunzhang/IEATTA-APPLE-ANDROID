package com.ieatta.com.parse.utils.cache;

import android.graphics.Bitmap;

import com.ieatta.com.parse.models.Photo;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;

import bolts.Task;

/**
 * Created by djzhang on 11/30/15.
 */
public class ThumbnailImageUtils extends AbstractImageUtils{

    final static ThumbnailImageUtils sharedInstance  = new  ThumbnailImageUtils();

    /**
     This SDImageCache constructor's namespace is "Thumbnail". Store images on the folder: "Thumbnail/com.virtualbreak.IEATTA.thumbnail"

     - returns: SDImageCache's instance
     */
    @Override
    protected UnlimitedDiskCache getImageCache() {
//        return SDImageCache.sharedImageCache();
        return null;
    }

    /**
     First of all, generate a thumbnail image from the original image, then store the thumbnail image as a offline cache file.

     - parameter image:           original image
     - parameter model:           Photo's instance
     - parameter completionBlock: callback variable
     */
    @Override
    public Task<Object> generateTakenPhoto(Bitmap image,Photo model) {
//        Bitmap thumbnailImage = ImageOptimizeUtils.generateOriginalImage(image)

//        return super.saveTakenPhoto(thumbnailImage, model: model)
        return null;
    }


}
