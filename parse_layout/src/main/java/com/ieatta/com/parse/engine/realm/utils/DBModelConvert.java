package com.ieatta.com.parse.engine.realm.utils;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.engine.realm.models.DBTeam;
import com.ieatta.com.parse.models.Event;
import com.ieatta.com.parse.models.NewRecord;
import com.ieatta.com.parse.models.PeopleInEvent;
import com.ieatta.com.parse.models.Photo;
import com.ieatta.com.parse.models.Recipe;
import com.ieatta.com.parse.models.Restaurant;
import com.ieatta.com.parse.models.Review;
import com.ieatta.com.parse.models.Team;

import io.realm.Realm;

/**
 * Created by djzhang on 12/20/15.
 */
public class DBModelConvert {

    public static void write(Event model, Realm realm) {

    }

    public static void write(NewRecord model, Realm realm) {

    }

    public static void write(PeopleInEvent model, Realm realm) {

    }

    public static void write(Photo model, Realm realm) {

    }

    public static void write(Recipe model, Realm realm) {

    }

    public static void write(Restaurant model, Realm realm) {

    }
    public static void write(Review model, Realm realm) {

    }

    public static void write(Team model, Realm realm) {
        DBTeam team = realm.createObject(DBTeam.class); // Create a new object
        team.setUUID(ParseModelAbstract.getPoint(model));
        team.setObjectCreatedDate(model.objectCreatedDate);
        team.setDisplayName(model.displayName);

        team.setEmail(model.email);
        team.setAddress(model.address);
    }
}
