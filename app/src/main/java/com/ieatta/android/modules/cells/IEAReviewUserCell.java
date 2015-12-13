package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.extensions.viewkit.AvatarView;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.adapter.enums.ViewHolderType;
import com.ieatta.com.parse.models.Team;

public class IEAReviewUserCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEAReviewUserCell.class, R.layout.review_user_cell);
    }

    @Override
    protected boolean shouldClickItem() {
        return false;
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.None;
    }

    private IEAReviewUserCell self = this;

    private AvatarView avatarView;

    private TextView titleLabel;
    private TextView subtitleLabel;

    public IEAReviewUserCell(View itemView) {
        super(itemView);
        self.avatarView = (AvatarView) itemView.findViewById(R.id.avatarView);
        self.titleLabel = (TextView) itemView.findViewById(R.id.titleTextView);
        self.subtitleLabel = (TextView) itemView.findViewById(R.id.addressTextView);
    }

    @Override
    public void render(Object value) {
        Team model  = (Team) value;
        self.titleLabel.setText(model.displayName);
        self.subtitleLabel.setText(model.getTimeAgoString());

        self.avatarView.loadNewPhotoByModel(model, R.drawable.blank_biz);
    }
}
