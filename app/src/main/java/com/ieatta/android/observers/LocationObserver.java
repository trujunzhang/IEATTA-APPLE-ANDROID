package com.ieatta.android.observers;

import android.location.Location;
import android.virtualbreak.com.manualdatabase.models.RestaurantListGenerator;

import com.ieatta.android.notification.NSNotificationCenter;
import com.ieatta.android.notification.NotifyType;
import com.parse.Parse;
import com.parse.ParseGeoPoint;

import java.util.Stack;

/**
 * Created by djzhang on 12/1/15.
 */
public class LocationObserver {
    private LocationObserver self =this;
    public static final LocationObserver sharedInstance = new LocationObserver();
    private ParseGeoPoint currentGeoPoint;
    private ParseGeoPoint DefaultGeoPoint =new  ParseGeoPoint( 0.0,  0.0);
    private Stack<ParseGeoPoint> observerStack = new Stack<>();

    public ParseGeoPoint getCurrentPFGeoPoint()  {

        //TODO: djzhang(test)(Special GeoPoint)
                        return RestaurantListGenerator.getCurrentLocationAndCulculateDistance();

//        return self.currentGeoPoint;
    }

    public void updateLocation(Location location) {
        double distance = currentGeoPoint.distanceInKilometersTo(new ParseGeoPoint(location.getLatitude(),location.getLongitude()))*1000;
        if (distance > 10) {
            self.checkLocationAndNotification(location);
        }
    }

    private void checkLocationAndNotification(Location location) {
        boolean isEmpty = self.observerStack.empty();

        self.observerStack.push(new ParseGeoPoint(location.getLatitude(),location.getLongitude()));

        // If the last is empty, then notify.
        if(isEmpty == true){
            notifyCurrentLocationDidChange();
        }
    }

    private void notifyCurrentLocationDidChange() {
        ParseGeoPoint pop = self.observerStack.pop();
        self.currentGeoPoint = pop;
        NSNotificationCenter.defaultCenter().postNotificationName(NotifyType.PACurrentLocationDidChangeNotification, null);
    }


}
