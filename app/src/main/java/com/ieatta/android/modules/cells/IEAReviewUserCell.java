package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.com.parse.models.Team;

public class IEAReviewUserCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEAReviewUserCell.class, R.layout.review_user_cell);
    }

    private IEAReviewUserCell self = this;

//    @IBOutlet weak var avatarView: AvatarView!

    private TextView titleLabel;
    private TextView subtitleLabel;

    public IEAReviewUserCell(View itemView) {
        super(itemView);

        self.titleLabel = (TextView) itemView.findViewById(R.id.titleTextView);
        self.subtitleLabel = (TextView) itemView.findViewById(R.id.addressTextView);
    }

    @Override
    public void updateWithModel(Object model) {
        Team more  = (Team) model;
        self.titleLabel.setText(more.displayName);
    }
}
