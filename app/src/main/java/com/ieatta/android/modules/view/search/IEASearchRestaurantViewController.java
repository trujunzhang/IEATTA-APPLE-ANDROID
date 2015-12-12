package com.ieatta.android.modules.view.search;

import android.os.Bundle;

import com.ieatta.android.modules.IEASplitDetailViewController;
import com.ieatta.android.modules.adapter.NSIndexPath;
import com.ieatta.android.modules.cells.IEANearRestaurantsCell;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Restaurant;

import java.util.List;

enum SearchRestaurantSection  {
         sectionRestaurants //;= 0
        }
/**
 * Created by djzhang on 12/1/15.
 */
public class IEASearchRestaurantViewController extends IEASplitDetailViewController {
private IEASearchRestaurantViewController self = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        self.setRegisterCellClassWhenSelected(IEANearRestaurantsCell.getType(),SearchRestaurantSection.sectionRestaurants.ordinal());
    }

    @Override
    public void whenSelectedEvent(Object model, NSIndexPath indexPath) {
        self.showSelectedModel((Restaurant) model);
    }

    private void showSelectedModel(Restaurant model){
//        self.getManagerNavigationViewController().pushViewController(UIStoryboard.Storyboard.Controllers.restaurantDetailViewController().transfer(model), animated: true)
    }

    private void updateTableView(List<ParseModelAbstract> items ){
        self.setSectionItems(items, SearchRestaurantSection.sectionRestaurants.ordinal());
    }
}
