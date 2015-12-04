package com.ieatta.android.modules;

import com.ieatta.android.extensions.viewkit.NSIndexPath;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.com.parse.models.Review;

import java.util.List;

/**
 * Created by djzhang on 12/1/15.
 */
/// IEAReviewsInDetailTableViewController <|-- IEARestaurantDetailViewController
/// IEAReviewsInDetailTableViewController <|-- IEAEventDetailViewController
/// IEAReviewsInDetailTableViewController <|-- IEARecipeDetailViewController
public class IEAReviewsInDetailTableViewController extends IEABaseReviewsTableViewController{
    private IEAReviewsInDetailTableViewController self = this;


    protected void registerReviewTableCells(){
//        self.setRegisterCellClass(IEAReviewUserCell);
//        self.setRegisterCellClassWhenSelected(IEAReviewsCell.self);

//        self.setRegisterFooterClass(IEAMoreReviewsFooterCell);
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
     public void setItemsForReviewsSection(List<Object> fetchedReviewPeople){
//        int startIndex = self.getReviewsSectionIndex();
//
//        let array = Review.getReviewItems(self.fetchedReviews, people: fetchedReviewPeople)
//        let sectionCount = array.count / 2
//        for var i = 0; i < sectionCount; ++i{
//            setSectionItems([array[i*2+0],array[i*2+1]], forSectionIndex: startIndex + i )
//        }

        // 2. refresh section tableview footer view.
//        setFooterModelInSection(SectionMoreReviewsFooterCellModel(editKey: IEAEditKey.Section_Title, reviewForModel: self.getPageModel(),viewController: self), forSectionIndex: self.getMoreReviewSectionIndex());
    }

    // MARK: NSNotificationCenter notification handlers
//    func ReviewWasCreated(note:NSNotification){
//
//        self.getReviewsReleatdModelQueryTask().continueWithBlock { (task) -> AnyObject? in
//            dispatch_async(dispatch_get_main_queue(), { () -> Void in
//                    self.configureReviewsSection(task.result as! [Team])
//            })
//            return nil
//        }
//
//    }

    // MARK: Show all posted reviews for Restaurant,Recipe and Event.
//    func performSegueForSeeReviews(){
//        self.performSegueWithIdentifier(MainSegueIdentifier.detailSeeReviewSegueIdentifier.rawValue, sender: self)
//    }

    @Override
    protected int getReviewsSectionIndex() {
        return (self.getReviewsSectionIndex() + Review.MAX_FETCHED_REVIEWS_IN_DetailPage + 1);
    }

}
