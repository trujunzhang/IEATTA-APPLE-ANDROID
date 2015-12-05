package com.ieatta.com.parse.debugspec;

import android.test.InstrumentationTestCase;

import com.ieatta.com.parse.ParseAPI;

import junit.framework.TestCase;

import android.test.InstrumentationTestCase;

import com.ieatta.com.parse.ParseAPI;
import com.ieatta.com.parse.async.ParseAsyncHandler;
import com.ieatta.com.parse.async.ParseAsyncTimer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by djzhang on 12/5/15.
 */
public class ParseModelQuerySpec extends InstrumentationTestCase {

    public void setUp() throws Exception {
        super.setUp();
        ParseAPI.setup(this.getInstrumentation().getContext());
    }

    public void testUnpinInBackground() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);

//        signal.countDown();

        signal.await(10000, TimeUnit.SECONDS);
    }
}