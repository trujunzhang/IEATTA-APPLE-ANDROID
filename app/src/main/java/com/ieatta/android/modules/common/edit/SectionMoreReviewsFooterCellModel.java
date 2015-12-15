package com.ieatta.android.modules.common.edit;

import com.ieatta.android.modules.IEAReviewsInDetailTableViewController;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.com.parse.ParseModelAbstract;

public class SectionMoreReviewsFooterCellModel extends EditBaseCellModel {
    public IEAReviewsInDetailTableViewController viewController;
    public ParseModelAbstract reviewForModel ;

    public SectionMoreReviewsFooterCellModel(IEAEditKey editKey, ParseModelAbstract reviewForModel, IEAReviewsInDetailTableViewController viewController) {
        super(editKey);
        this.reviewForModel = reviewForModel;
        this.viewController = viewController;
    }

}
