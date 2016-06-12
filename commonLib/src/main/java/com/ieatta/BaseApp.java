package com.ieatta;

import android.app.Application;

import org.wikipedia.database.Database;
import org.wikipedia.util.ReleaseUtil;

public class BaseApp extends Application{

    private Database database;

    public boolean isProdRelease() {
        return ReleaseUtil.isProdRelease();
    }

    public boolean isPreProdRelease() {
        return ReleaseUtil.isPreProdRelease();
    }

    public boolean isAlphaRelease() {
        return ReleaseUtil.isAlphaRelease();
    }

    public boolean isPreBetaRelease() {
        return ReleaseUtil.isPreBetaRelease();
    }

    public boolean isDevRelease() {
        return ReleaseUtil.isDevRelease();
    }


    /**
     * Singleton instance of IEAApp
     */
    private static BaseApp INSTANCE;


    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;
    }

    /**
     * Returns the singleton instance of the IEAApp
     *
     * This is ok, since android treats it as a singleton anyway.
     */
    public static BaseApp getInstance() {
        return INSTANCE;
    }

    /**
     * Gets whether EventLogging is currently enabled or disabled.
     *
     * @return A boolean that is true if EventLogging is enabled, and false if it is not.
     */
    public boolean isEventLoggingEnabled() {
//        return Prefs.isEventLoggingEnabled();
        return true;
    }

    /**
     * Get this app's unique install ID, which is a UUID that should be unique for each install
     * of the app. Useful for anonymous analytics.
     * @return Unique install ID for this app.
     */
    public String getAppInstallID() {
//        String id = Prefs.getAppInstallId();
//        if (id == null) {
//            id = UUID.randomUUID().toString();
//            Prefs.setAppInstallId(id);
//        }
//        return id;

        return "";
    }

    public Database getDatabase() {
        return database;
    }
}
