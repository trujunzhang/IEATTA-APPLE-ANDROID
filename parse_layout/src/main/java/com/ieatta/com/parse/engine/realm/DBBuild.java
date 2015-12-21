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
    private int limit = -1; // negative limits mean, do not send a limit

    public void fromLocalDatastore() {
        self.isFromLocalDatastore = true;
    }

    public void whereEqualTo(String key, Object value) {
        equalMap.put(key,value);
    }


    public void orderByDescending(String key) {

    }

    public void orderByAscending(String key) {

    }

    public void setLimit(int newLimit) {
        self.limit = newLimit;
    }
}
