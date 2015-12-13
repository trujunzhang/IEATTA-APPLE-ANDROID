package com.ieatta.android.modules.cells.headerfooterview;

import android.view.View;
import android.widget.Button;

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
        return new CellType(IEAMoreReviewsFooterCell.class, R.layout.businesspage_section_footer);
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

    private Button footerLargeButton;

    public IEAMoreReviewsFooterCell(View itemView) {
        super(itemView);

        self.footerLargeButton = (Button) itemView.findViewById(R.id.footer_large_button);
    }

    @Override
    public void render(Object value) {
        SectionMoreReviewsFooterCellModel model = (SectionMoreReviewsFooterCellModel) value;

        self.footerLargeButton.setText(R.string.See_More_Reviews);
    }
}
