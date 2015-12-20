package com.ieatta.com.parse.utils.cache;

import android.yelp.com.commonlib.EnvironmentUtils;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by djzhang on 11/30/15.
 */
public class CacheImageUtils extends AbstractImageUtils {

    public final static CacheImageUtils sharedInstance = new CacheImageUtils();

    /**
     * This SDImageCache constructor's namespace is "Cache". Store images on the folder: "Cache/com.virtualbreak.IEATTA.cache"
     * <p/>
     * - returns: SDImageCache's instance
     */
    @Override
    protected UnlimitedDiskCache getImageCache() {
        File cacheDir = StorageUtils.getCacheDirectory(EnvironmentUtils.sharedInstance.getGlobalContext(), "cache");
        return new UnlimitedDiskCache(cacheDir);
    }

    public void clearCacheDisk() {
        this.getImageCache().clear();
    }

}
