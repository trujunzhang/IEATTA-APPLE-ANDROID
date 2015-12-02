package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.cache.RatedModelReviewCount;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.cells.model.IEANearRestaurantMore;

public class IEAReadReviewsCell extends IEAViewHolder {
    public static int layoutResId = R.layout.near_restaurant_cell;

    private IEAReadReviewsCell self = this;

//    @IBOutlet weak var avatarView: AvatarView!

    private TextView titleLabel;
    private TextView subtitleLabel;

    public IEAReadReviewsCell(View itemView) {
        super(itemView);
    }

    @Override
    public void updateWithModel(Object model) {
        RatedModelReviewCount more  = (RatedModelReviewCount) model;
//        self.titleLabel.setText(more.titleResId);
    }
}
