package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ieatta.android.R;
import com.tableview.storage.models.CellType;
import com.tableview.adapter.IEAViewHolder;
import com.tableview.adapter.enums.ViewHolderType;


public class IEAReviewsCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEAReviewsCell.class, R.layout.reviews_cell);
    }

    private IEAReviewsCell self = this;

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.section;
    }

    private ImageView business_review_star_rating;
    private TextView reviewContentLabel;

    public IEAReviewsCell(View itemView) {
        super(itemView);

        self.business_review_star_rating = (ImageView) itemView.findViewById(R.id.business_review_star_rating);
        self.reviewContentLabel = (TextView) itemView.findViewById(R.id.reviewContentLabel);
    }

    @Override
    public void render(Object value) {
//        Review model = (Review) value;
//        self.business_review_star_rating.setImageLevel(model.rate);
//        self.reviewContentLabel.setText(model.content);
    }
}
