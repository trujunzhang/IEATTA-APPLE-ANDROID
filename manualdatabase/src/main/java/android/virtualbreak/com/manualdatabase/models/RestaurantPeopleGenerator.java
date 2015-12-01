package android.virtualbreak.com.manualdatabase.models;

import android.virtualbreak.com.manualdatabase.R;


import com.ieatta.com.parse.models.Team;

import java.util.LinkedList;

/**
 * Created by djzhang on 7/20/15.
 */
public class RestaurantPeopleGenerator {
    private static LinkedList<Team> parseUsers = new LinkedList<Team>();

    static {
        parseUsers.add(new Team("Clarence Morgan", "Clarence@gmail.com", "Fresno, Mississippi", R.drawable.clarencemorgan));
        parseUsers.add(new Team("Dolores Chavez", "Dolores@gmail.com", "Killeen, Texas", R.drawable.doloreschavez));
        // The following used for selecting people
        parseUsers.add(new Team("Frank Gregory", "Frank@gmail.com", "Dallas, Nevada", R.drawable.frankgregory));
        parseUsers.add(new Team("Luke Garcia", "Luke@gmail.com", "Lakeland, Massachusetts", R.drawable.lukegarcia));
        parseUsers.add(new Team("Terri Torres", "Terri@gmail.com", "Farmers Branch, Connecticut", R.drawable.territorres));
        parseUsers.add(new Team("Tom Terry", "Tom@gmail.com", "Baltimore, Arizona", R.drawable.tomterry));
    }

    public static LinkedList<Team> getCurrentParseUserList() {
        return parseUsers;
    }

}
