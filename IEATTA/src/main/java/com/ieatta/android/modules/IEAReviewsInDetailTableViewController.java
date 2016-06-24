package com.ieatta.android.modules;

import com.ieatta.android.modules.adapter.NSIndexPath;
import com.ieatta.android.modules.cells.IEAReviewUserCell;
import com.ieatta.android.modules.cells.IEAReviewsCell;
import com.ieatta.android.modules.cells.headerfooterview.IEAMoreReviewsFooterCell;
import com.ieatta.android.modules.common.MainSegueIdentifier;
import com.ieatta.android.modules.common.edit.SectionMoreReviewsFooterCellModel;
import com.ieatta.provide.IEAEditKey;
import com.ieatta.android.modules.tools.CollectionUtils;
import com.ieatta.android.notification.NSNotification;


import java.util.List;

import bolts.Continuation;
import bolts.Task;

/// IEAReviewsInDetailTableViewController <|-- IEARestaurantDetailActivity
/// IEAReviewsInDetailTableViewController <|-- IEAEventDetailViewController
/// IEAReviewsInDetailTableViewController <|-- IEARecipeDetailViewController
public class IEAReviewsInDetailTableViewController extends IEABaseReviewsTableViewController {
    private IEAReviewsInDetailTableViewController self = this;


    protected void registerReviewTableCells() {
//        int reviewSectionIndex = self.getReviewsSectionIndex();
//        for (int i = 0; i < Review.MAX_FETCHED_REVIEWS_IN_DetailPage; i++) {
//            self.setRegisterCellClass(IEAReviewUserCell.getType(), reviewSectionIndex + i);
//            self.setRegisterCellClassWhenSelected(IEAReviewsCell.getType(), reviewSectionIndex + i, 1);
//        }

        self.setRegisterFooterClass(IEAMoreReviewsFooterCell.getType());
    }


    // MARK: Override IEADTTableViewManagerViewController methods

    @Override
    public void whenSelectedEvent(Object model, NSIndexPath indexPath) {
//        if (indexPath.section >= self.getReviewsSectionIndex()) {
//            Review review = (Review) model;
//
//            self.selectedReview = review;
//            self.performSegueWithIdentifier(MainSegueIdentifier.detailReviewSegueIdentifier, self);
//        }
    }

//    @Override
    public void setItemsForReviewsSection(List fetchedReviewPeople) {
//        int startIndex = self.getReviewsSectionIndex();

//        List<Object> array = Review.getReviewItems(self.fetchedReviews, fetchedReviewPeople);
//        int sectionCount = array.size() / 2;
//        for (int i = 0; i < sectionCount; i++) {
//            Object[] item = {array.get(i * 2 + 0), array.get(i * 2 + 1)};
//            setSectionItems(CollectionUtils.createList(item), startIndex + i);
//        }

        // 2. refresh section tableview footer view.
//        setFooterModelInSection(new SectionMoreReviewsFooterCellModel(IEAEditKey.Section_Title, self.getPageModel(), self), self.getMoreReviewSectionIndex(), IEAMoreReviewsFooterCell.getType());
    }

    // MARK: NSNotificationCenter notification handlers
    @Override
    protected void ReviewWasCreated(NSNotification note) {
//        self.getReviewsRelatedModelQueryTask().onSuccess(new Continuation<Boolean, Object>() {
//            @Override
//            public Object then(Task<Boolean> task) throws Exception {
//                self.configureReviewsSection();
//                return null;
//            }
//        });
    }

    // MARK: Show all posted reviews for Restaurant,Recipe and Event.
    public void performSegueForSeeReviews() {
        self.performSegueWithIdentifier(MainSegueIdentifier.detailSeeReviewSegueIdentifier, self);
    }

    private int getMoreReviewSectionIndex() {
        return 0;
//        return (self.getReviewsSectionIndex() + Review.MAX_FETCHED_REVIEWS_IN_DetailPage + 1);
    }


}
