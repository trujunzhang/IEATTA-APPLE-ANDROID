package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.cells.model.IEAOrderedPeople;

public class IEAOrderedPeopleCell extends IEAViewHolder {
    public static int layoutResId = R.layout.near_restaurant_cell;

    private IEAOrderedPeopleCell self = this;

//    @IBOutlet weak var avatarView: AvatarView!

    private TextView nameLabel;
    private TextView subtitleLabel;

    public IEAOrderedPeopleCell(View itemView) {
        super(itemView);
    }

    @Override
    public void updateWithModel(Object model) {
        IEAOrderedPeople more  = (IEAOrderedPeople) model;
//        self.nameLabel.setText(more.displayName);
    }
}
