package com.ieatta.android.observers;

import android.virtualbreak.com.manualdatabase.models.RestaurantListGenerator;

import com.parse.ParseGeoPoint;

/**
 * Created by djzhang on 12/1/15.
 */
public class LocationObserver {
    private LocationObserver self =this;
    public static final LocationObserver sharedInstance = new LocationObserver();
    private ParseGeoPoint currentGeoPoint;

    public ParseGeoPoint getCurrentPFGeoPoint()  {

        //TODO: djzhang(test)(Special GeoPoint)
                        return RestaurantListGenerator.getCurrentLocationAndCulculateDistance();

//        return self.currentGeoPoint;
    }
}
