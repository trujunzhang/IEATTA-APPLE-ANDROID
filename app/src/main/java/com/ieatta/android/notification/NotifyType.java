package com.ieatta.android.notification;

/**
 * Created by djzhang on 12/15/15.
 */
public enum NotifyType {
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

    /// For class: IEAReadReviewsBaseViewController(used in iOS)
    PAReadReviewsSegmentedValueChanged("PAReadReviewsSegmentedValueChanged"),

    /// For class: IEAReadReviewsHeaderCell(used in iOS)
    PANotificationUserInfoForReadReviews("reviewType"),
    PANotificationChoicePerson("choiceTeam");

    private String value;

    NotifyType(String value) {
        this.value = value;
    }
}
