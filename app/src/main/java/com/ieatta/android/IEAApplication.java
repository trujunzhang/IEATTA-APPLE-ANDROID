package com.ieatta.android;

import android.app.Application;
import android.virtualbreak.com.debug.AppDebugManager;
import android.virtualbreak.com.debug.ParseLocalDatabase;
import android.virtualbreak.com.manualdatabase.migration.CompareDatabaseUtils;
import android.virtualbreak.com.manualdatabase.migration.MigrateUtils;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.android.apps.AppConfigure;
import com.ieatta.com.parse.ParseAPI;
import com.ieatta.com.parse.async.ParseAsyncTimer;
import com.ieatta.com.parse.engine.realm.models.DBNewRecord;
import com.ieatta.com.parse.models.NewRecord;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.ieatta.com.parse.utils.cache.CacheImageUtils;

import bolts.Continuation;
import bolts.Task;
import io.realm.Realm;

/**
 * Created by djzhang on 11/30/15.
 */
public class IEAApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // **** Important ****
        EnvironmentUtils.sharedInstance.registerGlobalContext(this);

        AppConfigure.setup(this);
        ParseAPI.setup(this);

        /// Clean Cache folder contains downloaded original images.
//        CacheImageUtils.sharedInstance.clearCacheDisk();

        // Async database in background automately
        // TODO: djzhang(used)
        new ParseAsyncTimer().prepareTimer();

        /**
         Show all tables data on the offline database,when have some issues.

         */
        AppDebugManager.show();

//        new MigrateUtils().executeMigrate()
//                .onSuccess(new Continuation<Void, Object>() {
//                    @Override
//                    public Object then(Task<Void> task) throws Exception {
//                        return ParseLocalDatabase.queryLocalDatastoreInBackground(new NewRecord().makeLocalQuery(), PQueryModelType.NewRecord);
//                    }
//                });

    }
}
