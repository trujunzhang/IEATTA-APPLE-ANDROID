package com.ieatta.com.parse.engine.realm;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by djzhang on 12/21/15.
 */
public class DBBuild {
    private DBBuild self = this;

    private boolean isFromLocalDatastore = false;
    private HashMap<String,Object> equalMap = new LinkedHashMap<>();

    public void fromLocalDatastore() {
        self.isFromLocalDatastore = true;
    }

    public void whereEqualTo(String key, Object value) {
        equalMap.put(key,value);
    }


}
