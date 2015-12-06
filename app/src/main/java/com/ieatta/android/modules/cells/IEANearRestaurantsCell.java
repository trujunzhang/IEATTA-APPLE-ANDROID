package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.CellType;
import com.ieatta.android.extensions.viewkit.AvatarView;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.cells.model.IEANearRestaurantMore;
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
    public void render(Object model) {
        Restaurant more  = (Restaurant) model;
        self.titleLabel.setText(more.displayName);
        self.subtitleLabel.setText(((Restaurant) model).getGoogleMapAddress());

        self.avatarView.loadNewPhotoByModel(more,R.drawable.black_biz);
//        self.avatarView.loadNewPhoto(byModel: model, placeHolder: UIImage.DefaultRestaurantIcon())
    }
}
