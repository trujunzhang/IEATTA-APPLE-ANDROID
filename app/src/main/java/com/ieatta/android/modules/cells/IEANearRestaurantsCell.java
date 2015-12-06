package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.CellType;
import com.ieatta.android.extensions.viewkit.AvatarView;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.com.parse.models.Restaurant;

public class IEANearRestaurantsCell extends IEAViewHolder {

    public static CellType getType() {
        return new CellType(IEANearRestaurantsCell.class, R.layout.near_restaurant_cell);
    }

    private IEANearRestaurantsCell self = this;

    private AvatarView avatarView;

    private TextView titleLabel;
    private TextView subtitleLabel;

    public IEANearRestaurantsCell(View itemView) {
        super(itemView);
        self.avatarView = (AvatarView) itemView.findViewById(R.id.avatarView);
        self.titleLabel = (TextView) itemView.findViewById(R.id.titleTextView);
        self.subtitleLabel = (TextView) itemView.findViewById(R.id.addressTextView);
    }

    @Override
    public void render(Object value) {
        Restaurant model  = (Restaurant) value;
        self.titleLabel.setText(model.displayName);
        self.subtitleLabel.setText(((Restaurant) value).getGoogleMapAddress());

        self.avatarView.loadNewPhotoByModel(model, R.drawable.blank_biz);
    }
}
