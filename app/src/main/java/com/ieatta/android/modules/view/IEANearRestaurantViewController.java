package com.ieatta.android.modules.view;

import android.os.Bundle;

import com.ieatta.android.modules.IEASplitMasterViewController;
import com.ieatta.android.modules.cells.IEANearRestaurantMoreCell;

/**
 * Created by djzhang on 12/1/15.
 */

enum NearRestaurantSection  {
         sectionMoreItems  ,//= 0
         sectionRestaurants, //= 1
        }

public class IEANearRestaurantViewController extends IEASplitMasterViewController {
    private IEANearRestaurantViewController self =this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IEANearRestaurantMoreCell.IENearRestaurantMore more;
    }
}
