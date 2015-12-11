package android.virtualbreak.com.manualdatabase;

import com.ieatta.com.parse.models.Event;
import com.ieatta.com.parse.models.Recipe;
import com.ieatta.com.parse.models.Restaurant;
import com.ieatta.com.parse.models.Team;

/**
 * Created by djzhang on 12/3/15.
 */
public class ActivityModelDebug {

    public static Restaurant getRestaurantForRestaurantDetail() {
        Restaurant restaurant = new Restaurant();
        restaurant.objectUUID = "1CE562A4-A978-4B75-9B7B-2F3CF9F42A04";
        restaurant.displayName = "Francisco's Centro Vasco";
        return restaurant;
    }

    public static Event getEventForEventDetail() {
        Event event = new Event();
        event.objectUUID = "C9970CF0-6925-42C5-8C52-4E37A8A250A1";
        event.displayName = "California Hot Sauce Expo";

        event.belongToModel = ActivityModelDebug.getRestaurantForRestaurantDetail();
        return event;
    }

    public static Team getOrderedPeople() {
        Team team = new Team();
        team.objectUUID = "F72F106D-8759-4932-BE76-10D89FBDEB79";
        team.displayName = "Frank Gregory";
        return team;
    }

    public static Recipe getOrderedRecipe() {
        Recipe recipe=new Recipe();
        recipe.objectUUID ="641619A3-9215-4DAA-9027-A5BCB7C65943";
//                "B46474CD-FA02-4D7E-AE0F-B9439F4C8E8F";
        recipe.displayName = "Amazing sticky rice!";
        recipe.cost = 1.2f;
        return recipe;
    }
}
