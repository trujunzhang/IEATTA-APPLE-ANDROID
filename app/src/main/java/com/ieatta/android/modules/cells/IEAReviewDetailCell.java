package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.com.parse.models.Review;

public class IEAReviewDetailCell extends IEAViewHolder {

    public static CellType getType() {
        return new CellType(IEAReviewDetailCell.class, R.layout.review_detail_cell);
    }

    private IEAReviewDetailCell self = this;

    private TextView reviewContentLabel;

    public IEAReviewDetailCell(View itemView) {
        super(itemView);
        self.reviewContentLabel = (TextView) itemView.findViewById(R.id.reviewContentLabel);
    }

    @Override
    public void render(Object value) {
        Review model  = (Review) value;
        self.reviewContentLabel.setText(model.content);
    }
}
