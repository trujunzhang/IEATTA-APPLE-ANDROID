package com.ieatta.android;

import android.app.Application;
import android.virtualbreak.com.debug.AppDebugManager;

/**
 * Created by djzhang on 11/30/15.
 */
public class IEAApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        AppDebugManager.show();
    }
}
