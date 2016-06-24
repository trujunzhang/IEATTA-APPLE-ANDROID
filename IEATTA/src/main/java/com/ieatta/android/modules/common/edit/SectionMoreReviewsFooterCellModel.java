package com.ieatta.android.modules.common.edit;

import com.ieatta.android.modules.IEAReviewsInDetailTableViewController;
import com.ieatta.provide.IEAEditKey;
import com.tableview.model.EditBaseCellModel;


public class SectionMoreReviewsFooterCellModel extends EditBaseCellModel {
    public int otherReviewCount;

    public SectionMoreReviewsFooterCellModel(int otherReviewCount) {
        super(IEAEditKey.Section_Title);
        this.otherReviewCount = otherReviewCount;
    }
//    public ParseModelAbstract reviewForModel;

//    public SectionMoreReviewsFooterCellModel(IEAEditKey editKey, ParseModelAbstract reviewForModel, IEAReviewsInDetailTableViewController viewController) {
//        super(editKey);
//        this.reviewForModel = reviewForModel;
//        this.viewController = viewController;
//    }

}
