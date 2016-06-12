package com.ieatta.android.modules.common.edit;

import com.ieatta.android.modules.common.edit.enums.IEAEditKey;

import com.ieatta.com.parse.models.Team;

public class SectionSeeReviewsCellModel extends EditBaseCellModel {
    private SectionSeeReviewsCellModel self = this;

    public Team user = null;
    public Review writedReview = null;

    public String timeAgoString = "";
    public int ratingValue = 0;
    public String reviewContent = "";

    public SectionSeeReviewsCellModel(IEAEditKey editKey, Object user, Object review) {
        super(editKey);

        self.user = (Team) user;

        self.writedReview = (Review) review;

        self.timeAgoString = writedReview.getTimeAgoString();
        self.ratingValue = writedReview.rate;
        self.reviewContent = writedReview.content;
    }

}
