package com.ieatta.android;

import android.app.Application;
import android.yelp.com.commonlib.EnvironmentUtils;
import org.ieatta.parse.ParseAPI;

public class IEAApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // **** Important ****
        EnvironmentUtils.sharedInstance.registerGlobalContext(this);

        ParseAPI.setup(this);

        /// Clean Cache folder contains downloaded original images.
//        CacheImageUtils.sharedInstance.clearCacheDisk();

        // Async database in background automately
        // TODO: djzhang(used)
//        new ParseAsyncTimer().prepareTimer();

        /**
         Show all tables data on the offline database,when have some issues.

         */
//        AppDebugManager.show();
    }
}
