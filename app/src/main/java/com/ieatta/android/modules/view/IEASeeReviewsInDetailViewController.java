package com.ieatta.android.modules.view;

import android.os.Bundle;
import android.virtualbreak.com.debug.AppDebugManager;
import android.virtualbreak.com.manualdatabase.ActivityModelDebug;

import com.ieatta.android.modules.IEABaseReviewsTableViewController;
import com.ieatta.android.modules.adapter.NSIndexPath;
import com.ieatta.android.modules.cells.IEASeeReviewsCell;
import com.ieatta.android.modules.common.MainSegueIdentifier;
import com.ieatta.android.modules.common.edit.SectionSeeReviewsCellModel;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Review;

import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 12/1/15.
 */

enum SeeReviewsInDetailSection {
    sectionReviews,// = 0;
}

public class IEASeeReviewsInDetailViewController extends IEABaseReviewsTableViewController {
    private IEASeeReviewsInDetailViewController self = this;

    IEASeeReviewsInDetailViewController transfer(ParseModelAbstract reviewForModel) {
        self.reviewForModel = reviewForModel;
        return self;
    }

    // Transferd Model from previous page.
    ParseModelAbstract reviewForModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Do any additional setup after loading the view.
//        self.transfer(self.getTransferedModel());
        // TODO djzhang: test
        self.transfer(ActivityModelDebug.getReviewForModel());

        self.getReviewsRelatedModelQueryTask().onSuccess(new Continuation<Boolean, Object>() {
            @Override
            public Object then(Task<Boolean> task) throws Exception {
                self.configureReviewsSection(self.fetchedReviewPeople);
                return null;
            }
        }).continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
                if (task.isFaulted()) {

                }
                return null;
            }
        });
    }

    // MARK: Override IEAReviewsTableViewController methods
    @Override
    public void registerReviewTableCells() {
        self.setRegisterCellClassWhenSelected(IEASeeReviewsCell.getType(), SeeReviewsInDetailSection.sectionReviews.ordinal());
    }

    @Override
    public int getQueriedReviewsLimit() {
        return Review.NO_Limit_FETCHED_REVIEWS_IN_DetailPage;
    }

    @Override
    public int getReviewsSectionIndex() {
        return SeeReviewsInDetailSection.sectionReviews.ordinal();
    }

    // MARK: Override IEABaseTableViewController methods
    @Override
    public ParseModelAbstract getPageModel() {
        return self.reviewForModel;
    }

    /// Add rows for section "Reviews".
    @Override
    public void setItemsForReviewsSection(List<ParseModelAbstract /*Team*/> fetchedReviewPeople) {
        Object object = fetchedReviewPeople;
        List<Object> array = Review.getReviewItems(self.fetchedReviews, fetchedReviewPeople);

        List<SectionSeeReviewsCellModel> items = new LinkedList<>();
        for (int i = 0; i < array.size() / 2; ++i) {
            items.add(new SectionSeeReviewsCellModel(IEAEditKey.Section_Title, array.get(i * 2 + 0), array.get(i * 2 + 1)));
        }

        setSectionItems(items, SeeReviewsInDetailSection.sectionReviews.ordinal());
    }

    @Override
    public void whenSelectedEvent(Object model, NSIndexPath indexPath) {
        SectionSeeReviewsCellModel cellModel = (SectionSeeReviewsCellModel) model;

        self.selectedReview = cellModel.writedReview;
        self.performSegueWithIdentifier(MainSegueIdentifier.detailReviewSegueIdentifier, self);
    }
}
