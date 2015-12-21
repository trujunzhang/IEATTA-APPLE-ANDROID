package com.ieatta.com.parse.engine.realm.utils;

import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.com.parse.engine.realm.models.DBTeam;
import com.ieatta.com.parse.models.enums.PQueryModelType;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;

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

/**
 * Created by djzhang on 12/21/15.
 */
public class RMQueryUtils {

    public <T extends RealmObject> RealmQuery<T> getRealmQuery(Realm realm, PQueryModelType modelType) {

        switch (modelType) {
            case Recipe:
                return (RealmQuery<T>) realm.where(DBRecipe.class);
            case Photo:
                return (RealmQuery<T>) realm.where(DBPhoto.class);
            case Team:
                return (RealmQuery<T>) realm.where(DBTeam.class);
            case Review:
                return (RealmQuery<T>) realm.where(DBReview.class);
            case Event:
                return (RealmQuery<T>) realm.where(DBEvent.class);
            case Restaurant:
                return (RealmQuery<T>) realm.where(DBRestaurant.class);
            case NewRecord:
                return (RealmQuery<T>) realm.where(DBNewRecord.class);
            case PeopleInEvent:
                return (RealmQuery<T>) realm.where(DBPeopleInEvent.class);
            default:
                break;
        }
        return null;
    }
}
