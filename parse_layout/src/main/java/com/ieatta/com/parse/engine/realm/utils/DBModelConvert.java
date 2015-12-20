package com.ieatta.com.parse.engine.realm.utils;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.engine.realm.models.DBEvent;
import com.ieatta.com.parse.engine.realm.models.DBNewRecord;
import com.ieatta.com.parse.engine.realm.models.DBPeopleInEvent;
import com.ieatta.com.parse.engine.realm.models.DBPhoto;
import com.ieatta.com.parse.engine.realm.models.DBRecipe;
import com.ieatta.com.parse.engine.realm.models.DBRestaurant;
import com.ieatta.com.parse.engine.realm.models.DBReview;
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
        DBEvent object = realm.createObject(DBEvent.class); // Create a new object

    }

    public static void write(NewRecord model, Realm realm) {
        DBNewRecord object = realm.createObject(DBNewRecord.class); // Create a new object
    }

    public static void write(PeopleInEvent model, Realm realm) {
        DBPeopleInEvent object = realm.createObject(DBPeopleInEvent.class); // Create a new object
    }

    public static void write(Photo model, Realm realm) {
        DBPhoto object = realm.createObject(DBPhoto.class); // Create a new object
    }

    public static void write(Recipe model, Realm realm) {
        DBRecipe object = realm.createObject(DBRecipe.class); // Create a new object
    }

    public static void write(Restaurant model, Realm realm) {
        DBRestaurant object = realm.createObject(DBRestaurant.class); // Create a new object

    }

    public static void write(Review model, Realm realm) {
        DBReview object = realm.createObject(DBReview.class); // Create a new object

    }

    public static void write(Team model, Realm realm) {
        DBTeam object = realm.createObject(DBTeam.class); // Create a new object

        object.setUUID(ParseModelAbstract.getPoint(model));
        object.setObjectCreatedDate(model.objectCreatedDate);
        object.setDisplayName(model.displayName);

        object.setEmail(model.email);
        object.setAddress(model.address);
    }
}
