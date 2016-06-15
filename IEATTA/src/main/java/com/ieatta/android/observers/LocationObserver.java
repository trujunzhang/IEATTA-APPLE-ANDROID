package com.ieatta.android.observers;

import android.location.Location;

import com.ieatta.android.notification.NSNotificationCenter;
import com.ieatta.android.notification.NotifyType;
import com.parse.ParseGeoPoint;

import java.util.Stack;

public class LocationObserver {
    public static final LocationObserver sharedInstance = new LocationObserver();

    private ParseGeoPoint DefaultGeoPoint = new ParseGeoPoint(0.0, 0.0);
    private ParseGeoPoint currentGeoPoint = DefaultGeoPoint;

    private Stack<ParseGeoPoint> observerStack = new Stack<>();

    public ParseGeoPoint getCurrentPFGeoPoint() {
        return this.currentGeoPoint;
    }

    public void updateLocation(Location location) {
        if (location == null) {
            return;
        }
        ParseGeoPoint newPoint = new ParseGeoPoint(location.getLatitude(), location.getLongitude());
        double distance = (currentGeoPoint.distanceInKilometersTo(newPoint)) * 1000;
        if (distance > 10) {
            this.checkLocationAndNotification(newPoint);
        }
    }

    private void checkLocationAndNotification(ParseGeoPoint newPoint) {
        boolean isEmpty = this.observerStack.empty();

        this.observerStack.push(newPoint);

        // If the last is empty, then notify.
        if (isEmpty == true) {
            notifyCurrentLocationDidChange();
        }
    }

    private void notifyCurrentLocationDidChange() {
        ParseGeoPoint pop = this.observerStack.pop();
        this.currentGeoPoint = pop;
        NSNotificationCenter.defaultCenter().postNotificationName(NotifyType.PACurrentLocationDidChangeNotification, null);
    }


}
