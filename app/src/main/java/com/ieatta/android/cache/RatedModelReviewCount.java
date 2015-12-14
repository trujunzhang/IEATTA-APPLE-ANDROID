package com.ieatta.android.cache;

import com.ieatta.android.R;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Review;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.ieatta.com.parse.models.enums.ReviewType;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by djzhang on 12/1/15.
 */
public class RatedModelReviewCount extends Review{
private RatedModelReviewCount self = this;

    String reviewTitle = "";
    PQueryModelType modelType = PQueryModelType.unkown;

    public RatedModelReviewCount(ParseModelAbstract model) {
        super();

        self.reviewTitle = model.displayName;
        self.modelType = model.getModelType();

        self.reviewRef = ParseModelAbstract.getPoint(model);
        self.reviewType = model.getReviewType();

        IEACache.sharedInstance.copyPhotoPoint(self, model);
    }

    public ParseModelAbstract convertToModelForReview() {
        ParseModelAbstract pageModel = ParseModelAbstract.getInstanceFromType(self.modelType,  self.reviewRef);

        pageModel.displayName = self.reviewTitle;

        return pageModel;
    }

    public int getPlaceHolderImage() {
        switch(self.reviewType){
            case Review_Restaurant:
                return R.drawable.blank_biz;
            case Review_Recipe:
                return R.drawable.blank_biz;
            case Review_Event:
                return R.drawable.blank_biz;
            default:
                return -1;
        }
    }

     public static List<RatedModelReviewCount> convertToRatedModelReviewCounts(List<ParseModelAbstract> source) {
        List<RatedModelReviewCount> list = new LinkedList<>();
         for(ParseModelAbstract model : source){
             list.add(new RatedModelReviewCount(model));
         }
        return list;
    }
}
