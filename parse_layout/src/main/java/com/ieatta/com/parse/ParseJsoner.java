package com.ieatta.com.parse;

import android.yelp.com.commonlib.EnvironmentUtils;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.ieatta.com.parse.models.enums.PQueryModelType;

import java.io.StringReader;

/**
 * Created by djzhang on 11/27/15.
 */
public abstract class ParseJsoner extends ParseModelAbstract {
    private ParseJsoner self = this;
    public ParseJsoner(String objectUUID) {
        super(objectUUID);
    }

    public ParseJsoner() {
        super();
    }


    public static void readJsonFile(PQueryModelType type){
        ParseModelAbstract model = ParseModelAbstract.getInstanceFromType(type);
        String name = model.getParseTableName();
//        EnvironmentUtils.sharedInstance.getGlobalContext().getResources().getf
//        JsonReader reader = new JsonReader(new StringReader(json));
    }

}
