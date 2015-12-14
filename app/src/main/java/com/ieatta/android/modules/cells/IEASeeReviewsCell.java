package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.cells.model.IEANearRestaurantMore;

public class IEASeeReviewsCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEASeeReviewsCell.class, R.layout.see_reviews_cell);
    }

    private IEASeeReviewsCell self = this;

//    @IBOutlet weak var avatarView: AvatarView!

    private TextView titleLabel;
    private TextView subtitleLabel;

    public IEASeeReviewsCell(View itemView) {
        super(itemView);
    }

    @Override
    public void render(Object value) {
        IEANearRestaurantMore more  = (IEANearRestaurantMore) value;
        self.titleLabel.setText(more.titleResId);
    }
}
