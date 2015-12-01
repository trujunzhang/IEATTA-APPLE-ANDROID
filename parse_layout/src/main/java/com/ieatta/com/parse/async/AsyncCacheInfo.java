package com.ieatta.com.parse.async;

import android.content.Context;
import android.content.SharedPreferences;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.com.parse.models.NewRecord;
import com.parse.ParseQuery;

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

    SharedPreferences mSharedPreferences;

    public AsyncCacheInfo() {
//        assert(false, "This constructor is not allowed!");
    }

    public AsyncCacheInfo(String storeKey) {
        this.storeKey = storeKey;

        // Access the device's key-value storage
        mSharedPreferences = EnvironmentUtils.sharedInstance.getGlobalContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        // Read the user's name,
        // or an empty string if nothing found
        long dateValue = mSharedPreferences.getLong(storeKey, Long.MIN_VALUE);

        this.lastRecordCreateAt = null;
        if (dateValue != Long.MIN_VALUE) {
            this.lastRecordCreateAt = new Date(dateValue);
            // 3. Print date info.
            this.printDescription("Constructor");
        }
    }

    public void storeNewRecordDate(Date newDate) {
        this.lastRecordCreateAt = newDate;

//        Defaults[self.key!] = newDate

//        this.printDescription("Store");
    }

    public ParseQuery createQuery(int limit) {
        return new NewRecord().createQueryForPullObjectsFromServer(this.lastRecordCreateAt, limit);
    }

    public void printDescription(String info) {
        if (this.lastRecordCreateAt != null) {
//            print("last newRecordDate from \(info): \(theDate.debugFormatted)")
        }
    }
}
