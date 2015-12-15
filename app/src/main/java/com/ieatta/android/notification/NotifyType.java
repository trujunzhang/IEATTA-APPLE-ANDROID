package com.ieatta.android.notification;

/**
 * Created by djzhang on 12/15/15.
 */
public enum  NotifyType {
    // Notification names:
    PAModelCreatedRestaurantNotification("PAModelCreatedRestaurantNotification"),
    PAModelCreateEventNotification("PAModelCreateEventNotification"),

    /// For classes: (IEAOrderedRecipesViewController,IEAPhotoGalleryViewController)
    PAModelTakenPhotoNotification("PAModelTakenPhotoNotification"),

    PAPeopleSelectedNotification("PAPeopleSelectedNotification"),
    PAPeopleCreatedNotification("PAPeopleCreatedNotification"),

    PARecipeCreatedNotification("PARecipeCreatedNotification"),

    PAReviewPostNotification("PARviewPostNotification"),

    PACurrentLocationDidChangeNotification("PACurrentLocationDidChangeNotification"),
    PAFilterDistanceDidChangeNotification("PAFilterDistanceDidChangeNotification"),

    PAReadReviewsSegmentedValueChanged("PAReadReviewsSegmentedValueChanged"),

    PANotificationUserInfoForReadReviews("reviewType");

    private String value;

    NotifyType(String value) {
        this.value = value;
    }
}
