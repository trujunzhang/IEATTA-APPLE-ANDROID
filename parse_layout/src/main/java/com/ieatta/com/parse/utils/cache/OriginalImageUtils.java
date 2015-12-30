package com.ieatta.com.parse.utils.cache;

import android.graphics.Bitmap;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Photo;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.io.FileNotFoundException;

import bolts.Task;

/**
 * Created by djzhang on 11/30/15.
 */
public class OriginalImageUtils extends AbstractImageUtils {

    public final static OriginalImageUtils sharedInstance = new OriginalImageUtils();

    /**
     * This SDImageCache constructor's namespace is "Original". Store images on the folder: "Original/com.virtualbreak.IEATTA.original"
     * <p/>
     * - returns: SDImageCache's instance
     */
    @Override
    protected UnlimitedDiskCache getImageCache() {
        File cacheDir = StorageUtils.getCacheDirectory(EnvironmentUtils.sharedInstance.getGlobalContext(), "original");
        return new UnlimitedDiskCache(cacheDir);
    }

    /**
     * Remove offline original image.
     * <p/>
     * - parameter photo: photo's instance
     */
    public Task<Boolean> removeOriginalImage(Photo photo) {
        boolean imageExist = this.diskImageExistsWithKey(photo);
        if (imageExist == false) {
            return Task.forError(new FileNotFoundException(photo.printDescription()));
        }
        boolean isRemove = this.getImageCache().remove(ParseModelAbstract.getPoint(photo));

        return Task.forResult(isRemove);
    }

    @Override
    public Task<Bitmap> generateTakenPhoto(Bitmap image, Photo model) {
        Bitmap originalImage = ImageOptimizeUtils.generateOriginalImage(image);

        return super.saveTakenPhoto(originalImage, model);
    }
}
