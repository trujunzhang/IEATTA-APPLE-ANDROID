package com.ieatta.android.notification;

/**
 * Created by djzhang on 12/15/15.
 */
public class NSNotification {
    private NSNotification self = this;

    public NotifyType type;
    public Object anObject;

    public NSNotification(NotifyType type, Object anObject) {
        self.type = type;
        self.anObject = anObject;
    }
}
