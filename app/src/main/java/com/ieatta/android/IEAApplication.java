package com.ieatta.android;

import android.app.Application;
import android.virtualbreak.com.debug.AppDebugManager;

import com.ieatta.android.apps.AppConfigure;
import com.ieatta.com.parse.ParseAPI;
import com.ieatta.com.parse.async.ParseAsyncTimer;

/**
 * Created by djzhang on 11/30/15.
 */
public class IEAApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        AppConfigure.setup(this);
        ParseAPI.setup(this);

        AppDebugManager.show();

//        new ParseAsyncTimer().prepareTimer();
    }
}
