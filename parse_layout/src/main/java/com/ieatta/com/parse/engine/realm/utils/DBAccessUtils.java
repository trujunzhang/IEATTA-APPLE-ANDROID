package com.ieatta.com.parse.engine.realm.utils;

import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.com.parse.ParseModelQuery;
import com.ieatta.com.parse.engine.realm.models.DBTeam;
import com.ieatta.com.parse.models.Team;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.ieatta.com.parse.models.enums.ReviewType;

import io.realm.Realm;

/**
 * Created by djzhang on 12/20/15.
 */
public class DBAccessUtils {


    public static void pinInBackground(ParseModelQuery model) {
        PQueryModelType modelType = model.getModelType();
        if(modelType.equals(PQueryModelType.Team)){
            DBAccessUtils.save((Team)model);
        }
    }

    private static void save(Team model) {
        // Obtain a Realm instance
        Realm realm = Realm.getInstance(EnvironmentUtils.sharedInstance.getGlobalContext());

        realm.beginTransaction();

        //... add or update objects here ...
        DBModelConvert.write(realm,model);

        realm.commitTransaction();
    }
}
