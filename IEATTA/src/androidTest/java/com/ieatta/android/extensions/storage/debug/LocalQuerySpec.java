package com.ieatta.android.extensions.storage.debug;

import android.test.InstrumentationTestCase;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.ParseModelLocalQuery;
import com.ieatta.com.parse.ParseModelOnlineQuery;
import com.ieatta.com.parse.engine.realm.LocalQuery;
import com.ieatta.com.parse.models.NewRecord;
import com.ieatta.com.parse.models.Restaurant;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.parse.ParseQuery;

import java.util.List;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 12/31/15.
 */
public class LocalQuerySpec extends InstrumentationTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void tesxxxtQueryNewRecord() throws Exception {
        LocalQuery localQuery = new NewRecord().makeLocalQuery();
        localQuery.whereEqualTo("modelType", PQueryModelType.getInt(PQueryModelType.Team));

        ParseModelLocalQuery.queryFromRealm(PQueryModelType.NewRecord, localQuery)
                .continueWith(new Continuation<List<ParseModelAbstract>, Object>() {
                    @Override
                    public Object then(Task<List<ParseModelAbstract>> task) throws Exception {
                        if (task.isFaulted()) {
                            Exception error = task.getError();
                            String message = error.getMessage();
                        }

                        List<ParseModelAbstract> result = task.getResult();

                        return null;
                    }
                });

    }

    public void testQueryRestaurants() throws Exception {
        ParseQuery query = new Restaurant().makeParseQuery();

        ParseModelOnlineQuery.queryFromParse(PQueryModelType.Restaurant, query)
                .continueWith(new Continuation<List<ParseModelAbstract>, Object>() {
                    @Override
                    public Object then(Task<List<ParseModelAbstract>> task) throws Exception {
                        if (task.isFaulted()) {
                            Exception error = task.getError();
                            String message = error.getMessage();
                        }

                        List<ParseModelAbstract> result = task.getResult();

                        return null;
                    }
                });


    }
}
