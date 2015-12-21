package com.ieatta.com.parse.engine.realm.utils;

import com.ieatta.com.parse.ParseModelAbstract;

import java.util.LinkedList;
import java.util.List;

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

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by djzhang on 12/21/15.
 */
public class DBModelReader<T extends ParseModelAbstract> {

    public List<T> readRealmResults(RealmResults results, PQueryModelType modelType) {
        List<T> list = new LinkedList<>();

        for(Object object : results){
            int x = 0;

            ParseModelAbstract modelAbstract = new Photo();
        }


        return list;
    }
}
