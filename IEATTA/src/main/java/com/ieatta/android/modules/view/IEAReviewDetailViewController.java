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
import com.ieatta.com.parse.models.Restaurant;
import com.ieatta.com.parse.models.Review;
import com.ieatta.com.parse.models.Team;
import com.ieatta.com.parse.models.enums.ReviewType;

import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 12/1/15.
 */

enum ReviewDetailSection {
    sectionReviewForModel,//= 0
}

class ReviewSectionInfo {
    public ParseModelAbstract model;
    public ReviewType type = ReviewType.Review_Unknow;

    public ReviewSectionInfo(ParseModelAbstract model, ReviewType type) {
        this.model = model;
        this.type = type;
    }
}

public class IEAReviewDetailViewController extends IEABaseTableViewController {
    private IEAReviewDetailViewController self = this;

    // Transferd Model from previous page.
    private ParseModelAbstract reviewForModel;
    private Review review;
    private List<ReviewSectionInfo> reivewSectionInfos = new LinkedList<>();

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

        self.reivewSectionInfos.add(new ReviewSectionInfo(self.review, ReviewType.Review_Unknow));

        self.reviewForModel.queryBelongToTask(self.reviewForModel)
                .onSuccess(new Continuation<ParseModelAbstract, Object>() {
                    @Override
                    public Object then(Task<ParseModelAbstract> task) throws Exception {
                        ParseModelAbstract backModel = task.getResult();
                        self.generateReviewSectionInfos(backModel);
                        self.setSectionsForReview();
                        return null;
                    }
                }, Task.UI_THREAD_EXECUTOR);
    }

    private void setSectionsForReview() {
        int sectionIndex = 0;
        int size = self.reivewSectionInfos.size();
        for (int i = 0; i < size; i++) {
            ReviewSectionInfo info = self.reivewSectionInfos.get(size - 1 - i);

            ParseModelAbstract model = info.model;
            sectionIndex += 1;

            switch (info.type) {
                case Review_Restaurant:
                    self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Restaurant_Information), sectionIndex);

                    self.setRegisterCellClass(IEANearRestaurantsCell.getType(), sectionIndex);
                    self.setSectionItems(CollectionUtils.createList(model), sectionIndex);
                    break;
                case Review_Recipe:
                    self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Recipe_Information), sectionIndex);

                    self.setRegisterCellClass(IEAOrderedRecipeCell.getType(), sectionIndex);
                    self.setSectionItems(CollectionUtils.createList(model), sectionIndex);
                    break;
                case Review_Event:
                    self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Event_Information), sectionIndex);

                    self.setRegisterCellClass(IEARestaurantEventsCell.getType(), sectionIndex);
                    self.setSectionItems(CollectionUtils.createList(model), sectionIndex);
                    break;
                default:
                    // Add Review Content cell.
                    self.setRegisterCellClass(IEAReviewDetailCell.getType(), sectionIndex);
                    // 2. Add section title cell.
                    self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Review_Highlights), sectionIndex);
                    /// 3. Set section items.
                    self.setSectionItems(CollectionUtils.createList(model), sectionIndex);
                    break;
            }

        }
    }

    private void generateReviewSectionInfos(ParseModelAbstract model) {
        ReviewType reviewType = (model.getReviewType());

        switch (reviewType) {
            case Review_Restaurant:
                self.reivewSectionInfos.add(new ReviewSectionInfo(model, reviewType));
                break;
            case Review_Recipe:
                self.reivewSectionInfos.add(new ReviewSectionInfo(model, reviewType));
                Recipe recipe = (Recipe) model;
                Team team = recipe.belongToModel;
                Event event = team.belongToModel;
                if (team != null && event != null) {
                    self.generateReviewSectionInfos(event);
                }
                break;
            case Review_Event:
                self.reivewSectionInfos.add(new ReviewSectionInfo(model, reviewType));
                Restaurant restaurant = ((Event) model).belongToModel;
                if (restaurant != null) {
                    self.generateReviewSectionInfos(restaurant);
                }
                break;
            default:
                break;
        }
    }

}
