package com.ieatta.com.parse.debugspec.migrate;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.InstrumentationTestCase;
import android.virtualbreak.com.debug.R;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.com.parse.ParseJsoner;
import com.ieatta.com.parse.models.Photo;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.ieatta.com.parse.utils.cache.ImageOptimizeUtils;
import com.ieatta.com.parse.utils.cache.ThumbnailImageUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;

import java.io.File;
import java.util.List;

/**
 * Created by djzhang on 12/6/15.
 */
public class ReadJsonFileSpec extends InstrumentationTestCase {
    private ReadJsonFileSpec self = this;
    private Context context;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        self.context = this.getInstrumentation().getContext();
        EnvironmentUtils.sharedInstance.registerGlobalContext(this.context);
    }

    public void testConstructor() throws Exception {
        ParseJsoner.readJsonFile(PQueryModelType.Event);
    }


}
