package com.ieatta.com.parse.engine.realm.utils;

import com.ieatta.com.parse.ParseModelAbstract;

import java.util.LinkedList;
import java.util.List;

import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.ParseModelQuery;
import com.ieatta.com.parse.engine.realm.models.DBTeam;
import com.ieatta.com.parse.models.Event;
import com.ieatta.com.parse.models.NewRecord;
import com.ieatta.com.parse.models.PeopleInEvent;
import com.ieatta.com.parse.models.Photo;
import com.ieatta.com.parse.models.Recipe;
import com.ieatta.com.parse.models.Restaurant;
import com.ieatta.com.parse.models.Review;
import com.ieatta.com.parse.models.Team;
import com.ieatta.com.parse.models.enums.PQueryModelType;
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
import com.ieatta.com.parse.models.enums.PhotoUsedType;
import com.ieatta.com.parse.models.enums.ReviewType;
import com.parse.ParseGeoPoint;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by djzhang on 12/21/15.
 */
public class DBModelReader<T extends ParseModelAbstract> {

    public List<T> readRealmResults(RealmResults results, PQueryModelType modelType, int limit) {
        List<T> list = new LinkedList<>();

        int step = 0;
        for (Object realmObject : results) {
            if(limit != -1 && step >= limit){
                break;
            }
            step ++;

            ParseModelAbstract model = this.reader(realmObject,modelType);

            list.add((T) model);
        }

        return list;
    }

    public ParseModelAbstract reader(Object realmObject, PQueryModelType modelType){
        ParseModelAbstract model = null;

        switch (modelType) {
            case Recipe:
                model = this.reader((DBRecipe) realmObject);
                break;
            case Photo:
                model = this.reader((DBPhoto) realmObject);
                break;
            case Team:
                model = this.reader((DBTeam) realmObject);
                break;
            case Review:
                model = this.reader((DBReview) realmObject);
                break;
            case Event:
                model = this.reader((DBEvent) realmObject);
                break;
            case Restaurant:
                model = this.reader((DBRestaurant) realmObject);
                break;
            case NewRecord:
                model = this.reader((DBNewRecord) realmObject);
                break;
            case PeopleInEvent:
                model = this.reader((DBPeopleInEvent) realmObject);
                break;
            default:
                break;
        }
        return model;
    }


    private ParseModelAbstract reader(DBEvent model) {
        Event object = new Event();

        object.objectUUID = model.getUUID();
        object.objectCreatedDate = model.getObjectCreatedDate();
        object.displayName = model.getDisplayName();

        object.startDate = model.getStartDate();
        object.endDate = model.getEndDate();
        object.whatToEat = model.getWhatToEat();
        object.remarks = model.getRemarks();
        object.waiter = model.getWaiter();
        object.restaurantRef = model.getRestaurantRef();

        return object;
    }

    private ParseModelAbstract reader(DBNewRecord model) {
        NewRecord object = new NewRecord();

        object.objectUUID = model.getUUID();
        object.objectCreatedDate = model.getObjectCreatedDate();

        object.modelType = PQueryModelType.fromInteger(model.getModelType());
        object.modelPoint = model.getModelPoint();
        object.modelCreatedDate = model.getModelCreatedDate();

        return object;
    }

    private ParseModelAbstract reader(DBPeopleInEvent model) {
        PeopleInEvent object = new PeopleInEvent();

        object.objectUUID = model.getUUID();
        object.objectCreatedDate = model.getObjectCreatedDate();

        object.userRef = model.getUserRef();
        object.eventRef = model.getEventRef();

        return object;
    }

    private ParseModelAbstract reader(DBPhoto model) {
        Photo object = new Photo();

        object.objectUUID = model.getUUID();
        object.objectCreatedDate = model.getObjectCreatedDate();


        object.restaurantRef = model.getRestaurantRef();
        object.usedRef = model.getUsedRef();
        object.usedType = PhotoUsedType.fromInteger(model.getUsedType());

        object.originalUrl = model.getOriginalUrl();
        object.thumbnailUrl = model.getThumbnailUrl();

        return object;
    }

    private ParseModelAbstract reader(DBRecipe model) {
        Recipe object = new Recipe();

        object.objectUUID = model.getUUID();
        object.objectCreatedDate = model.getObjectCreatedDate();
        object.displayName = model.getDisplayName();

        object.cost = model.getCost();
        object.likeCount = model.getLikeCount();
        object.eventRef = model.getEventRef();
        object.orderedPeopleRef = model.getOrderedPeopleRef();

        return object;
    }

    private ParseModelAbstract reader(DBRestaurant model) {
        Restaurant object = new Restaurant();

        object.objectUUID = model.getUUID();
        object.objectCreatedDate = model.getObjectCreatedDate();
        object.displayName = model.getDisplayName();

        object.location = new ParseGeoPoint(model.getLatitude(), model.getLongitude());
        object.googleMapAddress = model.getGoogleMapAddress();

        return object;
    }

    private ParseModelAbstract reader(DBReview model) {
        Review object = new Review();

        object.objectUUID = model.getUUID();
        object.objectCreatedDate = model.getObjectCreatedDate();

        object.content = model.getContent();
        object.rate = model.getRate();
        object.userRef = model.getUserRef();
        object.reviewRef = model.getReviewRef();
        object.reviewType = ReviewType.fromInteger(model.getReviewType());

        return object;
    }

    private ParseModelAbstract reader(DBTeam model) {
        Team object = new Team();

        object.objectUUID = model.getUUID();
        object.objectCreatedDate = model.getObjectCreatedDate();
        object.displayName = model.getDisplayName();

        object.email = model.getEmail();
        object.address = model.getAddress();

        return object;
    }

}
