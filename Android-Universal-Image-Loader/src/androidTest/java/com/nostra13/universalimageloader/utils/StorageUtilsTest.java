package com.nostra13.universalimageloader.utils;

import android.test.InstrumentationTestCase;

import junit.framework.TestCase;

import java.io.File;

/**
 * Created by djzhang on 11/30/15.
 */
public class StorageUtilsTest extends InstrumentationTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();


    }

    public void testCacheDir() throws Exception {
        File cacheDir = StorageUtils.getCacheDirectory(this.getInstrumentation().getContext());

        int x = 0;

    }
}