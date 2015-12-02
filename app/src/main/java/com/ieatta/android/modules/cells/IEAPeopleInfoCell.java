package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.com.parse.models.Team;

public class IEAPeopleInfoCell extends IEAViewHolder {
    public static int layoutResId = R.layout.near_restaurant_cell;

    private IEAPeopleInfoCell self = this;

//    @IBOutlet weak var avatarView: AvatarView!

    private TextView nameLabel;
    private TextView emailLabel;

    public IEAPeopleInfoCell(View itemView) {
        super(itemView);
    }

    @Override
    public void updateWithModel(Object model) {
        Team more  = (Team) model;
        self.nameLabel.setText(more.displayName);
        self.emailLabel.setText(more.email);
    }
}
