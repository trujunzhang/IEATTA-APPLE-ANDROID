package com.ieatta.android.modules;

import android.os.Bundle;

import com.ieatta.android.IEAPageActivity;
import com.ieatta.android.notification.NSNotification;

import de.greenrobot.event.EventBus;

public class IEANotificationTableViewController extends IEAPageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //register EventBus.
        EventBus.getDefault().register(this);
    }

    public void onEventMainThread(NSNotification note) {
        switch (note.type) {
            case PAModelCreatedRestaurantNotification:
                this.RestaurantWasCreated(note);
                break;
            case PAModelCreateEventNotification:
                this.EventWasCreated(note);
                break;

            /// For classes: (IEAOrderedRecipesViewController,IEAPhotoGalleryViewController)
            case PAModelTakenPhotoNotification:
                this.TakenPhotoWasChanged(note);
                break;

            case PAPeopleSelectedNotification:
                break;
            case PAPeopleCreatedNotification:
                this.PeopleWasCreated(note);
                break;

            case PARecipeCreatedNotification:
                this.RecipeWasCreated(note);
                break;

            case PAReviewPostNotification:
                this.ReviewWasCreated(note);
                break;

            case PACurrentLocationDidChangeNotification:
                this.LocationDidChange(note);
                break;
            case PAFilterDistanceDidChangeNotification:
                break;

            case PAReadReviewsSegmentedValueChanged:
                break;

            case PANotificationUserInfoForReadReviews:
                break;
            case PANotificationChoicePerson:
                this.didSelectPeople(note);
                break;
        }
    }

    protected void didSelectPeople(NSNotification note) {

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
