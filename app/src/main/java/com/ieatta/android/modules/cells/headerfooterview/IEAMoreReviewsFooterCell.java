package com.ieatta.android.modules.cells.headerfooterview;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.adapter.enums.ViewHolderType;
import com.ieatta.android.modules.common.edit.SectionMoreReviewsFooterCellModel;

/**
 * Created by djzhang on 12/2/15.
 */
public class IEAMoreReviewsFooterCell extends IEAViewHolder {
    public static CellType  getType() {
        return new CellType(IEAMoreReviewsFooterCell.class, R.layout.more_reviews_footer_cell);
    }

    @Override
    protected boolean shouldClickItem() {
        return false;
    }
    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.footer;
    }

    private IEAMoreReviewsFooterCell self = this;

    private TextView titleView;

    public IEAMoreReviewsFooterCell(View itemView) {
        super(itemView);

        self.titleView = (TextView) itemView.findViewById(R.id.headerTextView);
    }

    @Override
    public void render(Object value) {
        SectionMoreReviewsFooterCellModel model = (SectionMoreReviewsFooterCellModel) value;

        self.titleView.setText(R.string.Select_Person);

    }
}
