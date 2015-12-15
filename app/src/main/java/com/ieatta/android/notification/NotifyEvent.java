package com.ieatta.android.notification;

/**
 * Created by djzhang on 12/15/15.
 */
public class NotifyEvent {
    private NotifyEvent self = this;

    private  NotifyType type;
    private  Object anObject;

    public NotifyEvent(NotifyType type, Object anObject) {
    self.type = type;
        self.anObject = anObject;
    }
}
