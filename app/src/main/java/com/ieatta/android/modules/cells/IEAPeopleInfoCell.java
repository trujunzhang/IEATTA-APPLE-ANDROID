package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.extensions.viewkit.AvatarView;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.com.parse.models.Team;

public class IEAPeopleInfoCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEAPeopleInfoCell.class, R.layout.people_info_cell);
    }

    private IEAPeopleInfoCell self = this;

    private AvatarView avatarView;
    private TextView nameLabel;
    private TextView emailLabel;
    private TextView addressLabel;

    public IEAPeopleInfoCell(View itemView) {
        super(itemView);
        self.avatarView = (AvatarView) itemView.findViewById(R.id.avatarView);
        self.nameLabel = (TextView) itemView.findViewById(R.id.titleTextView);
        self.emailLabel = (TextView) itemView.findViewById(R.id.emailTextView);
        self.addressLabel = (TextView) itemView.findViewById(R.id.addressTextView);
    }

    @Override
    public void render(Object value) {
        Team model = (Team) value;
        self.nameLabel.setText(model.displayName);
        self.emailLabel.setText(model.email);
        self.addressLabel.setText(model.address);

        self.avatarView.loadNewPhotoByModel(model, R.drawable.blank_user_small);
    }
}
