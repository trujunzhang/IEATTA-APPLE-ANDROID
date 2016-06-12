package com.tableview.model;

import org.ieatta.database.provide.ReviewType;
import com.ieatta.provide.IEAEditKey;

public class SectionMoreReviewsFooterCellModel extends EditBaseCellModel {
    public int otherReviewCount;

    public SectionMoreReviewsFooterCellModel(int otherReviewCount) {
        super(IEAEditKey.Section_Title);
        this.otherReviewCount = otherReviewCount;
    }

}
