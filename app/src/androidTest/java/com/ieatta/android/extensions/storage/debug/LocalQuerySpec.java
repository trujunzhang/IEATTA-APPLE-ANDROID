package com.ieatta.android.extensions.storage.debug;

import android.content.Context;
import android.graphics.Bitmap;
import android.test.InstrumentationTestCase;
import android.util.AttributeSet;
import android.view.View;
import android.yelp.com.commonlib.io.FileUtils;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.engine.realm.LocalQuery;
import com.ieatta.com.parse.models.NewRecord;
import com.ieatta.com.parse.models.Photo;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.ieatta.com.parse.utils.cache.OriginalImageUtils;
import com.ieatta.com.parse.utils.cache.ThumbnailImageUtils;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;

import junit.framework.TestCase;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 12/31/15.
 */
public class LocalQuerySpec extends InstrumentationTestCase{

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testQueryNewRecord() throws Exception {
        LocalQuery localQuery = new  NewRecord().makeLocalQuery();
        localQuery.whereEqualTo("modelType", PQueryModelType.getInt(PQueryModelType.Team));

        localQuery.findInBackground().onSuccessTask(new Continuation() {
            @Override
            public Object then(Task task) throws Exception {
                Object result = task.getResult();

                return null;
            }
        }).continueWith(new Continuation() {
            @Override
            public Object then(Task task) throws Exception {
                if (task.isFaulted()) {
                    Exception error = task.getError();
                    String message = error.getMessage();
                }
                return null;
            }
        });

    }
}
