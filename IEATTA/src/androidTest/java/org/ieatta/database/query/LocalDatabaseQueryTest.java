package org.ieatta.database.query;

import android.support.test.runner.AndroidJUnit4;

import org.ieatta.database.provide.ReviewType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import bolts.Continuation;
import bolts.Task;

@RunWith(AndroidJUnit4.class)
public class LocalDatabaseQueryTest {
    @Before
    public void setUp() throws Exception {


    }

    @Test
    public void testFetchRatingReview() throws Exception {
        final String _restaurantUUID = "1CE562A4-A978-4B75-9B7B-2F3CF9F42A04";//this.entry.getHPara();
        LocalDatabaseQuery.queryRatingInReviews(_restaurantUUID, ReviewType.Review_Restaurant).continueWith(new Continuation<Integer, Void>() {
            @Override
            public Void then(Task<Integer> task) throws Exception {
                Exception error = task.getError();
                int rating = task.getResult();
                return null;
            }
        });
    }
}