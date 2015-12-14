package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.extensions.viewkit.RatingImageView;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.cells.model.IEANearRestaurantMore;
import com.ieatta.android.modules.common.edit.ReviewDetailForModelCell;

public class IEAReviewDetailForModelCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEAReviewDetailForModelCell.class, R.layout.review_detail_for_model_cell);
    }

    private IEAReviewDetailForModelCell self = this;

    private TextView displayNameLabel;
    private RatingImageView ratingImageView;
    private TextView dayOfVisitLabel;

    public IEAReviewDetailForModelCell(View itemView) {
        super(itemView);
        self.displayNameLabel = (TextView) itemView.findViewById(R.id.displayNameTextView);
        self.ratingImageView = (RatingImageView) itemView.findViewById(R.id.business_review_star_rating);

        self.dayOfVisitLabel =  (TextView) itemView.findViewById(R.id.dayOfVisitLabel);
    }

    @Override
    public void render(Object value) {
        ReviewDetailForModelCell model = (ReviewDetailForModelCell) value;

        self.displayNameLabel.setText(model.reviewForModel.displayName);
        self.ratingImageView.queryRatingInReviewsByModel(model.reviewForModel);
    }
}
