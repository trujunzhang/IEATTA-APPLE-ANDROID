package org.ieatta.database.realm;


import com.ieatta.BaseApp;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmInstance {
    public static Realm getInstance() {
        RealmConfiguration config = new RealmConfiguration.Builder(BaseApp.getInstance()).build();
        return Realm.getInstance(config);
    }
}
