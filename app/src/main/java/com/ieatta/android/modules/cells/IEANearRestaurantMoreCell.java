package com.ieatta.android.modules.cells;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import com.ieatta.android.extensions.viewkit.ModelTransfer;
import com.ieatta.android.modules.common.MainSegueIdentifier;

/**
 * Created by djzhang on 12/1/15.
 */

public class IEANearRestaurantMoreCell implements ModelTransfer {
    public class IENearRestaurantMore {
        int resId;
        String title = "";
        MainSegueIdentifier identifier = MainSegueIdentifier.Unspecified;

        public IENearRestaurantMore(int resId, String title, MainSegueIdentifier identifier) {
            this.resId = resId;
            this.title = title;
            this.identifier = identifier;
        }
    }

    private IEANearRestaurantMoreCell self = this;
    private ImageView iconImageView;
    private TextView titleLabel;

    @Override
    public void updateWithModel(Object model) {
        IENearRestaurantMore more  = (IENearRestaurantMore) model;

        self.iconImageView.setImageResource(more.resId);
        self.titleLabel.setText(more.title);
    }
}
