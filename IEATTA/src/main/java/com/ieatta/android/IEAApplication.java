package com.ieatta.android;

import android.app.Application;
import android.location.Location;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.ieatta.BaseApp;

import org.ieatta.parse.ParseAPI;
import org.ieatta.server.recurring.SyncRecurringTask;

public class IEAApplication extends BaseApp {
    public Location lastLocation;

    /**
     * Singleton instance of IEAApp
     */
    private static IEAApplication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;

        // **** Important ****
        EnvironmentUtils.sharedInstance.registerGlobalContext(this);

        ParseAPI.setup(this);

        Fresco.initialize(this);

        /// Clean Cache folder contains downloaded original images.
//        CacheImageUtils.sharedInstance.clearCacheDisk();

        // Async database in background automately
        // TODO: djzhang(used)
        new SyncRecurringTask().prepareTimer();

        /**
         Show all tables data on the offline database,when have some issues.

         */
//        AppDebugManager.show();
    }


    /**
     * Returns the singleton instance of the IEAApp
     * <p/>
     * This is ok, since android treats it as a singleton anyway.
     */
    public static IEAApplication getInstance() {
        return INSTANCE;
    }

}
