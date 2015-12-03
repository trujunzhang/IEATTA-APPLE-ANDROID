package com.ieatta.android.modules.cells.model;

import com.ieatta.android.modules.view.IEARestaurantDetailViewController;
import com.ieatta.com.parse.models.Restaurant;

/**
 * Created by djzhang on 12/2/15.
 */
public class IEARestaurantDetailHeader {
    public IEARestaurantDetailViewController viewController;
    public Restaurant model;

    public IEARestaurantDetailHeader(IEARestaurantDetailViewController viewController, Restaurant model) {
        this.viewController = viewController;
        this.model = model;
    }
}
