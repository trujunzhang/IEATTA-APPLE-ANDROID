package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.cells.model.IEANearRestaurantMore;

public class IEAReviewsCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEAReviewsCell.class, R.layout.near_restaurant_cell);
    }

    private IEAReviewsCell self = this;

//    @IBOutlet weak var avatarView: AvatarView!

    private TextView titleLabel;
    private TextView subtitleLabel;

    public IEAReviewsCell(View itemView) {
        super(itemView);
    }

    @Override
    public void render(Object value) {
        IEANearRestaurantMore more  = (IEANearRestaurantMore) value;
        self.titleLabel.setText(more.titleResId);
    }
}
