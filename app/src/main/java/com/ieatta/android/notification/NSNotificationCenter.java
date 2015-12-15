package com.ieatta.android.notification;

/**
 * Created by djzhang on 12/15/15.
 */
public class NSNotificationCenter {
    private static NSNotificationCenter instance = new NSNotificationCenter();

    public static NSNotificationCenter defaultCenter() {
        return instance;
    }

    public void postNotificationName(String aName,Object anObject){

    }

}
