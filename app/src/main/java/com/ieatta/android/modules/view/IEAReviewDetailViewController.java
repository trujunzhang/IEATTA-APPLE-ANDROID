package com.ieatta.android.modules.view;

import android.os.Bundle;

import com.ieatta.android.modules.IEABaseTableViewController;
import com.ieatta.android.modules.common.edit.IEAEditKey;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Review;
import com.ieatta.com.parse.models.enums.ReviewType;

import bolts.Continuation;
import bolts.Task;

import static com.ieatta.com.parse.models.enums.ReviewType.Review_Event;
import static com.ieatta.com.parse.models.enums.ReviewType.Review_Restaurant;

/**
 * Created by djzhang on 12/1/15.
 */

enum ReviewDetailSection {
         sectionReviewForModel   ,//= 0
        }
public class IEAReviewDetailViewController extends IEABaseTableViewController {
    private IEAReviewDetailViewController self =this;

    // Transferd Model from previous page.
    private ParseModelAbstract reviewForModel;
    private Review review;

    public void transferToReviewDetail(ParseModelAbstract reviewForModel,Review review){
        self.reviewForModel = reviewForModel;
        self.review = review;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Do any additional setup after loading the view.
//        assert(self.reviewForModel != nil, "Must setup reviewForModel's instance.")
//        assert(self.review != nil, "Must setup selectedReview's instance.")

//        self.setRegisterCellClass(IEAReviewDetailForModelCell)
//        self.setRegisterCellClass(IEAReviewDetailCell)

//        self.appendSectionTitleCell(SectionTitleCellModel(editKey: IEAEditKey.Section_Title, title: ""), forSectionIndex: ReviewDetailSection.sectionReviewForModel.rawValue)

//        setSectionItems([ReviewDetailForModelCell(reviewForModel: self.reviewForModel!, review: self.review!)], forSectionIndex: ReviewDetailSection.sectionReviewForModel.rawValue)


        self.reviewForModel.queryBelongToTask(self.reviewForModel).continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
                if(task.getError()!= null){

                }else {
                    self.showReviewForModelCells(self.reviewForModel);
                }

                return null;
            }
        });

    }

    private void showReviewForModelCells(ParseModelAbstract model) {
        ReviewType reviewType = (model.getReviewType());

        switch(reviewType){
            case Review_Restaurant:
//                self.getTableViewHeightInfo().setHeightForRowAtIndexPath(1,value: CellsHeight.NearRestaurant_Restaurants.rawValue)

//                self.appendSectionTitleCell(SectionTitleCellModel(editKey: IEAEditKey.Section_Title, title: L10n.RestaurantInformation.string), forSectionIndex: 1)

//                self.setRegisterCellClass(IEANearRestaurantsCell)
//                self.setSectionItems([model], forSectionIndex: 1)

                self.showReviewForModelCells(self.review);
                break;
            case Review_Recipe:
//                self.getTableViewHeightInfo().setHeightForRowAtIndexPath(3,value: CellsHeight.OrderedRecipes_Recipes.rawValue)

//                self.appendSectionTitleCell(SectionTitleCellModel(editKey: IEAEditKey.Section_Title, title: L10n.RecipeInformation.string), forSectionIndex: 3)

//                self.setRegisterCellClass(IEAOrderedRecipeCell)
//                self.setSectionItems([model], forSectionIndex: 3)

//                self.showReviewForModelCells(((model as! Recipe).belongToModel! ).belongToModel!)
                break;
            case Review_Event:
//                self.getTableViewHeightInfo().setHeightForRowAtIndexPath(2,value: CellsHeight.RestaurantDetail_Events.rawValue)
//
//                self.appendSectionTitleCell(SectionTitleCellModel(editKey: IEAEditKey.Section_Title, title: L10n.EventInformation.string), forSectionIndex: 2)
//
//                self.setRegisterCellClass(IEARestaurantEventsCell)
//                self.setSectionItems([model], forSectionIndex: 2)
//
//                self.showReviewForModelCells((model as! Event).belongToModel!)
                break;
            default:
                // Add Review Content cell.
                int rowCount = self.getTableViewHeightInfo().getRowCount();
                /// 1. Set current review index.
//                self.getTableViewHeightInfo().setReviewSectionIndex(rowCount);
                /// 2. Add section title cell.
//                self.appendSectionTitleCell(SectionTitleCellModel(editKey: IEAEditKey.Section_Title, title: L10n.ReviewHighlights.string), forSectionIndex:rowCount)
                /// 3. Set section items.
//                self.setSectionItems([model],  rowCount);
                break;
        }

    }
}
