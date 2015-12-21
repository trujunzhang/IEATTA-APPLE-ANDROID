package com.ieatta.com.parse.engine.realm;

/**
 * Created by djzhang on 12/21/15.
 */
public class DBBuild {
    private DBBuild self = this;
    private boolean isFromLocalDatastore = false;
    public void fromLocalDatastore() {
        self.isFromLocalDatastore = true;
    }
}
