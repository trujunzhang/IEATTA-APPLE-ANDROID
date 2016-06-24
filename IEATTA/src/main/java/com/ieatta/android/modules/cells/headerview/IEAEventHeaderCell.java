package com.ieatta.android.modules.cells.headerview;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.tableview.storage.models.CellType;
import com.ieatta.android.extensions.viewkit.RatingImageView;
import com.tableview.adapter.IEAViewHolder;
import com.ieatta.android.modules.cells.model.IEAEventHeader;

import org.ieatta.database.provide.ReviewType;

public class IEAEventHeaderCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEAEventHeaderCell.class, R.layout.event_header_cell);
    }

    @Override
    protected boolean shouldClickItem() {
        return false;
    }

    private IEAEventHeader model;

    private TextView restaurantNameLabel;
    private TextView displayNameLabel;
    private RatingImageView ratingImageView;

    private TextView editButton;
    private TextView firstButton;
    private TextView secondButton;
    private TextView thirdButton;

    public IEAEventHeaderCell(View itemView) {
        super(itemView);

        this.restaurantNameLabel = (TextView) itemView.findViewById(R.id.restaurantNameTextView);
        this.displayNameLabel = (TextView) itemView.findViewById(R.id.displayNameTextView);
        this.ratingImageView = (RatingImageView) itemView.findViewById(R.id.business_review_star_rating);

        this.editButton = (TextView) itemView.findViewById(R.id.editNameTextView);
        this.editButton.setText(R.string.Edit_Event);
        this.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                this.model.viewController.performSegueForEditingModel();
            }
        });
        this.firstButton = (TextView) itemView.findViewById(R.id.firstTextView);
        this.firstButton.setText(R.string.Select_People);
        this.firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                this.model.viewController.addPeopleTaped();
            }
        });
        this.secondButton = (TextView) itemView.findViewById(R.id.secondTextView);
        this.secondButton.setText(R.string.Write_Review);
        this.secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                this.model.viewController.performSegueForWritingReview();
            }
        });
        this.thirdButton = (TextView) itemView.findViewById(R.id.thirdTextView);
        this.thirdButton.setText(R.string.See_Reviews);
        this.thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                this.model.viewController.performSegueForSeeReviews();
            }
        });
    }

    private void setEventHeader(IEAEventHeader header) {
        this.model = header;

        this.restaurantNameLabel.setText(model.restaurantName);
        this.displayNameLabel.setText(model.eventName);

        this.ratingImageView.queryRatingInReviewsByModel(model.eventUUID, ReviewType.Review_Event);
    }

    @Override
    public void render(Object value) {
        this.setEventHeader((IEAEventHeader) value);
    }
}
