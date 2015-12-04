package com.ieatta.android.modules.cells.photos;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.cells.model.IEANearRestaurantMore;

public class IEAAddPhotoCell extends IEAViewHolder {
    @Override
    public CellType getType() {
        return new CellType(IEAAddPhotoCell.class, R.layout.near_restaurant_cell);
    }

    private IEAAddPhotoCell self = this;

//    @IBOutlet weak var avatarView: AvatarView!

    private TextView titleLabel;
    private TextView subtitleLabel;

    public IEAAddPhotoCell(View itemView) {
        super(itemView);
    }

    @Override
    public void updateWithModel(Object model) {
        IEANearRestaurantMore more  = (IEANearRestaurantMore) model;
        self.titleLabel.setText(more.titleResId);
    }
}
