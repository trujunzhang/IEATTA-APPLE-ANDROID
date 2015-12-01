package com.ieatta.com.parse.async;

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

    public AsyncCacheInfo() {
//        assert(false, "This constructor is not allowed!");
    }

    public AsyncCacheInfo(String storeKey) {
        this.storeKey = storeKey;

//        if let theNewRecordDate = Defaults[self.key!] {
//            self.lastRecordCreateAt = theNewRecordDate
//
////            self.printDescription("Constructor")
//        }
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
