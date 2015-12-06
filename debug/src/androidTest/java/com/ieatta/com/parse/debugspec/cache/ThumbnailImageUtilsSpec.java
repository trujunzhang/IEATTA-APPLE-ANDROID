package com.ieatta.com.parse.debugspec.cache;

import android.test.InstrumentationTestCase;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.com.parse.utils.cache.ThumbnailImageUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;

/**
 * Created by djzhang on 12/6/15.
 */
public class ThumbnailImageUtilsSpec extends InstrumentationTestCase{
    @Override
    public void setUp() throws Exception {
        super.setUp();
        EnvironmentUtils.sharedInstance.registerGlobalContext(this.getInstrumentation().getContext());
    }

    public void testConstructor() throws Exception {
        UnlimitedDiskCache imageCache = ThumbnailImageUtils.sharedInstance.getImageCache();

    }

    public void testGenerateTakenPhoto() throws Exception {


    }
}
