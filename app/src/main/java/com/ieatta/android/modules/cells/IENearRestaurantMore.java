package com.ieatta.android.modules.cells;

import com.ieatta.android.modules.common.MainSegueIdentifier;

/**
 * Created by djzhang on 12/1/15.
 */

public class IENearRestaurantMore {
    int resId;
    String title = "";
    MainSegueIdentifier identifier = MainSegueIdentifier.Unspecified;

    public IENearRestaurantMore(int resId, String title, MainSegueIdentifier identifier) {
        this.resId = resId;
        this.title = title;
        this.identifier = identifier;
    }
}
