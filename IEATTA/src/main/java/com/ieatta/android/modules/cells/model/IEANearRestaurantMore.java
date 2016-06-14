package com.ieatta.android.modules.cells.model;

import com.ieatta.android.modules.common.MainSegueIdentifier;

public class IEANearRestaurantMore {
    public int iconResId;
    public int titleResId;
    public MainSegueIdentifier identifier = MainSegueIdentifier.Unspecified;

    public IEANearRestaurantMore(int iconResId, int titleResId, MainSegueIdentifier identifier) {
        this.iconResId = iconResId;
        this.titleResId = titleResId;
        this.identifier = identifier;
    }
}
