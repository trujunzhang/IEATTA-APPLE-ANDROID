package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.tableview.storage.models.CellType;
import com.ieatta.android.extensions.viewkit.AvatarView;
import com.tableview.adapter.IEAViewHolder;


public class IEAReviewUserCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEAReviewUserCell.class, R.layout.review_user_cell);
    }

    @Override
    protected boolean shouldClickItem() {
        return false;
    }

    private IEAReviewUserCell self = this;

    private AvatarView avatarView;

    private TextView titleLabel;
    private TextView timeAgoTextView;

    public IEAReviewUserCell(View itemView) {
        super(itemView);
        self.avatarView = (AvatarView) itemView.findViewById(R.id.avatarView);
        self.titleLabel = (TextView) itemView.findViewById(R.id.titleTextView);
        self.timeAgoTextView = (TextView) itemView.findViewById(R.id.timeAgoTextView);
    }

    @Override
    public void render(Object value) {
//        Team model = (Team) value;
//        self.titleLabel.setText(model.displayName);
//        self.timeAgoTextView.setText(model.writedReviewTimeAgo);
//
//        self.avatarView.loadNewPhotoByModel(model, R.drawable.blank_user_small);
    }
}
