package com.ieatta.android.modules.cells.model;

import com.ieatta.android.R;
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
