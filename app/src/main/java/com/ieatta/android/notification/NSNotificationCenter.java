package com.ieatta.android.notification;

import de.greenrobot.event.EventBus;

/**
 * Created by djzhang on 12/15/15.
 */
public class NSNotificationCenter {
    private static NSNotificationCenter instance = new NSNotificationCenter();

    public static NSNotificationCenter defaultCenter() {
        return instance;
    }

    public void postNotificationName(NotifyType type, Object anObject) {
        EventBus.getDefault().post(
                new NotifyEvent(type, anObject));
    }

}
