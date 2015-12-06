package com.ieatta.com.parse.debugspec.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.InstrumentationTestCase;
import android.virtualbreak.com.debug.R;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.com.parse.models.Photo;
import com.ieatta.com.parse.utils.cache.ImageOptimizeUtils;
import com.ieatta.com.parse.utils.cache.ThumbnailImageUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;

import java.io.File;
import java.net.URI;
import java.util.List;

/**
 * Created by djzhang on 12/6/15.
 */
public class ThumbnailImageUtilsSpec extends InstrumentationTestCase{
    private ThumbnailImageUtilsSpec self = this;
    private Context context;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        self.context = this.getInstrumentation().getContext();
        EnvironmentUtils.sharedInstance.registerGlobalContext(this.context);
    }

    public void txestConstructor() throws Exception {
        UnlimitedDiskCache imageCache = ThumbnailImageUtils.sharedInstance.getImageCache();
        List<File> list = imageCache.getCacheFileList();
    }


    public void testGenerateTakenPhoto() throws Exception {
        Bitmap bm = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.rest01);

//        bm = ImageOptimizeUtils.generateOriginalImage(bm);
        bm = ImageOptimizeUtils.generateThumbnailImage(bm);

        Photo photo = new Photo();
        ThumbnailImageUtils.sharedInstance.saveTakenPhoto(bm, photo);

        File file = ThumbnailImageUtils.sharedInstance.getTakenPhoto(photo);

//        URI uri = new URI(file.getAbsolutePath());

        bm = null;

    }
}
