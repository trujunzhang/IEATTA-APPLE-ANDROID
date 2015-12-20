package com.ieatta.com.parse.engine.realm.utils;

import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.ParseModelQuery;
import com.ieatta.com.parse.models.Event;
import com.ieatta.com.parse.models.NewRecord;
import com.ieatta.com.parse.models.PeopleInEvent;
import com.ieatta.com.parse.models.Photo;
import com.ieatta.com.parse.models.Recipe;
import com.ieatta.com.parse.models.Restaurant;
import com.ieatta.com.parse.models.Review;
import com.ieatta.com.parse.models.Team;
import com.ieatta.com.parse.models.enums.PQueryModelType;

import io.realm.Realm;

/**
 * Created by djzhang on 12/20/15.
 */
public class DBAccessUtils {


    public static void pinInBackground(ParseModelQuery model) {
        DBAccessUtils.save(model);
    }

    private static void save(ParseModelAbstract model) {
        // Obtain a Realm instance
        Realm realm = Realm.getInstance(EnvironmentUtils.sharedInstance.getGlobalContext());

        realm.beginTransaction();

        //... add or update objects here ...
        DBAccessUtils.write(realm, model);

        realm.commitTransaction();
    }

    private static void write(Realm realm, ParseModelAbstract model) {
        PQueryModelType modelType = model.getModelType();
        switch (modelType) {
            case Recipe:
                DBModelConvert.write((Recipe) model, realm);
                break;
            case Photo:
                DBModelConvert.write((Photo) model, realm);
                break;
            case Team:
                DBModelConvert.write((Team) model, realm);
                break;
            case Review:
                DBModelConvert.write((Review) model, realm);
                break;
            case Event:
                DBModelConvert.write((Event) model, realm);
                break;
            case Restaurant:
                DBModelConvert.write((Restaurant) model, realm);
                break;
            case NewRecord:
                DBModelConvert.write((NewRecord) model, realm);
                break;
            case PeopleInEvent:
                DBModelConvert.write((PeopleInEvent) model, realm);
                break;
            default:
                break;
        }

    }


}
