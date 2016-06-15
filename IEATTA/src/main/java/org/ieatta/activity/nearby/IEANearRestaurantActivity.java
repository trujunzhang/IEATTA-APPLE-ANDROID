package org.ieatta.activity.nearby;

import android.location.Location;
import android.os.Bundle;

public class IEANearRestaurantActivity extends LocationObserveActivity {
    private NearRestaurantsTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        task = new NearRestaurantsTask(this, this.recyclerView);
        task.makeActivePage();
    }

    @Override
    protected void notifyLocationChanged(Location location) {
        if (task != null)
            task.executeUpdateTask();
    }

}
