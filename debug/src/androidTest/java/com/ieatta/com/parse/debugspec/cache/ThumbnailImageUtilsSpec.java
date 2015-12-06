package com.ieatta.com.parse.debugspec.cache;

import android.test.InstrumentationTestCase;

import com.ieatta.com.parse.utils.cache.ThumbnailImageUtils;

/**
 * Created by djzhang on 12/6/15.
 */
public class ThumbnailImageUtilsSpec extends InstrumentationTestCase{
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testConstructor() throws Exception {
        ThumbnailImageUtils.sharedInstance.getImageCache();
    }

    public void testGenerateTakenPhoto() throws Exception {


    }
}
