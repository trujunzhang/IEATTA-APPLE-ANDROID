package com.ieatta.android.modules.cells.headerview;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.cells.model.IEARestaurantDetailHeader;
import com.ieatta.com.parse.models.Restaurant;

/**
 * Created by djzhang on 12/2/15.
 */
public class IEARestaurantDetailHeaderCell  extends IEAViewHolder {
    public static int layoutResId = R.layout.restaurant_detail_header_cell;

    private IEARestaurantDetailHeaderCell self = this;

    private TextView formattedAddressLabel;

    public IEARestaurantDetailHeaderCell(View itemView) {
        super(itemView);

//        self.formattedAddressLabel = (TextView) itemView.findViewById(R.id.formattedAddressLabel);
    }

    @Override
    public void updateWithModel(Object model) {
        IEARestaurantDetailHeader more  = (IEARestaurantDetailHeader) model;
//        self.formattedAddressLabel.setText(more.model.getGoogleMapAddress());

    }
}
