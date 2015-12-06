package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.com.parse.models.Review;

public class IEAReviewDetailCell extends IEAViewHolder {

    public static CellType getType() {
        return new CellType(IEAReviewDetailCell.class, R.layout.near_restaurant_cell);
    }

    private IEAReviewDetailCell self = this;

//    @IBOutlet weak var avatarView: AvatarView!

    private TextView titleLabel;
    private TextView subtitleLabel;

    public IEAReviewDetailCell(View itemView) {
        super(itemView);
    }

    @Override
    public void render(Object value) {
        Review more  = (Review) value;
//        self.titleLabel.setText(more.titleResId);
    }
}
