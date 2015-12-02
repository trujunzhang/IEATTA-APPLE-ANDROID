package com.ieatta.android.modules.view;

import android.os.Bundle;

import com.ieatta.android.modules.IEABaseReviewsTableViewController;
import com.ieatta.android.modules.common.edit.IEAEditKey;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Review;

import java.util.Collection;
import java.util.LinkedList;

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
//        assert(self.reviewForModel != nil, "Must setup reviewForModel's instance.")

        self.getReviewsReleatdModelQueryTask().continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
                if(task.getError()!=null){

                }else {
                    self.configureReviewsSection(new LinkedList<Object>((Collection<?>) task.getResult()));
                }
                return null;
            }
        });
    }

    // MARK: Override IEAReviewsTableViewController methods
    @Override
    public void registerReviewTableCells() {
//        self.setRegisterCellClassWhenSelected(IEASeeReviewsCell.self);
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
    public void setItemsForReviewsSection(LinkedList<Object> fetchedReviewPeople) {
//         Review.getReviewItems(self.fetchedReviews, fetchedReviewPeople);

//        var items:[SectionSeeReviewsCellModel] = [SectionSeeReviewsCellModel]()
//        for var i = 0; i <  array.count / 2; ++i{
//            items.append(SectionSeeReviewsCellModel(editKey: IEAEditKey.Section_Title, user: array[i*2+0], review: array[i*2+1]))
//        }

//        setSectionItems(items,  SeeReviewsInDetailSection.sectionReviews.ordinal()  );
    }

}
