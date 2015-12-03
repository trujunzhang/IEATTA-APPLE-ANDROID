package com.ieatta.android.modules;

import android.os.Bundle;

import com.ieatta.android.extensions.viewkit.NSIndexPath;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Review;
import com.ieatta.com.parse.models.Team;

import java.util.Collection;
import java.util.LinkedList;

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
    LinkedList<Object> fetchedReviews = new LinkedList<>();//Review

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        self.registerReviewTableCells();
    }


    protected Task<Object> getReviewsReleatdModelQueryTask() {
        return Review.queryReviews(self.getPageModel(), self.getQueriedReviewsLimit())
                .continueWith(new Continuation<LinkedList<ParseModelAbstract>, Object>() {
                    @Override
                    public Object then(Task<LinkedList<ParseModelAbstract>> task) throws Exception {
                        self.fetchedReviews = new LinkedList<Object>((Collection<?>) task.getResult());//Review
//                return Team.queryTeam(Review.getUserPoints(self.fetchedReviews));
                        return null;
                    }
                }).continueWith(new Continuation<Object, Object>() {
                    @Override
                    public Object then(Task<Object> task) throws Exception {

                        return self.getPhotosForModelsTask(task);
                    }
                });
    }

    protected void configureReviewsSection(LinkedList<Object> fetchedReviewPeople) {//Team
//        self.appendSectionTitleCell(SectionTitleCellModel(editKey: IEAEditKey.Section_Title, title: L10n.ReviewHighlights.string), forSectionIndex: self.getReviewsSectionIndex())
        self.setItemsForReviewsSection(fetchedReviewPeople);
    }

    protected abstract void setItemsForReviewsSection(LinkedList<Object> fetchedReviewPeople);//Team

    protected abstract void registerReviewTableCells();

    protected int getQueriedReviewsLimit() {
        return Review.MAX_FETCHED_REVIEWS_IN_DetailPage;
    }

    protected abstract int getReviewsSectionIndex();

    protected Review getReview(NSIndexPath indexPath) {
//        return self.fetchedReviews[(indexPath.section - self.getReviewsSectionIndex())];
        return null;
    }

//    func performSegueForWritingReview(){
//        self.presentViewController(UINavigationController(rootViewController: IEAWriteReviewViewController.createInstance(self.getPageModel())), animated: true, completion: nil)
//    }

//    override func segueForReviewDetailViewController(destination:IEAReviewDetailViewController){
//        destination.transferToReviewDetail(self.getPageModel(), review: self.selectedReview!)
//    }

}
