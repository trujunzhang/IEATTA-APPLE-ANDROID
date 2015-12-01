package com.ieatta.android.modules.cells;

import com.ieatta.android.modules.common.MainSegueIdentifier;

/**
 * Created by djzhang on 12/1/15.
 */

public class IENearRestaurantMore {
    int iconResId;
    int titleResId;
    MainSegueIdentifier identifier = MainSegueIdentifier.Unspecified;

    public IENearRestaurantMore(int iconResId, int titleResId, MainSegueIdentifier identifier) {
        this.iconResId = iconResId;
        this.titleResId = titleResId;
        this.identifier = identifier;
    }
}
