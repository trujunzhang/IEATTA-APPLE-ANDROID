package com.ieatta.android.cache;

import com.ieatta.android.R;

import java.util.LinkedList;
import java.util.List;

public class RatedModelReviewCount extends Review {
    private RatedModelReviewCount self = this;

    public String reviewTitle = "";
    public PQueryModelType modelType = PQueryModelType.unkown;

    public RatedModelReviewCount(ParseModelAbstract model) {
        super();

        self.reviewTitle = model.displayName;
        self.modelType = model.getModelType();

        self.reviewRef = ParseModelAbstract.getPoint(model);
        self.reviewType = model.getReviewType();

        IEACache.sharedInstance.copyPhotoPoint(self, model);
    }

    public ParseModelAbstract convertToModelForReview() {
        ParseModelAbstract pageModel = ParseModelAbstract.getInstanceFromType(self.modelType, self.reviewRef);

        pageModel.displayName = self.reviewTitle;

        return pageModel;
    }

    public int getPlaceHolderImage() {
        switch (self.reviewType) {
            case Review_Restaurant:
                return R.drawable.blank_biz;
            case Review_Recipe:
                return R.drawable.placeholder_photo;
            case Review_Event:
                return R.drawable.nav_events;
            default:
                return -1;
        }
    }

    public static List<RatedModelReviewCount> convertToRatedModelReviewCounts(List<ParseModelAbstract> source) {
        List<RatedModelReviewCount> list = new LinkedList<>();
        for (ParseModelAbstract model : source) {
            list.add(new RatedModelReviewCount(model));
        }
        return list;
    }
}
