package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.cells.model.IEANearRestaurantMore;
import com.ieatta.com.parse.models.Restaurant;

public class IEAGoogleMapAddressCell extends IEAViewHolder {
    public static int layoutResId = R.layout.near_restaurant_cell;

    private IEAGoogleMapAddressCell self = this;

    private TextView formattedAddressLabel;

    public IEAGoogleMapAddressCell(View itemView) {
        super(itemView);
    }

    @Override
    public void updateWithModel(Object model) {
        Restaurant more  = (Restaurant) model;
        self.formattedAddressLabel.setText(more.getGoogleMapAddress());
    }
}
