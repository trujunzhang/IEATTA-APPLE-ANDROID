package com.ieatta.android.debug;

import android.os.Bundle;

import com.ieatta.android.R;
import com.ieatta.android.modules.IEABaseTableViewController;
import com.ieatta.android.modules.cells.IEANearRestaurantMoreCell;
import com.ieatta.android.modules.common.edit.IEAEditKey;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;

public class MainActivity extends IEABaseTableViewController {
private MainActivity self = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.More), 0);
//        self.setRegisterCellClassWhenSelected(IEANearRestaurantMoreCell.class, IEANearRestaurantMoreCell.layoutResId, 0);
        

    }

}
