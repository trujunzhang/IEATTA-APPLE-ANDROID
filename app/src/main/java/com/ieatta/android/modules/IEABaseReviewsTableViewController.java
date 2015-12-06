package com.ieatta.android.modules;

import android.os.Bundle;

import com.ieatta.android.R;
import com.ieatta.android.extensions.viewkit.NSIndexPath;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Review;
import com.ieatta.com.parse.models.Team;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 12/1/15.
 */
/// IEABaseReviewsTableViewController <|-- IEAReviewsInDetailTableViewController
/// IEABaseReviewsTableViewController <|-- IEASeeReviewsInDetailViewController
public abstract class IEABaseReviewsTableViewController extends IEAReviewSegueTableViewController {
    private IEABaseReviewsTableViewController self = this;


    // Selected model from tableview.
    protected Review selectedReview;
    // Fetched list by quering database.
    protected List<ParseModelAbstract> fetchedReviews = new LinkedList<>();//Review

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        self.registerReviewTableCells();
    }

    protected Task<Boolean> getReviewsReleatdModelQueryTask() {
        return Review.queryReviews(self.getPageModel(),self.getQueriedReviewsLimit()).onSuccessTask(new Continuation<List<ParseModelAbstract>, Task<List<ParseModelAbstract>>>() {
            @Override
            public Task<List<ParseModelAbstract>> then(Task<List<ParseModelAbstract>> task) throws Exception {
                self.fetchedReviews = task.getResult();//Review
                return Team.queryTeamByPoints(Review.getUserPoints(self.fetchedReviews));
            }
        }).onSuccessTask(new Continuation<List<ParseModelAbstract>, Task<Boolean> >() {
            @Override
            public Task<Boolean>  then(Task<List<ParseModelAbstract>> task) throws Exception {
                return self.getPhotosForModelsTask(task);
            }
        });

//        return Review.queryReviews(self.getPageModel(), self.getQueriedReviewsLimit())
//                .continueWith(new Continuation<LinkedList<ParseModelAbstract>, Object>() {
//                    @Override
//                    public Object then(Task<LinkedList<ParseModelAbstract>> task) throws Exception {
//                        self.fetchedReviews = new LinkedList<Object>((Collection<?>) task.getResult());//Review
//                return Team.queryTeam(Review.getUserPoints(self.fetchedReviews));
////                        return null;
//                    }
//                }).continueWith(new Continuation<Object, Object>() {
//                    @Override
//                    public Object then(Task<Object> task) throws Exception {
//
//                        return self.getPhotosForModelsTask(task);
//                    }
//                });
    }

    protected void configureReviewsSection(List<Object> fetchedReviewPeople) {//Team
        self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Review_Highlights), self.getReviewsSectionIndex());
        self.setItemsForReviewsSection(fetchedReviewPeople);
    }

    protected abstract void setItemsForReviewsSection(List<Object> fetchedReviewPeople);//Team

    protected abstract void registerReviewTableCells();

    protected int getQueriedReviewsLimit() {
        return Review.MAX_FETCHED_REVIEWS_IN_DetailPage;
    }

    protected abstract int getReviewsSectionIndex();

    protected Review getReview(NSIndexPath indexPath) {
        return (Review) self.fetchedReviews.get(indexPath.section- self.getReviewsSectionIndex());
    }

    public void performSegueForWritingReview(){
//        self.presentViewController(UINavigationController(rootViewController: IEAWriteReviewViewController.createInstance(self.getPageModel())), animated: true, completion: nil)
    }

//    override func segueForReviewDetailViewController(destination:IEAReviewDetailViewController){
//        destination.transferToReviewDetail(self.getPageModel(), review: self.selectedReview!)
//    }

}
