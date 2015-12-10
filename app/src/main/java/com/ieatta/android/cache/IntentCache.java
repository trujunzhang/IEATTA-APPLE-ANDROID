package com.ieatta.android.cache;

import com.ieatta.com.parse.ParseModelAbstract;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by djzhang on 12/10/15.
 */
public class IntentCache {
    private IntentCache self = this;
    public static final IEACache sharedInstance = new IEACache();

    private HashMap<String,ParseModelAbstract> intentModelCache = new LinkedHashMap<>();

    public void cacheIntentModel(ParseModelAbstract model){
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
