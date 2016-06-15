package com.ieatta.android.modules;

import android.content.Intent;
import android.os.Bundle;

import com.ieatta.android.R;
import com.ieatta.android.cache.IntentCache;
import com.ieatta.android.modules.adapter.NSIndexPath;
import com.ieatta.android.modules.common.MainSegueIdentifier;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.view.IEAReviewDetailViewController;
import com.ieatta.android.modules.view.posts.IEAWriteReviewViewController;


import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

/// IEABaseReviewsTableViewController <|-- IEAReviewsInDetailTableViewController
/// IEABaseReviewsTableViewController <|-- IEASeeReviewsInDetailViewController
public abstract class IEABaseReviewsTableViewController extends IEAReviewSegueTableViewController {

    // Selected model from tableview.
    protected Object selectedReview;
    // Fetched list by quering database.
    protected List fetchedReviews = new LinkedList<>();//Review
    protected List fetchedReviewPeople = new LinkedList<>();//Review

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        this.registerReviewTableCells();
    }

//    protected Task<Boolean> getReviewsRelatedModelQueryTask() {
//        return Review.queryReviews(this.getPageModel(), this.getQueriedReviewsLimit())
//                .onSuccessTask(new Continuation<List , Task<List >>() {
//                    @Override
//                    public Task<List > then(Task<List > task) throws Exception {
//                        Object object = task;
//                        this.fetchedReviews = task.getResult();//Review
//                        return Team.queryTeamByPoints(Review.getUserPoints(this.fetchedReviews));
//                    }
//                }).onSuccessTask(new Continuation<List , Task<Boolean>>() {
//                    @Override
//                    public Task<Boolean> then(Task<List > task) throws Exception {
//                        Object object = task.getResult();
//                        this.fetchedReviewPeople = task.getResult();
//                        return this.getPhotosForModelsTask(task);
//                    }
//                });
//    }
//
//    protected void configureReviewsSection() {
//        this.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Review_Highlights), this.getReviewsSectionIndex());
//        this.setItemsForReviewsSection(this.fetchedReviewPeople);
//    }
//
//    protected abstract void setItemsForReviewsSection(List  fetchedReviewPeople);//Team
//
//    protected abstract void registerReviewTableCells();
//
//    protected int getQueriedReviewsLimit() {
//        return Review.MAX_FETCHED_REVIEWS_IN_DetailPage;
//    }
//
//    protected int getReviewsSectionIndex() {
//        return -1;
//    }
//
//    protected Review getReview(NSIndexPath indexPath) {
//        return (Review) this.fetchedReviews.get(indexPath.section - this.getReviewsSectionIndex());
//    }
//
//    public void performSegueForWritingReview() {
//        this.performSegueWithIdentifier(MainSegueIdentifier.postReviewSegueIdentifier, this);
//    }
//
//    @Override
//    protected void segueForPostReviewViewController(IEAWriteReviewViewController destination, Intent sender) {
//        this.setTransferedModel(sender, this.getPageModel());
//    }
//
//    @Override
//    protected void segueForReviewDetailViewController(IEAReviewDetailViewController destination, Intent sender) {
//        this.setTransferedModel(sender, this.getPageModel());
//        this.setTransferedModel(sender, this.selectedReview, IntentCache.selectedReview);
////        destination.transferToReviewDetail(this.getPageModel(), review: this.selectedReview!)
//    }

}
