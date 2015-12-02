package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.com.parse.models.Event;

public class IEARestaurantEventsCell extends IEAViewHolder {
    public static int layoutResId = R.layout.near_restaurant_cell;

    private IEARestaurantEventsCell self = this;

//    @IBOutlet weak var avatarView: AvatarView!

    private TextView infoLabel;
    private TextView subtitleLabel;

    public IEARestaurantEventsCell(View itemView) {
        super(itemView);
    }

    @Override
    public void updateWithModel(Object model) {
        Event more  = (Event) model;
        self.infoLabel.setText(more.displayName);

    }
}
