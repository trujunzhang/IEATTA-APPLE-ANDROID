package com.ieatta.android.modules.cells;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.viewkit.ModelTransfer;
import com.ieatta.android.modules.adopter.IEAViewHolder;
import com.ieatta.android.modules.common.MainSegueIdentifier;

public class IEANearRestaurantMoreCell extends IEAViewHolder {
    public static int layoutResId = R.layout.near_restaurant_more_cell;

    private IEANearRestaurantMoreCell self = this;
    private ImageView iconImageView;
    private TextView titleLabel;

    public IEANearRestaurantMoreCell(View itemView) {
        super(itemView);
    }

    @Override
    public void updateWithModel(Object model) {
        IENearRestaurantMore more  = (IENearRestaurantMore) model;

        self.iconImageView.setImageResource(more.iconResId);
        self.titleLabel.setText(more.titleResId);
    }
}
