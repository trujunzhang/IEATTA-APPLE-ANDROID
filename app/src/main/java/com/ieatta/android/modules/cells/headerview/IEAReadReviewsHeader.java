package com.ieatta.android.modules.cells.headerview;

import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.cells.enums.IEAReadReviewsHeaderSegmentedType;
import com.ieatta.android.modules.cells.model.IEAEventHeader;
import com.ieatta.com.parse.models.enums.ReviewType;

import info.hoang8f.android.segmented.SegmentedGroup;


/**
 * Created by djzhang on 12/2/15.
 */
public class IEAReadReviewsHeader extends IEAViewHolder implements RadioGroup.OnCheckedChangeListener {

    private SegmentedGroup segmentedControl;

    public static CellType getType() {
        return new CellType(IEAReadReviewsHeader.class, R.layout.read_reviews_header);
    }

    @Override
    protected boolean shouldClickItem() {
        return false;
    }

    private IEAReadReviewsHeader self = this;

    public IEAReadReviewsHeader(View itemView) {
        super(itemView);

        self.segmentedControl = (SegmentedGroup) itemView.findViewById(R.id.segmentedControl);
        self.segmentedControl.setOnCheckedChangeListener(this);
    }

    @Override
    public void render(Object value) {
        IEAReadReviewsHeader model = (IEAReadReviewsHeader) value;


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
    }
}
