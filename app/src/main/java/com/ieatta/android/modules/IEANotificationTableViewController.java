package com.ieatta.android.modules;

import android.os.Bundle;

import com.ieatta.android.notification.NSNotificationCenter;

import de.greenrobot.event.EventBus;

/**
 * Created by djzhang on 12/15/15.
 */
public class IEANotificationTableViewController extends IEAAppTableViewController{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //register EventBus.
        EventBus.getDefault().register(this);
    }


    public void onEventPostThread(Object event) {
        NSNotificationCenter.defaultCenter().postNotificationName(PAModelCreatedRestaurantNotification,  null);
    }
}
