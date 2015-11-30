package com.ieatta.com.parse.utils.cache;

import android.graphics.Bitmap;

import com.ieatta.com.parse.models.Photo;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;

import bolts.Task;

/**
 * Created by djzhang on 11/30/15.
 */
public class CacheImageUtils extends AbstractImageUtils{

    final static CacheImageUtils sharedInstance  = new  CacheImageUtils();

    /**
     This SDImageCache constructor's namespace is "Cache". Store images on the folder: "Cache/com.virtualbreak.IEATTA.cache"

     - returns: SDImageCache's instance
     */
    @Override
    protected UnlimitedDiskCache getImageCache() {
//        return SDImageCache.sharedImageCache();
        return null;
    }

    @Override
    public Task<Object> generateTakenPhoto(Bitmap image, Photo model) {
        return null;
    }

    public void clearCacheDisk() {
//        this.getImageCache().clearDisk();
    }

}
