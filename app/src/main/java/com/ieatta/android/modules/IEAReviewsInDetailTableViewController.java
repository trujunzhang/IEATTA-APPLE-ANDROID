package com.ieatta.android.modules;

import com.ieatta.android.modules.cells.IEAReviewUserCell;
import com.ieatta.android.modules.cells.IEAReviewsCell;
import com.ieatta.android.modules.cells.headerfooterview.IEAMoreReviewsFooterCell;
import com.ieatta.android.modules.common.MainSegueIdentifier;
import com.ieatta.android.modules.tools.CollectionUtils;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Review;

import java.util.List;

/**
 * Created by djzhang on 12/1/15.
 */
/// IEAReviewsInDetailTableViewController <|-- IEARestaurantDetailViewController
/// IEAReviewsInDetailTableViewController <|-- IEAEventDetailViewController
/// IEAReviewsInDetailTableViewController <|-- IEARecipeDetailViewController
public class IEAReviewsInDetailTableViewController extends IEABaseReviewsTableViewController {
    private IEAReviewsInDetailTableViewController self = this;


    protected void registerReviewTableCells() {
        int reviewSectionIndex = self.getReviewsSectionIndex();
        for (int i = 0; i < Review.MAX_FETCHED_REVIEWS_IN_DetailPage; i++) {
            self.setRegisterCellClass(IEAReviewUserCell.getType(), reviewSectionIndex + i);
            self.setRegisterCellClassWhenSelected(IEAReviewsCell.getType(), reviewSectionIndex + i, 1);
        }

//        self.setRegisterFooterClass(IEAMoreReviewsFooterCell.getType());
    }


    // MARK: Override IEADTTableViewManagerViewController methods
//    override func whenSelectedEvent(model:AnyObject,NSIndexPath indexPath){
//        if(indexPath.section >= self.getReviewsSectionIndex()){
//            let review = model  as! Review
//
//            self.selectedReview = review
//            self.performSegueWithIdentifier(MainSegueIdentifier.detailReviewSegueIdentifier.rawValue, sender: self)
//        }
//    }

    @Override
    public void setItemsForReviewsSection(List<ParseModelAbstract> fetchedReviewPeople) {
        int startIndex = self.getReviewsSectionIndex();

        List<Object> array = Review.getReviewItems(self.fetchedReviews, fetchedReviewPeople);
        int sectionCount = array.size() / 2;
        for (int i = 0; i < sectionCount; i++) {
            Object[] item = {array.get(i * 2 + 0), array.get(i * 2 + 1)};
            setSectionItems(CollectionUtils.createList(item), startIndex + i);
        }

        // 2. refresh section tableview footer view.
//        setFooterModelInSection(new SectionMoreReviewsFooterCellModel(IEAEditKey.Section_Title, self.getPageModel(), self), self.getMoreReviewSectionIndex());
    }

    // MARK: NSNotificationCenter notification handlers
//    func ReviewWasCreated(note:NSNotification){
//
//        self.getReviewsRelatedModelQueryTask().continueWithBlock { (task) -> AnyObject? in
//            dispatch_async(dispatch_get_main_queue(), { () -> Void in
//                    self.configureReviewsSection(task.result as! [Team])
//            })
//            return nil
//        }
//
//    }

    // MARK: Show all posted reviews for Restaurant,Recipe and Event.
    public void performSegueForSeeReviews() {
        self.performSegueWithIdentifier(MainSegueIdentifier.detailSeeReviewSegueIdentifier,  self);
    }

    private int getMoreReviewSectionIndex() {
        return (self.getReviewsSectionIndex() + Review.MAX_FETCHED_REVIEWS_IN_DetailPage + 1);
    }

}
