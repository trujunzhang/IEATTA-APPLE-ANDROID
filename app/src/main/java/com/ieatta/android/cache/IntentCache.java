package com.ieatta.android.cache;

import com.ieatta.com.parse.ParseModelAbstract;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Created by djzhang on 12/10/15.
 */
public class IntentCache {

    public static String intentUUID = "intentUUID";
    public static String newModel = "newModel";
    public static String selectedReview = "selectedReview";
    public static String photoAtIndex = "photoAtIndex";

    private IntentCache self = this;
    public static final IntentCache sharedInstance = new IntentCache();

    private HashMap<String,ParseModelAbstract> intentModelCache = new LinkedHashMap<>();
    public LinkedList<ParseModelAbstract> orderedPeople = new LinkedList<>();

    public void setIntentModel(ParseModelAbstract model){
        self.intentModelCache.put(model.intentUUID, model);
    }

    public ParseModelAbstract getIntentModel(String intentUUID) {
        if(self.intentModelCache.containsKey(intentUUID)) {
            return  self.intentModelCache.get(intentUUID);
        }
        return null;
    }

    public void clearIntentModelCache(){
        self.intentModelCache = new LinkedHashMap<>();
    }

}
