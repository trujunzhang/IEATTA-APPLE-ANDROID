package com.ieatta.android.modules;

import android.os.Bundle;

import com.ieatta.android.notification.NSNotification;

import de.greenrobot.event.EventBus;

/**
 * Created by djzhang on 12/15/15.
 */
public class IEANotificationTableViewController extends IEAAppTableViewController {
    private IEANotificationTableViewController self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //register EventBus.
        EventBus.getDefault().register(this);
    }

    public void onEventBackgroundThread(NSNotification note) {
        switch (note.type) {
            case PAModelCreatedRestaurantNotification:
                self.RestaurantWasCreated(note);
                break;
            case PAModelCreateEventNotification:
                self.EventWasCreated(note);
                break;

            /// For classes: (IEAOrderedRecipesViewController,IEAPhotoGalleryViewController)
            case PAModelTakenPhotoNotification:
                self.TakenPhotoWasChanged(note);
                break;

            case PAPeopleSelectedNotification:
                break;
            case PAPeopleCreatedNotification:
                self.PeopleWasCreated(note);
                break;

            case PARecipeCreatedNotification:
                self.RecipeWasCreated(note);
                break;

            case PAReviewPostNotification:
                self.ReviewWasCreated(note);
                break;

            case PACurrentLocationDidChangeNotification:
                self.LocationDidChange(note);
                break;
            case PAFilterDistanceDidChangeNotification:
                break;

            case PAReadReviewsSegmentedValueChanged:
                break;

            case PANotificationUserInfoForReadReviews:
                break;

        }
    }

    protected void ReviewWasCreated(NSNotification note) {

    }

    protected void TakenPhotoWasChanged(NSNotification note) {

    }

    protected void RecipeWasCreated(NSNotification note) {

    }

    protected void EventWasCreated(NSNotification note) {

    }

    protected void PeopleWasCreated(NSNotification note) {

    }


    protected void RestaurantWasCreated(NSNotification note) {

    }

    protected void LocationDidChange(NSNotification note) {

    }

}
