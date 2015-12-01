package android.virtualbreak.com.manualdatabase;

/**
 * Created by djzhang on 12/1/15.
 */
public class ManualGenerateDatabaseUtils {
    public static void manualOfflineDatabase(){
        if(true){
            return; // ignour
        }

        // =============================================================================
        //        Team
        // =============================================================================
        ParseManualGenerator.createTeamTable();

        // =============================================================================
        //        Restaurant
        // =============================================================================
        ParseManualGenerator.createRestaurantTable();

        //
        // 2. Restaurants with google map address.
        //
        //                ParseManualGenerator.createRestaurantTableForGoogleMapAddress(true)

        //
        // 3. Save photo for restaurant.(Test for uploading to parse.com using parse cloud.)
        //
        //        ParseManualGenerator.createPhotoWithPFFileForRestaurant(true)

    }
}
