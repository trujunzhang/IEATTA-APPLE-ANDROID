package com.ieatta.com.parse;

import android.content.Context;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by djzhang on 11/29/15.
 */
public class ParseAPI {

    public static void setup(Context context) {

        // Enable Local Datastore.
        Parse.enableLocalDatastore(context);

        // Add your initialization code here
//        Parse.initialize(context,"VCaFXiKwdCYUkY9fOe1gK4lcmvexwXPjEt6r0txq","DcqY8cnikXrRaYwteQyCX4lkN3ksrhROBXuarXpB");

        Parse.initialize(context, "HMmSx44DYn6xJDdNgxq1ess5oU0Nmu5POd561nSr", "x5ttH0jvHG6ITGi47LFRGo0l7b4Mz1o51HHCKV8T");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        // defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

    }
}
