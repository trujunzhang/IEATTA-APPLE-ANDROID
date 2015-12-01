package com.ieatta.android.modules.cells;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import com.ieatta.android.extensions.viewkit.ModelTransfer;
import com.ieatta.android.modules.common.MainSegueIdentifier;

public class IEANearRestaurantMoreCell implements ModelTransfer {


    private IEANearRestaurantMoreCell self = this;
    private ImageView iconImageView;
    private TextView titleLabel;

    @Override
    public void updateWithModel(Object model) {
        IENearRestaurantMore more  = (IENearRestaurantMore) model;

        self.iconImageView.setImageResource(more.iconResId);
        self.titleLabel.setText(more.titleResId);
    }
}
