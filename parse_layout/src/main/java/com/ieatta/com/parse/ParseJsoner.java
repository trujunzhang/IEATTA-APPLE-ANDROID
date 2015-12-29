package com.ieatta.com.parse;

import android.content.res.AssetManager;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.ieatta.com.parse.models.enums.PQueryModelType;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import bolts.Task;

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

    public static Task<List<ParseModelAbstract>> parseJsonFileToArray(PQueryModelType type){
        List<ParseModelAbstract> list = new LinkedList<>();

        JsonElement jsonElement = ParseJsoner.readJsonFile(type);
        if(jsonElement == null){
            String name = ParseModelAbstract.getInstanceFromType(type).getParseTableName();
            return Task.forError(new NullPointerException("not found json file for "+name));
        }

        JsonElement rootElement = (((JsonObject) jsonElement).get("results"));
        JsonArray results = rootElement.getAsJsonArray();
        for(JsonElement element :results){
            JsonObject item = element.getAsJsonObject();

            ParseModelAbstract instance = ParseModelAbstract.getInstanceFromType(type);
            instance.parseJson(item);

            list.add(instance);
        }

        return Task.forResult(list);
    }

    public static JsonElement readJsonFile(PQueryModelType type) {
        String name = ParseModelAbstract.getInstanceFromType(type).getParseTableName();
        AssetManager assets = EnvironmentUtils.sharedInstance.getGlobalContext().getAssets();

        byte[] buffer = null;
        InputStream is = null;
        try {
            String jsonName = "export/" + name + ".json";
            is = assets.open(jsonName);
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
        if(buffer == null){
            return null;
        }

        String bufferString = new String(buffer);

        JsonParser parser = new JsonParser();
        JsonElement rootElement = parser.parse(bufferString);

        return rootElement;
    }

}
