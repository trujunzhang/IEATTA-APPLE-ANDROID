package com.ieatta.android.modules.view;

import android.os.Bundle;

import com.ieatta.android.R;
import com.ieatta.android.cache.IntentCache;
import com.ieatta.android.modules.IEABaseTableViewController;
import com.ieatta.android.modules.cells.IEANearRestaurantsCell;
import com.ieatta.android.modules.cells.IEAOrderedRecipeCell;
import com.ieatta.android.modules.cells.IEARestaurantEventsCell;
import com.ieatta.android.modules.cells.IEAReviewDetailCell;
import com.ieatta.android.modules.cells.IEAReviewDetailForModelCell;
import com.ieatta.android.modules.common.edit.ReviewDetailForModelCell;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.tools.CollectionUtils;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Event;
import com.ieatta.com.parse.models.Recipe;
import com.ieatta.com.parse.models.Review;
import com.ieatta.com.parse.models.enums.ReviewType;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 12/1/15.
 */

enum ReviewDetailSection {
    sectionReviewForModel,//= 0
}

public class IEAReviewDetailViewController extends IEABaseTableViewController {
    private IEAReviewDetailViewController self = this;

    // Transferd Model from previous page.
    private ParseModelAbstract reviewForModel;
    private Review review;
    private int step = 0;


    public void transferToReviewDetail(ParseModelAbstract reviewForModel, Review review) {
        self.reviewForModel = reviewForModel;
        self.review = review;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        self.transferToReviewDetail(self.getTransferedModel(), (Review) self.getTransferedModel(IntentCache.selectedReview));

        self.setRegisterCellClass(IEAReviewDetailForModelCell.getType(), ReviewDetailSection.sectionReviewForModel.ordinal());

        setSectionItems(CollectionUtils.createList(new ReviewDetailForModelCell(self.reviewForModel, self.review)), ReviewDetailSection.sectionReviewForModel.ordinal());

        self.reviewForModel.queryBelongToTask(self.reviewForModel).onSuccess(new Continuation<Boolean, Object>() {
            @Override
            public Object then(Task<Boolean> task) throws Exception {
                self.showReviewForModelCells(self.reviewForModel);
                return null;
            }
        }).continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {

                return null;
            }
        });
    }

    private void showReviewForModelCells(ParseModelAbstract model) {
        ReviewType reviewType = (model.getReviewType());

        switch (reviewType) {
            case Review_Restaurant:
//                self.getTableViewHeightInfo().setHeightForRowAtIndexPath(1,value: CellsHeight.NearRestaurant_Restaurants.rawValue)
                self.step = 1;
                self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Restaurant_Information), 1);

                self.setRegisterCellClass(IEANearRestaurantsCell.getType(), 1);
                self.setSectionItems(CollectionUtils.createList(model), 1);

                self.showReviewForModelCells(self.review);
                break;
            case Review_Recipe:
//                self.getTableViewHeightInfo().setHeightForRowAtIndexPath(3,value: CellsHeight.OrderedRecipes_Recipes.rawValue)

                self.step = 3;
                self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Recipe_Information), 3);

                self.setRegisterCellClass(IEAOrderedRecipeCell.getType(), 3);
                self.setSectionItems(CollectionUtils.createList(model), 3);

                self.showReviewForModelCells((((Recipe) model).belongToModel).belongToModel);
                break;
            case Review_Event:
//                self.getTableViewHeightInfo().setHeightForRowAtIndexPath(2,value: CellsHeight.RestaurantDetail_Events.rawValue)
                self.step = 2;
                self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Event_Information), 2);
//
                self.setRegisterCellClass(IEARestaurantEventsCell.getType(), 2);
                self.setSectionItems(CollectionUtils.createList(model), 2);
//
                self.showReviewForModelCells(((Event) model).belongToModel);
                break;
            default:
                // Add Review Content cell.
                int rowCount = self.step;
                self.setRegisterCellClass(IEAReviewDetailCell.getType(), rowCount);
                /// 1. Set current review index.
//                self.getTableViewHeightInfo().setReviewSectionIndex(rowCount);
                // 2. Add section title cell.
                self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Review_Highlights), rowCount);
                /// 3. Set section items.
                self.setSectionItems(CollectionUtils.createList(model), rowCount);
                break;
        }

    }
}
