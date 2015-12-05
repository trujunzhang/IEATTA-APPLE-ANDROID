package com.ieatta.com.parse.debugspec;

import android.test.InstrumentationTestCase;

import com.ieatta.com.parse.ParseAPI;

import junit.framework.TestCase;

import android.test.InstrumentationTestCase;
import android.virtualbreak.com.debug.AppDebugManager;
import android.virtualbreak.com.debug.ParseLocalDatabase;

import com.ieatta.com.parse.ParseAPI;
import com.ieatta.com.parse.ParseModelQuery;
import com.ieatta.com.parse.async.ParseAsyncHandler;
import com.ieatta.com.parse.async.ParseAsyncTimer;
import com.ieatta.com.parse.models.Event;
import com.ieatta.com.parse.models.Team;
import com.ieatta.com.parse.models.enums.PQueryModelType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 12/5/15.
 */
public class ParseModelQuerySpec extends InstrumentationTestCase {

    public void setUp() throws Exception {
        super.setUp();
        ParseAPI.setup(this.getInstrumentation().getContext());

    }

    private void createModels() {
        final Team whteam = new Team("wh", "wh@gmail.com", "wh.st", 123);
        final Team djteam = new Team("jd", "dj@gmail.com", "dj.st", 234);

        whteam.pinInBackgroundForModel().onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                return djteam.pinInBackgroundForModel();
            }
        }).onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                return ParseLocalDatabase.queryLocalDatastoreInBackground(new Team().makeParseQuery(), PQueryModelType.Team);
            }
        }).continueWith(new Continuation<Void, Object>() {
            @Override
            public Object then(Task<Void> task) throws Exception {
                if (task.isFaulted()) {
                    int x = 0;

                }
                return null;
            }
        });

    }

    public void testUnpinInBackground() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);

        this.createModels();

//        signal.countDown();

        signal.await(10000, TimeUnit.SECONDS);
    }
}