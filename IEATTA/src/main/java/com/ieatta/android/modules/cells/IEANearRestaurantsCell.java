package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.tableview.storage.models.CellType;
import com.ieatta.android.extensions.viewkit.AvatarView;
import com.tableview.adapter.IEAViewHolder;

import org.ieatta.database.models.DBRestaurant;
import org.wikipedia.views.GoneIfEmptyTextView;


public class IEANearRestaurantsCell extends IEAViewHolder {

    public static CellType getType() {
        return new CellType(IEANearRestaurantsCell.class, R.layout.near_restaurant_cell);
    }

    private AvatarView avatarView;

    private TextView titleLabel;
    private GoneIfEmptyTextView subtitleLabel;

    public IEANearRestaurantsCell(View itemView) {
        super(itemView);
        this.avatarView = (AvatarView) itemView.findViewById(R.id.page_list_item_image);
        this.titleLabel = (TextView) itemView.findViewById(R.id.page_list_item_title);
        this.subtitleLabel = (GoneIfEmptyTextView) itemView.findViewById(R.id.page_list_item_description);
    }

    private void setRestaurant(DBRestaurant model) {
        this.titleLabel.setText(model.getDisplayName());
        this.subtitleLabel.setText(model.getGoogleMapAddress());
        this.avatarView.loadNewPhotoByModel(model.getUUID());
    }

    @Override
    public void render(Object value) {
        this.setRestaurant((DBRestaurant) value);
    }
}
