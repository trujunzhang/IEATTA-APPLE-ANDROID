package com.ieatta.android.modules.cells.headerview;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.extensions.viewkit.RatingImageView;
import com.tableview.adapter.IEAViewHolder;
import com.ieatta.android.modules.cells.model.IEAEventHeader;

public class IEAEventHeaderCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEAEventHeaderCell.class, R.layout.event_header_cell);
    }

    @Override
    protected boolean shouldClickItem() {
        return false;
    }

    private IEAEventHeaderCell self = this;
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

        self.restaurantNameLabel = (TextView) itemView.findViewById(R.id.restaurantNameTextView);
        self.displayNameLabel = (TextView) itemView.findViewById(R.id.displayNameTextView);
        self.ratingImageView = (RatingImageView) itemView.findViewById(R.id.business_review_star_rating);

//        self.editButton = (TextView) itemView.findViewById(R.id.editNameTextView);
//        self.editButton.setText(R.string.Edit_Event);
//        self.editButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                self.model.viewController.performSegueForEditingModel();
//            }
//        });
//        self.firstButton = (TextView) itemView.findViewById(R.id.firstTextView);
//        self.firstButton.setText(R.string.Select_People);
//        self.firstButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                self.model.viewController.addPeopleTaped();
//            }
//        });
        self.secondButton = (TextView) itemView.findViewById(R.id.secondTextView);
        self.secondButton.setText(R.string.Write_Review);
        self.secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                self.model.viewController.performSegueForWritingReview();
            }
        });
        self.thirdButton = (TextView) itemView.findViewById(R.id.thirdTextView);
        self.thirdButton.setText(R.string.See_Reviews);
        self.thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                self.model.viewController.performSegueForSeeReviews();
            }
        });
    }

    @Override
    public void render(Object value) {
        self.model = (IEAEventHeader) value;

//        self.restaurantNameLabel.setText(model.model.belongToModel.displayName);
//        self.displayNameLabel.setText(model.model.displayName);
//
//        self.ratingImageView.queryRatingInReviewsByModel(model.model);
    }
}
