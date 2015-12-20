package com.ieatta.com.parse.async;

import android.content.Context;
import android.content.SharedPreferences;
import android.yelp.com.commonlib.EnvironmentUtils;
import android.yelp.com.commonlib.LogUtils;

import com.ieatta.com.parse.models.NewRecord;
import com.ieatta.com.parse.engine.realm.DBQuery;

import java.util.Date;

/**
 * Created by djzhang on 11/27/15.
 */
public class AsyncCacheInfo {

    public static final String TAG_NEW_RECORD_DATE = "LastPulledNewRecordCreatedAt";
    private static final String PREFS = "prefs";

    /// Cache the last update date from server for new record table.
    public Date lastRecordCreateAt = null;
    private String storeKey;

    SharedPreferences sharedPreferences;

    private AsyncCacheInfo() {
//        assert(false, "This constructor is not allowed!");
    }

    public AsyncCacheInfo(String storeKey) {
        this.storeKey = storeKey;

        // Access the device's key-value storage
        sharedPreferences = EnvironmentUtils.sharedInstance.getGlobalContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        // Read the user's name,
        // or an empty string if nothing found
        long dateValue = sharedPreferences.getLong(this.storeKey, Long.MIN_VALUE);

        this.lastRecordCreateAt = null;
        if (dateValue != Long.MIN_VALUE) {
            this.lastRecordCreateAt = new Date(dateValue);
            // 3. Print date info.
//            this.printDescription("Constructor");
        }
    }

    public void storeNewRecordDate(Date newDate) {
        // Put it into memory (don't forget to commit!)
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putLong(this.storeKey, newDate.getTime());
        e.commit();

        // 2. Set last date.
        this.lastRecordCreateAt = newDate;

        // 3. Print date info.
        this.printDescription("Store");
    }

    public DBQuery createQuery(int limit) {
        return new NewRecord().createQueryForPullObjectsFromServer(this.lastRecordCreateAt, limit);
    }

    public void printDescription(String info) {
        if (this.lastRecordCreateAt != null) {
            LogUtils.debug("last newRecordDate from " + info + ": " + this.lastRecordCreateAt.toString());
        }
    }
}
