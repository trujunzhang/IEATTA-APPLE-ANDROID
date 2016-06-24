package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.android.R;
import com.tableview.storage.models.CellType;
import com.ieatta.android.extensions.viewkit.RatingImageView;
import com.tableview.adapter.IEAViewHolder;
import com.ieatta.android.modules.common.edit.ReviewDetailForModelCell;

public class IEAReviewDetailForModelCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEAReviewDetailForModelCell.class, R.layout.review_detail_for_model_cell);
    }

    private IEAReviewDetailForModelCell self = this;

    @Override
    protected boolean shouldClickItem() {
        return false;
    }


    private TextView displayNameLabel;
    private RatingImageView ratingImageView;
    private TextView timeAgoLabel;

    public IEAReviewDetailForModelCell(View itemView) {
        super(itemView);
        self.displayNameLabel = (TextView) itemView.findViewById(R.id.displayNameTextView);
        self.ratingImageView = (RatingImageView) itemView.findViewById(R.id.business_review_star_rating);

        self.timeAgoLabel = (TextView) itemView.findViewById(R.id.dayOfVisitLabel);
    }

    @Override
    public void render(Object value) {
//        ReviewDetailForModelCell model = (ReviewDetailForModelCell) value;
//
//        self.displayNameLabel.setText(model.reviewForModel.displayName);
//        self.ratingImageView.queryRatingInReviewsByModel(model.reviewForModel);
//
//        String timeAgoTitle = EnvironmentUtils.sharedInstance.getGlobalContext().getResources().getString(R.string.Date_of_visit);
//        String timeAgoText = timeAgoTitle + ": " + model.timeAgoString;
//        self.timeAgoLabel.setText(timeAgoText);
    }
}
