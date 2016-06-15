package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.extensions.viewkit.AvatarView;
import com.tableview.adapter.IEAViewHolder;
import com.ieatta.android.modules.common.edit.SectionSeeReviewsCellModel;

public class IEASeeReviewsCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEASeeReviewsCell.class, R.layout.see_reviews_cell);
    }

    private IEASeeReviewsCell self = this;

    private AvatarView avatarView;

    private TextView titleLabel;
    private TextView timeAgoTextView;

    private ImageView business_review_star_rating;
    private TextView reviewContentLabel;

    public IEASeeReviewsCell(View itemView) {
        super(itemView);

        self.avatarView = (AvatarView) itemView.findViewById(R.id.avatarView);
        self.titleLabel = (TextView) itemView.findViewById(R.id.titleTextView);
        self.timeAgoTextView = (TextView) itemView.findViewById(R.id.timeAgoTextView);

        self.business_review_star_rating = (ImageView) itemView.findViewById(R.id.business_review_star_rating);
        self.reviewContentLabel = (TextView) itemView.findViewById(R.id.reviewContentLabel);
    }

    @Override
    public void render(Object value) {
        SectionSeeReviewsCellModel model = (SectionSeeReviewsCellModel) value;

//        self.titleLabel.setText(model.user.displayName);
//        self.timeAgoTextView.setText(model.timeAgoString);
//
//        self.avatarView.loadNewPhotoByModel(model.user, R.drawable.blank_user_small);
//
//        self.business_review_star_rating.setImageLevel(model.ratingValue);
//        self.reviewContentLabel.setText(model.reviewContent);
    }
}
