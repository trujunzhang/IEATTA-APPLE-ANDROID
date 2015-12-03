package com.ieatta.android.apps;

import android.content.Context;
import android.content.res.Resources;

import com.ieatta.android.R;
import com.ieatta.com.parse.models.Restaurant;

/**
 * Created by djzhang on 12/1/15.
 */
public class AppConfigure {

    public static void setup(Context context) {
        Restaurant.Default_FormattedAddress = context.getString(R.string.Still_converting_GPS_to_Address);
    }

}
