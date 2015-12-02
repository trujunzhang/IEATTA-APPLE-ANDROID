package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.cells.model.IENearRestaurantMore;

public class IEANearRestaurantMoreCell extends IEAViewHolder {
    public static int layoutResId = R.layout.near_restaurant_more_cell;

    private IEANearRestaurantMoreCell self = this;
    private ImageView iconImageView;
    private TextView titleLabel;

    public IEANearRestaurantMoreCell(View itemView) {
        super(itemView);

        self.iconImageView = (ImageView) itemView.findViewById(R.id.moreCellIconImageView);
        self.titleLabel = (TextView) itemView.findViewById(R.id.moreCellTextView);
    }

    @Override
    public void updateWithModel(Object model) {
        IENearRestaurantMore more  = (IENearRestaurantMore) model;

        self.iconImageView.setImageResource(more.iconResId);
        self.titleLabel.setText(more.titleResId);
    }
}
