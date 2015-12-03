package android.virtualbreak.com.manualdatabase;

import com.ieatta.com.parse.models.Event;
import com.ieatta.com.parse.models.Restaurant;
import com.ieatta.com.parse.models.Team;

/**
 * Created by djzhang on 12/3/15.
 */
public class ActivityModelDebug {

    public static Restaurant getRestaurantForRestaurantDetail() {
        Restaurant restaurant = new Restaurant();
        restaurant.objectUUID = "1CE562A4-A978-4B75-9B7B-2F3CF9F42A04";
        return restaurant;
    }

    public static Event getEventForEventDetail() {
        Event event = new Event();
        event.objectUUID = "C9970CF0-6925-42C5-8C52-4E37A8A250A1";
        return event;
    }

    public static Team getOrderedPeople() {
        Team team = new Team();
        team.objectUUID = "5C52C84D-4E0B-4727-B3FD-274C9F004580";
        return team;
    }

}
