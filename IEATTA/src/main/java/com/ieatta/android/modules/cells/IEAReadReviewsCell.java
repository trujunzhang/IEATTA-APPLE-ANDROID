package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.cache.RatedModelReviewCount;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.extensions.viewkit.AvatarView;
import com.ieatta.android.extensions.viewkit.RatingImageView;
import com.tableview.adapter.IEAViewHolder;



public class IEAReadReviewsCell extends IEAViewHolder {

    public static CellType getType() {
        return new CellType(IEAReadReviewsCell.class, R.layout.read_reviews_cell);
    }

    private IEAReadReviewsCell self = this;

    private AvatarView avatarView;
    private TextView titleLabel;
    private RatingImageView ratingImageView;

    public IEAReadReviewsCell(View itemView) {
        super(itemView);
        self.avatarView = (AvatarView) itemView.findViewById(R.id.avatarView);
        self.titleLabel = (TextView) itemView.findViewById(R.id.titleTextView);
        self.ratingImageView = (RatingImageView) itemView.findViewById(R.id.business_review_star_rating);
    }

    @Override
    public void render(Object value) {
//        RatedModelReviewCount model = (RatedModelReviewCount) value;
//        self.titleLabel.setText(model.reviewTitle);
//
//        if (model.reviewType == ReviewType.Review_Event) {
//            self.avatarView.configureAvatar(R.drawable.nav_events);
//        } else {
//            ParseModelAbstract reviewedModel = ParseModelAbstract.getInstanceFromType(model.modelType, model.reviewRef);
//            self.avatarView.loadNewPhotoByModel(reviewedModel, model.getPlaceHolderImage());
//        }
//        self.ratingImageView.queryRatingInReviewsByReview(model);
    }
}
