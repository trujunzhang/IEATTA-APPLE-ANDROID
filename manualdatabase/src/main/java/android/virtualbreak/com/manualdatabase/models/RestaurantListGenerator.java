package android.virtualbreak.com.manualdatabase.models;

import android.virtualbreak.com.manualdatabase.R;

import com.ieatta.com.parse.models.Restaurant;
import com.parse.ParseGeoPoint;
import java.util.LinkedList;

/**
 * Created by djzhang on 7/18/15.
 */
public class RestaurantListGenerator {
    private static LinkedList<Restaurant> RestaurantListDefault = new LinkedList<Restaurant>();

    static {
        // 1. nearly(3)
        RestaurantListDefault.add(new Restaurant("george mende", new ParseGeoPoint(40.738720, -73.993836), "Digby, NS, Canada", "Fresno, Mississippi", R.drawable.rest01));
        RestaurantListDefault.add(new Restaurant("Basta Pasta", new ParseGeoPoint(40.738821, -73.994026), "Cookson State Forest", "Killeen, Texas", R.drawable.rest02));
        RestaurantListDefault.add(new Restaurant("Petite Abeille", new ParseGeoPoint(40.738663, -73.994514), "West Hartford", "Dallas, Nevada", R.drawable.rest03));
        // 2. nearly(2)
        RestaurantListDefault.add(new Restaurant("Zio Ristorante", new ParseGeoPoint(40.739672, -73.992478), "Kensington, Berlin", "Lakeland, Massachusetts", R.drawable.rest04));
        RestaurantListDefault.add(new Restaurant("Spoon", new ParseGeoPoint(40.739672, -73.992478), "Middletown", "Farmers Branch, Connecticut", R.drawable.rest05));
        // 3. nearly(1)
        RestaurantListDefault.add(new Restaurant("Francisco's Centro Vasco", new ParseGeoPoint(40.743936, -73.994795), "Wallingford", "Baltimore, Arizona", R.drawable.rest06));
    }

    public static LinkedList<Restaurant> getCurrentRestaurantList() {
        return RestaurantListDefault;
    }


}
