package org.ieatta.location;

import android.location.Location;
import android.widget.TextView;

import rx.functions.Action1;

public class UpdateLocationAction implements Action1<Location> {
    @Override
    public void call(Location location) {

    }
//    private final PageActivity activity;
//
//    public UpdateLocationAction(PageActivity activity) {
//        this.activity = activity;
//    }
//
//    @Override
//    public void call(Location location) {
////        IEAApp.getInstance().lastLocation = location;
//        // TODO: djzhang(test)
//        IEAApp.getInstance().lastLocation = LocationUtil.getLocation();
//        this.activity.updateLocation();
//    }
}
