package com.ieatta.android.modules.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.modules.IEABaseReviewsTableViewController;
import com.ieatta.android.modules.adapter.NSIndexPath;
import com.ieatta.android.modules.cells.IEASeeReviewsCell;
import com.ieatta.android.modules.common.MainSegueIdentifier;
import com.ieatta.android.modules.common.edit.SectionSeeReviewsCellModel;
import com.ieatta.provide.IEAEditKey;

import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;


public class IEASeeReviewsInDetailViewController extends IEABaseReviewsTableViewController {
    enum SeeReviewsInDetailSection {
        sectionReviews,// = 0;
    }

//    private TextView infoLabel;
//
//    IEASeeReviewsInDetailViewController transfer(ParseModelAbstract reviewForModel) {
//        this.reviewForModel = reviewForModel;
//        return this;
//    }
//
//    // Transferd Model from previous page.
//    ParseModelAbstract reviewForModel;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        // Do any additional setup after loading the view.
//        this.transfer(this.getTransferedModel());
//
//        this.infoLabel = (TextView) this.findViewById(R.id.emptyInfoTextView);
//
//        this.getReviewsRelatedModelQueryTask().onSuccess(new Continuation<Boolean, Object>() {
//            @Override
//            public Object then(Task<Boolean> task) throws Exception {
//
//                this.configureDetailSection(this.fetchedReviews, R.string.Empty_for_Review, null, SeeReviewsInDetailSection.sectionReviews.ordinal());
//
//                return null;
//            }
//        }, Task.UI_THREAD_EXECUTOR);
//    }
//
//    // MARK: Override IEAReviewsTableViewController methods
//    @Override
//    public void registerReviewTableCells() {
//        this.setRegisterCellClassWhenSelected(IEASeeReviewsCell.getType(), SeeReviewsInDetailSection.sectionReviews.ordinal());
//    }
//
//    @Override
//    public int getQueriedReviewsLimit() {
//        return Review.NO_Limit_FETCHED_REVIEWS_IN_DetailPage;
//    }
//
//    @Override
//    public int getReviewsSectionIndex() {
//        return SeeReviewsInDetailSection.sectionReviews.ordinal();
//    }
//
//    @Override
//    protected int getContentView() {
//        return R.layout.table_controller_view_with_emptyinfo;
//    }
//
//    // MARK: Override IEABaseTableViewController methods
//    @Override
//    public ParseModelAbstract getPageModel() {
//        return this.reviewForModel;
//    }
//
//    /// Add rows for section "Reviews".
//    @Override
//    public void setItemsForReviewsSection(List<ParseModelAbstract /*Team*/> fetchedReviewPeople) {
//        List<Object> array = Review.getReviewItems(this.fetchedReviews, fetchedReviewPeople);
//
//        List<SectionSeeReviewsCellModel> items = new LinkedList<>();
//        for (int i = 0; i < array.size() / 2; ++i) {
//            items.add(new SectionSeeReviewsCellModel(IEAEditKey.Section_Title, array.get(i * 2 + 0), array.get(i * 2 + 1)));
//        }
//
//        setSectionItems(items, SeeReviewsInDetailSection.sectionReviews.ordinal());
//    }
//
//    @Override
//    public void whenSelectedEvent(Object model, NSIndexPath indexPath) {
//        SectionSeeReviewsCellModel cellModel = (SectionSeeReviewsCellModel) model;
//
//        this.selectedReview = cellModel.writedReview;
//        this.performSegueWithIdentifier(MainSegueIdentifier.detailReviewSegueIdentifier, this);
//    }
//
//    public void configureDetailSection(List items, int emptyInfoResId, CellType type, int forSectionIndex) {
//        if (items.size() == 0) {
//            this.infoLabel.setVisibility(View.VISIBLE);
//            this.infoLabel.setText(emptyInfoResId);
//        } else {
//            this.infoLabel.setVisibility(View.GONE);
//
//            this.configureReviewsSection();
//        }
//    }

}
