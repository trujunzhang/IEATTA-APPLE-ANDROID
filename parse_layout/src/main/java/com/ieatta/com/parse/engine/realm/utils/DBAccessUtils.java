package com.ieatta.com.parse.engine.realm.utils;

import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.com.parse.ParseModelAbstract;

import com.ieatta.com.parse.models.Event;
import com.ieatta.com.parse.models.NewRecord;
import com.ieatta.com.parse.models.PeopleInEvent;
import com.ieatta.com.parse.models.Photo;
import com.ieatta.com.parse.models.Recipe;
import com.ieatta.com.parse.models.Restaurant;
import com.ieatta.com.parse.models.Review;
import com.ieatta.com.parse.models.Team;
import com.ieatta.com.parse.models.enums.PQueryModelType;

import java.util.List;

import bolts.Task;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by djzhang on 12/20/15.
 */
public class DBAccessUtils {
    public Task<Void> pinInBackground(ParseModelAbstract model) {
        this.save(model);
        return Task.forResult(null);
    }

    private Task<Void> save(ParseModelAbstract model) {
        // Obtain a Realm instance
        Realm realm = Realm.getInstance(EnvironmentUtils.sharedInstance.getGlobalContext());

        try {
            realm.beginTransaction();

            // add or update objects here ...
            this.write(realm, model);

            realm.commitTransaction();
        } catch (Exception e) {
            return Task.forError(e);
        } finally {
            realm.close();
        }

        return Task.forResult(null);
    }

    private void write(Realm realm, ParseModelAbstract model) {
        PQueryModelType modelType = model.getModelType();
        switch (modelType) {
            case Recipe:
                DBModelWriter.write((Recipe) model, realm);
                break;
            case Photo:
                DBModelWriter.write((Photo) model, realm);
                break;
            case Team:
                DBModelWriter.write((Team) model, realm);
                break;
            case Review:
                DBModelWriter.write((Review) model, realm);
                break;
            case Event:
                DBModelWriter.write((Event) model, realm);
                break;
            case Restaurant:
                DBModelWriter.write((Restaurant) model, realm);
                break;
            case NewRecord:
                DBModelWriter.write((NewRecord) model, realm);
                break;
            case PeopleInEvent:
                DBModelWriter.write((PeopleInEvent) model, realm);
                break;
            default:
                break;
        }

    }
}
