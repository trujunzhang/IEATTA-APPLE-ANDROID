package com.ieatta.com.parse.async;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.enums.PQueryModelType;

/**
 * Created by djzhang on 11/30/15.
 */
public class AsyncPullNotify {

    public static void notify(ParseModelAbstract model) {
        switch (model.getModelType()) {
            case Restaurant:
//                        NSNotificationCenter.defaultCenter().postNotificationName(.PAModelCreatedRestaurantNotification, null);
                break;
            case Review:
                break;
            default:
                break;
        }

    }
}
