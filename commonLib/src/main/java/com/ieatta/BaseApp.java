package com.ieatta;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.yelp.com.commonlib.R;

import org.wikipedia.database.Database;
import org.wikipedia.settings.Prefs;
import org.wikipedia.util.ReleaseUtil;

import org.wikipedia.crash.CrashReporter;
import org.wikipedia.crash.hockeyapp.HockeyAppCrashReporter;
import org.wikipedia.util.log.L;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

public class BaseApp extends Application {

    private CrashReporter crashReporter;

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
     * <p/>
     * This is ok, since android treats it as a singleton anyway.
     */
    public static BaseApp getInstance() {
        return INSTANCE;
    }

    /**
     * Get this app's unique install ID, which is a UUID that should be unique for each install
     * of the app. Useful for anonymous analytics.
     *
     * @return Unique install ID for this app.
     */
    public String getAppInstallID() {
        String id = Prefs.getAppInstallId();
        if (id == null) {
            id = UUID.randomUUID().toString();
            Prefs.setAppInstallId(id);
        }
        return id;
    }

    public Database getDatabase() {
        return database;
    }

    @ColorInt
    public int getContrastingThemeColor() {
//        return getCurrentTheme().getContrastingColor();
        return -1;
    }


    public void putCrashReportProperty(String key, String value) {
        crashReporter.putReportProperty(key, value);
    }

    public void checkCrashes(@NonNull Activity activity) {
        crashReporter.checkCrashes(activity);
    }


    private void initExceptionHandling() {
        crashReporter = new HockeyAppCrashReporter(getString(R.string.hockeyapp_app_id), consentAccessor());
        crashReporter.registerCrashHandler(this);

        L.setRemoteLogger(crashReporter);
    }

    private CrashReporter.AutoUploadConsentAccessor consentAccessor() {
        return new CrashReporter.AutoUploadConsentAccessor() {
            @Override
            public boolean isAutoUploadPermitted() {
                return Prefs.isCrashReportAutoUploadEnabled();
            }
        };
    }



    /**
     * Gets whether EventLogging is currently enabled or disabled.
     *
     * @return A boolean that is true if EventLogging is enabled, and false if it is not.
     */
    public boolean isEventLoggingEnabled() {
        return Prefs.isEventLoggingEnabled();
    }

    public boolean isImageDownloadEnabled() {
        return Prefs.isImageDownloadEnabled();
    }

    public boolean isLinkPreviewEnabled() {
        return Prefs.isLinkPreviewEnabled();
    }

    public SimpleDateFormat getSimpleDateFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ROOT);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat;
    }


}
