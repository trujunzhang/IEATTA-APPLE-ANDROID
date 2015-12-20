package com.ieatta.com.parse.engine.realm.utils;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.engine.realm.models.DBTeam;
import com.ieatta.com.parse.models.Team;

import io.realm.Realm;

/**
 * Created by djzhang on 12/20/15.
 */
public class DBModelConvert {

    public static void write( Realm realm, Team model) {
        DBTeam team = realm.createObject(DBTeam.class); // Create a new object
        team.setUUID(ParseModelAbstract.getPoint(model));
        team.setObjectCreatedDate(model.objectCreatedDate);
        team.setDisplayName(model.displayName);

        team.setEmail(model.email);
        team.setAddress(model.address);
    }
}
