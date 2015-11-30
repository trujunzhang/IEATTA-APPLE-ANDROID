package com.ieatta.com.parse.debugspec;

import android.test.InstrumentationTestCase;

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
    }

    public void testAsync() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);

        new ParseAsyncTimer().prepareTimer();

//        signal.countDown();

        signal.await(1000, TimeUnit.SECONDS);

    }
}

