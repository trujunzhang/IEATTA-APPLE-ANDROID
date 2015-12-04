package com.ieatta.android.debug;

import android.os.Bundle;

import com.ieatta.android.R;
import com.ieatta.android.modules.IEABaseTableViewController;
import com.ieatta.android.modules.cells.IEANearRestaurantMoreCell;
import com.ieatta.android.modules.cells.model.IEANearRestaurantMore;
import com.ieatta.android.modules.common.MainSegueIdentifier;
import com.ieatta.android.modules.common.edit.IEAEditKey;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends IEABaseTableViewController {
private MainActivity self = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.More), 0);


        self.setRegisterCellClassWhenSelected(IEANearRestaurantMoreCell.getType(), 0);

        IEANearRestaurantMore managerRestaurantItem = new IEANearRestaurantMore(R.drawable.restaurants_icon, R.string.Add_a_Restaurant, MainSegueIdentifier.editRestaurantSegueIdentifier);
        IEANearRestaurantMore[] mores = {managerRestaurantItem};
        List<IEANearRestaurantMore> list = Arrays.asList(mores);

        self.setSectionItems(new LinkedList<Object>(list), 0);

//        self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Recipe), 1);
    }

}
