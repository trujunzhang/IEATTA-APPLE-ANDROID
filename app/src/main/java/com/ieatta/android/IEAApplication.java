package com.ieatta.android;

import android.app.Application;
import android.virtualbreak.com.debug.AppDebugManager;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.android.apps.AppConfigure;
import com.ieatta.com.parse.ParseAPI;
import com.ieatta.com.parse.utils.cache.CacheImageUtils;

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
        CacheImageUtils.sharedInstance.clearCacheDisk();

        // Async database in background automately
        // TODO: djzhang(used)
//        new ParseAsyncTimer().prepareTimer();

        /**
         Show all tables data on the offline database,when have some issues.

         */
        AppDebugManager.show();

    }
}
