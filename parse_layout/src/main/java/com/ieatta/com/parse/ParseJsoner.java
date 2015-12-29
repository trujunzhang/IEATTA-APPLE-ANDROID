package com.ieatta.com.parse;

import android.content.res.AssetManager;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.ieatta.com.parse.models.enums.PQueryModelType;

import java.io.IOException;
import java.io.InputStream;
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
        String name = ParseModelAbstract.getInstanceFromType(type).getParseTableName();
        AssetManager assets = EnvironmentUtils.sharedInstance.getGlobalContext().getAssets();

        byte[] buffer = null;
        InputStream is = null;
        try {
            is = assets.open(name + ".json");
            int size = is.available();

            buffer = new byte[size];
            is.read(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String bufferString = new String(buffer);

//        EnvironmentUtils.sharedInstance.getGlobalContext().getResources().getf
//        JsonReader reader = new JsonReader(new StringReader(json));
    }

}
