package com.ieatta.com.parse.debugspec.query;

import android.test.InstrumentationTestCase;
import android.virtualbreak.com.debug.ParseLocalDatabase;

import com.ieatta.com.parse.ParseAPI;
import com.ieatta.com.parse.engine.realm.LocalQuery;
import com.ieatta.com.parse.models.NewRecord;
import com.ieatta.com.parse.models.Team;
import com.ieatta.com.parse.models.enums.PQueryModelType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 12/5/15.
 */
public class ParseModelUnpinLocalSpec extends InstrumentationTestCase {

    public void setUp() throws Exception {
        super.setUp();
        ParseAPI.setup(this.getInstrumentation().getContext());
    }

    public void txestUnpinInBackground() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);

        final Team whTeam = new Team("wh", "wh@gmail.com", "wh.st", 123);
        final LocalQuery whQuery = whTeam.createQueryByObjectUUID();
        final LocalQuery teamCountQurey = whTeam.makeLocalQuery();

        final Team countTeam = new Team();

        final int[] expectCount = {-1, 1};
        countTeam.countLocalObjects(teamCountQurey).onSuccessTask(new Continuation<Integer, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Integer> task) throws Exception {
                expectCount[0] = task.getResult();
                // Step02: Save it.
                return whTeam.saveInBackground();
            }
        }).onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                // Step03: List table.
                return ParseLocalDatabase.queryLocalDatastoreInBackground(new Team().makeLocalQuery(), PQueryModelType.Team);
            }
        }).onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                /**
                 * **** Important ****
                 * Test function here
                 */
                // Step04: Delete it.
                return whTeam.deleteInBackground(whQuery);
            }
        }).onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                // Step05: List table.
                return ParseLocalDatabase.queryLocalDatastoreInBackground(new Team().makeLocalQuery(), PQueryModelType.Team);
            }
        }).onSuccessTask(new Continuation<Void, Task<Integer>>() {
            @Override
            public Task<Integer> then(Task<Void> task) throws Exception {
                return countTeam.countLocalObjects(teamCountQurey);
            }
        }).onSuccess(new Continuation<Integer, Void>() {
            @Override
            public Void then(Task<Integer> task) throws Exception {
                expectCount[1] = task.getResult();
                return null;
            }
        }).continueWith(new Continuation<Void, Object>() {
            @Override
            public Object then(Task<Void> task) throws Exception {
                if (task.isFaulted()) {
                    Exception error = task.getError();
                    String message = error.getLocalizedMessage();
                    String errorMessage = error.getMessage();

                    fail(message);

                    signal.countDown();
                    return null;
                }
                assertEquals("Save/Delete ", expectCount[0], expectCount[1]);
                signal.countDown();
                return null;
            }
        });

        signal.await(10000, TimeUnit.SECONDS);
    }


    public void txestUnpinEmptyInBackground() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);

        final Team whTeam = new Team("empty", "empty@gmail.com", "empty.st", 123);
        final LocalQuery whQuery = whTeam.createQueryByObjectUUID();
        final LocalQuery teamCountQuery = whTeam.makeLocalQuery();

        final Team countTeam = new Team();

        final int[] expectCount = {-1, 1};
        countTeam.countLocalObjects(teamCountQuery).onSuccessTask(new Continuation<Integer, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Integer> task) throws Exception {
                expectCount[0] = task.getResult();
                // Step02: List table.
                return ParseLocalDatabase.queryLocalDatastoreInBackground(new Team().makeLocalQuery(), PQueryModelType.Team);
            }
        }).onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                /**
                 * **** Important ****
                 * Test function here
                 */
                // Step04: Delete it.
                return whTeam.deleteInBackground(whQuery);
            }
        }).onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                // Step05: List table.
                return ParseLocalDatabase.queryLocalDatastoreInBackground(new Team().makeLocalQuery(), PQueryModelType.Team);
            }
        }).onSuccessTask(new Continuation<Void, Task<Integer>>() {
            @Override
            public Task<Integer> then(Task<Void> task) throws Exception {
                return countTeam.countLocalObjects(teamCountQuery);
            }
        }).onSuccess(new Continuation<Integer, Void>() {
            @Override
            public Void then(Task<Integer> task) throws Exception {
                expectCount[1] = task.getResult();
                return null;
            }
        }).continueWith(new Continuation<Void, Object>() {
            @Override
            public Object then(Task<Void> task) throws Exception {
                if (task.isFaulted()) {
                    Exception error = task.getError();
                    String message = error.getLocalizedMessage();
                    String errorMessage = error.getMessage();
                    fail(message);
                    signal.countDown();
                    return null;
                }
                assertEquals("Save/Delete ", expectCount[0], expectCount[1]);
                signal.countDown();
                return null;
            }
        });

        signal.await(10000, TimeUnit.SECONDS);
    }


    public void txestUnpinInBackgroundWithNewRecord() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);

        final Team whTeam = new Team("whWithNewRecord", "whWithNewRecord@gmail.com", "whWithNewRecord.st", 123);

        final Team countTeam = new Team();
        final LocalQuery countTeamQurey = countTeam.makeLocalQuery();

        final NewRecord countNewRecord = new NewRecord();
        final LocalQuery countNewRecordQuery = countNewRecord.makeLocalQuery();

        final int[] expectCount = {-1, 1, -1, 1};

        countTeam.countLocalObjects(countTeamQurey).onSuccessTask(new Continuation<Integer, Task<Integer>>() {
            @Override
            public Task<Integer> then(Task<Integer> task) throws Exception {
                expectCount[0] = task.getResult();
                return countNewRecord.countLocalObjects(countNewRecordQuery);
            }
        }).onSuccessTask(new Continuation<Integer, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Integer> task) throws Exception {
                expectCount[2] = task.getResult();
                // Step02: Save it.
                return whTeam.saveInBackgroundWithNewRecord();
            }
        }).onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                // Step03: List table.
                return ParseLocalDatabase.queryLocalDatastoreInBackground(new Team().makeLocalQuery(), PQueryModelType.Team);
            }
        }).onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                // Step03: List table.
                return ParseLocalDatabase.queryLocalDatastoreInBackground(new NewRecord().makeLocalQuery(), PQueryModelType.NewRecord);
            }
        }).onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                /**
                 * **** Important ****
                 * Test function here
                 */
                // Step04: Delete it.
                return whTeam.unpinInBackgroundWithNewRecord();
            }
        }).onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                // Step05: List table.
                return ParseLocalDatabase.queryLocalDatastoreInBackground(new Team().makeLocalQuery(), PQueryModelType.Team);
            }
        }).onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                // Step05: List table.
                return ParseLocalDatabase.queryLocalDatastoreInBackground(new NewRecord().makeLocalQuery(), PQueryModelType.NewRecord);
            }
        }).onSuccessTask(new Continuation<Void, Task<Integer>>() {
            @Override
            public Task<Integer> then(Task<Void> task) throws Exception {
                return countTeam.countLocalObjects(countTeamQurey);
            }
        }).onSuccessTask(new Continuation<Integer, Task<Integer>>() {
            @Override
            public Task<Integer> then(Task<Integer> task) throws Exception {
                expectCount[1] = task.getResult();
                return countNewRecord.countLocalObjects(countNewRecordQuery);
            }
        }).onSuccess(new Continuation<Integer, Void>() {
            @Override
            public Void then(Task<Integer> task) throws Exception {
                expectCount[3] = task.getResult();
                return null;
            }
        }).continueWith(new Continuation<Void, Object>() {
            @Override
            public Object then(Task<Void> task) throws Exception {
                if (task.isFaulted()) {
                    Exception error = task.getError();
                    String message = error.getLocalizedMessage();
                    String errorMessage = error.getMessage();
                    fail(message);
                    signal.countDown();
                    return null;
                }
                assertEquals("Save/Delete model with NewRecord ", expectCount[0], expectCount[1]);
                assertEquals("Save/Delete model with NewRecord ", expectCount[2], expectCount[3]);
                signal.countDown();
                return null;
            }
        });

        signal.await(10000, TimeUnit.SECONDS);
    }


    public void txestUnpinInBackgroundWithoutNewRecord() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);

        final Team whTeam = new Team("whWithNewRecord", "whWithNewRecord@gmail.com", "whWithNewRecord.st", 123);

        final Team countTeam = new Team();
        final LocalQuery teamCountQurey = countTeam.makeLocalQuery();
        final NewRecord countNewRecord = new NewRecord();
        final LocalQuery newRecordCountQuery = countNewRecord.makeLocalQuery();


        final int[] expectCount = {-1, 1, -1, 1};
        countTeam.countLocalObjects(teamCountQurey).onSuccessTask(new Continuation<Integer, Task<Integer>>() {
            @Override
            public Task<Integer> then(Task<Integer> task) throws Exception {
                expectCount[0] = task.getResult();
                return countNewRecord.countLocalObjects(newRecordCountQuery);
            }
        }).onSuccessTask(new Continuation<Integer, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Integer> task) throws Exception {
                expectCount[2] = task.getResult();
                /**
                 * Step02: Save it.(without NewRecord)
                 */
                return whTeam.saveInBackground();
            }
        }).onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                // Step03: List table.
                return ParseLocalDatabase.queryLocalDatastoreInBackground(new Team().makeLocalQuery(), PQueryModelType.Team);
            }
        }).onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                // Step03: List table.
                return ParseLocalDatabase.queryLocalDatastoreInBackground(new NewRecord().makeLocalQuery(), PQueryModelType.NewRecord);
            }
        }).onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                /**
                 * **** Important ****
                 * Test function here
                 */
                // Step04: Delete it.
                return whTeam.unpinInBackgroundWithNewRecord();
            }
        }).onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                // Step05: List table.
                return ParseLocalDatabase.queryLocalDatastoreInBackground(new Team().makeLocalQuery(), PQueryModelType.Team);
            }
        }).onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                // Step03: List table.
                return ParseLocalDatabase.queryLocalDatastoreInBackground(new NewRecord().makeLocalQuery(), PQueryModelType.NewRecord);
            }
        }).onSuccessTask(new Continuation<Void, Task<Integer>>() {
            @Override
            public Task<Integer> then(Task<Void> task) throws Exception {
                return countTeam.countLocalObjects(teamCountQurey);
            }
        }).onSuccessTask(new Continuation<Integer, Task<Integer>>() {
            @Override
            public Task<Integer> then(Task<Integer> task) throws Exception {
                expectCount[1] = task.getResult();
                return countNewRecord.countLocalObjects(newRecordCountQuery);
            }
        }).onSuccess(new Continuation<Integer, Void>() {
            @Override
            public Void then(Task<Integer> task) throws Exception {
                expectCount[3] = task.getResult();
                return null;
            }
        }).continueWith(new Continuation<Void, Object>() {
            @Override
            public Object then(Task<Void> task) throws Exception {
                if (task.isFaulted()) {
                    Exception error = task.getError();
                    String message = error.getLocalizedMessage();
                    String errorMessage = error.getMessage();

                    fail(message);
                    signal.countDown();
                    return null;
                }
                assertEquals("Save/Delete model with NewRecord ", expectCount[0], expectCount[1]);
                assertEquals("Save/Delete model with NewRecord ", expectCount[2], expectCount[3]);
                signal.countDown();
                return null;
            }
        });

        signal.await(10000, TimeUnit.SECONDS);
    }

}