package com.ieatta.android.modules.view.search;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.ieatta.android.R;
import com.ieatta.android.cache.RatedModelReviewCount;
import com.ieatta.android.modules.IEAReviewSegueTableViewController;
import com.ieatta.android.modules.adapter.NSIndexPath;
import com.ieatta.android.modules.cells.IEAReadReviewsCell;
import com.ieatta.android.modules.cells.headerview.IEAReadReviewsHeader;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.enums.ReviewType;

import info.hoang8f.android.segmented.SegmentedGroup;

enum ReadReviewsSection {
    //         sectionHeader                 ,//= 0
    sectionRatedModelReviewCounts //= 1
}

/**
 * Created by djzhang on 12/1/15.
 */
public class IEAReadReviewsViewController extends IEAReviewSegueTableViewController implements RadioGroup.OnCheckedChangeListener {
    private IEAReadReviewsViewController self = this;

    // Selected model from tableview.
    private RatedModelReviewCount selectedModel;
    private int reviewType = ReviewType.Review_Restaurant.ordinal();

    protected int getContentView() {
        return R.layout.table_reviews_view_controller;
    }

    private SegmentedGroup segmentedControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        self.segmentedControl = (SegmentedGroup) self.findViewById(R.id.segmentedControl);
        self.segmentedControl.setOnCheckedChangeListener(this);

        self.setRegisterCellClassWhenSelected(IEAReadReviewsCell.getType(), ReadReviewsSection.sectionRatedModelReviewCounts.ordinal());

//        self.setSectionHeader(self.reviewType);
    }

    @Override
    public void whenSelectedEvent(Object model, NSIndexPath indexPath) {
        super.whenSelectedEvent(model, indexPath);
    }

    @Override
    public ParseModelAbstract getPageModel() {
        return super.getPageModel();
    }

    private void setSectionHeader(int reviewType) {
//        setSectionItems([IEAReadReviewsHeader(reviewType:reviewType,viewController: self)], forSectionIndex: ReadReviewsSection.sectionHeader.rawValue)
    }

    private void showSelectedModel(RatedModelReviewCount model) {
//        self.selectedModel = model
//        self.prepareForSeeReviewSegueIdentifier(self.getManagerNavigationViewController())
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        self.reviewType = IEAReadReviewsHeader.convertToReviewType(checkedId).ordinal();
    }
}
