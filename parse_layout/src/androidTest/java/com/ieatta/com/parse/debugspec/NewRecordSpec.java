package com.ieatta.com.parse.debugspec;

import android.test.InstrumentationTestCase;

import com.ieatta.com.parse.ParseAPI;
import com.ieatta.com.parse.async.ParseAsyncHandler;
import com.ieatta.com.parse.async.ParseAsyncTimer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by djzhang on 11/30/15.
 */
public class NewRecordSpec  extends InstrumentationTestCase{

    @Override
    public void setUp() throws Exception {
        super.setUp();
        ParseAPI.setup(this.getInstrumentation().getContext());
    }

    public void testAsync() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);

        ParseAsyncHandler.sharedInstance.PullObjectsFromServer();

//        signal.countDown();

        signal.await(10000, TimeUnit.SECONDS);

    }
}

