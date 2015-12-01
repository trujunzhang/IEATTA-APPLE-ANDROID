package com.ieatta.android.modules.common;

public class IEAConstants {


    public static final int Gallery_Cell_Collection_Width = 132;
    public static final int Gallery_Cell_Collection_Height = 124;

    enum CellsHeight {
        // Photo gallery
        Gallery_Cell_Height {
            public int toInt() {
                return 140;
            }
        },

//        // GoogleMapAddress
//        case Google_MapAddress          = 44
//
//        case Edit_Cell                  = 56
//        // Near Restaurant
//        case NearRestaurant_Restaurants = 58
//        // Restaurant detail
//        case RestaurantDetail_Header    = 160
//        case RestaurantDetail_Reviews   = 162
//        case RestaurantDetail_Events    = 54
//        // Event detail
//        case EventDetail_Header         = 180
//        case EventDetail_OrderedPeople  = 60
//        // Ordered Recipes
//        case OrderedRecipes_Recipes     = 80
//        // Recipe detail
//        case RecipeDetail_Header        = 175
//        // Edit Event
//        case Edit_Event_WriteReview     = 150
//        // Choice People
//        case People_Info                = 100
//        // Read Reviews
//        case Read_Reviews_Header        = 50
//        case Read_Reviews               = 40
    }

    public static final int DEFAULT_TABLEVIEW_SECTION_HEIGHT = Integer.MIN_VALUE;

    public static final int Edit_Cell_Height = 60;

    public static final int DEFAULT_SECTION_TITLE_HEADER_HEIGHT = 50;

    public static final int SECTION_PHOTO_GALLERY_HEADER_HEIGHT = 56;
    public static final int SECTION_BUTTON_FOOTER_HEIGHT = 60;

    public static final int DEFAULT_SECTION_HEADER_HEIGHT = 4;

    // NSNotification userInfo keys:
    public static final String kPAFilterDistanceKey = "filterDistance";
    public static final String kPALocationKey = "location";

    // Notification names:
    public static final String PAModelCreatedRestaurantNotification = "PAModelCreatedRestaurantNotification";
    public static final String PAModelCreateEventNotification = "PAModelCreateEventNotification";

    /// For classes: (IEAOrderedRecipesViewController,IEAPhotoGalleryViewController)
    public static final String PAModelTakenPhotoNotification = "PAModelTakenPhotoNotification";

    public static final String PAPeopleSelectedNotification = "PAPeopleSelectedNotification";
    public static final String PAPeopleCreatedNotification = "PAPeopleCreatedNotification";

    public static final String PARecipeCreatedNotification = "PARecipeCreatedNotification";

    public static final String PAReviewPostNotification = "PARviewPostNotification";

    public static final String PACurrentLocationDidChangeNotification = "PACurrentLocationDidChangeNotification";
    public static final String PAFilterDistanceDidChangeNotification = "PAFilterDistanceDidChangeNotification";

    public static final String PAReadReviewsSegmentedValueChanged = "PAReadReviewsSegmentedValueChanged";

    public static final String PANotificationUserInfoForReadReviews = "reviewType";


}
