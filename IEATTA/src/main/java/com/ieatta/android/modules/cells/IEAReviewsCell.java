package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.viewkit.AvatarView;
import com.tableview.storage.models.CellType;
import com.tableview.adapter.IEAViewHolder;

import org.ieatta.database.query.IEAReviewsCellModel;


public class IEAReviewsCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEAReviewsCell.class, R.layout.cell_reviews);
    }

    @Override
    protected boolean shouldOnClickItem() {
        return false;
    }

    private AvatarView avatarView;

    private TextView titleLabel;
    private TextView timeAgoTextView;

    private ImageView business_review_star_rating;
    private TextView reviewContentLabel;

    public IEAReviewsCell(View itemView) {
        super(itemView);

        this.avatarView = (AvatarView) itemView.findViewById(R.id.avatarView);
        this.titleLabel = (TextView) itemView.findViewById(R.id.titleTextView);
        this.timeAgoTextView = (TextView) itemView.findViewById(R.id.timeAgoTextView);

        this.business_review_star_rating = (ImageView) itemView.findViewById(R.id.business_review_star_rating);
        this.reviewContentLabel = (TextView) itemView.findViewById(R.id.reviewContentLabel);
    }

    private void setReviewCell(IEAReviewsCellModel model) {
        this.avatarView.loadNewPhotoByModel(model.userUUID);

        this.titleLabel.setText(model.title);
        this.timeAgoTextView.setText(model.timeAgoString);
        this.business_review_star_rating.setImageLevel(model.ratingValue);
        this.reviewContentLabel.setText(model.reviewContent);
    }

    @Override
    public void render(Object value) {
        this.setReviewCell((IEAReviewsCellModel) value);
    }
}
