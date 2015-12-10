package com.ieatta.android.modules.cells.headerview;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.cells.model.IEARestaurantDetailHeader;

/**
 * Created by djzhang on 12/2/15.
 */
public class IEARestaurantDetailHeaderCell  extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEARestaurantDetailHeaderCell.class, R.layout.restaurant_detail_header_cell);
    }

    @Override
    protected boolean shouldClickItem() {
        return false;
    }

    private IEARestaurantDetailHeaderCell self = this;

    private TextView formattedAddressLabel;

    public IEARestaurantDetailHeaderCell(View itemView) {
        super(itemView);

//        self.formattedAddressLabel = (TextView) itemView.findViewById(R.id.formattedAddressLabel);
    }

    @Override
    public void render(Object value) {
        IEARestaurantDetailHeader more  = (IEARestaurantDetailHeader) value;
//        self.formattedAddressLabel.setText(more.model.getGoogleMapAddress());

    }
}
