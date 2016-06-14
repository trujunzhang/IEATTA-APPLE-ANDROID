package org.ieatta.location;


import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;


public class LocationHandler {
    private static final int REQUEST_CHECK_SETTINGS = 0;

    private final Context context;

    private ReactiveLocationProvider locationProvider;
    private Observable<Location> locationUpdatesObservable;
    private Subscription updatableLocationSubscription;

    public LocationHandler(Context context) {
        this.context = context;
    }

    public void showLastLocation(Action1<Location> action) {
        ReactiveLocationProvider locationProvider = new ReactiveLocationProvider(context);
        locationProvider.getLastKnownLocation().subscribe(action);
    }

    public void showUpdateLocation(Action1<Location> action) {
        final LocationRequest locationRequest = LocationRequest.create() //standard GMS LocationRequest
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setNumUpdates(5)
                .setInterval(100);

        locationProvider = new ReactiveLocationProvider(context);
        locationUpdatesObservable = locationProvider
                .checkLocationSettings(
                        new LocationSettingsRequest.Builder()
                                .addLocationRequest(locationRequest)
                                .setAlwaysShow(true)  //Refrence: http://stackoverflow.com/questions/29824408/google-play-services-locationservices-api-new-option-never
                                .build()
                )
                .doOnNext(new Action1<LocationSettingsResult>() {
                    @Override
                    public void call(LocationSettingsResult locationSettingsResult) {
                        Status status = locationSettingsResult.getStatus();
                        if (status.getStatusCode() == LocationSettingsStatusCodes.RESOLUTION_REQUIRED) {
//                            try {
//                                status.startResolutionForResult(activity, REQUEST_CHECK_SETTINGS);
//                            } catch (IntentSender.SendIntentException th) {
//                                Log.e("MainActivity", "Error opening settings activity.", th);
//                            }
                        }
                    }
                })
                .flatMap(new Func1<LocationSettingsResult, Observable<Location>>() {
                    @Override
                    public Observable<Location> call(LocationSettingsResult locationSettingsResult) {
                        return locationProvider.getUpdatedLocation(locationRequest);
                    }
                });

        updatableLocationSubscription = locationUpdatesObservable.subscribe(action, new ErrorHandler());
    }

    private class ErrorHandler implements Action1<Throwable> {
        @Override
        public void call(Throwable throwable) {
            Log.d("LocationActivity", "Error occurred", throwable);
        }
    }

    public void onStop() {
        if (updatableLocationSubscription != null)
            updatableLocationSubscription.unsubscribe();
    }

}
