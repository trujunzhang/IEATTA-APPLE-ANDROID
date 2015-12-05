package com.ieatta.android.modules.common.edit;

import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.view.IEAChoicePeopleViewController;
import com.ieatta.com.parse.models.Review;
import com.ieatta.com.parse.models.Team;

public class SectionSeeReviewsCellModel extends EditBaseCellModel {
    private SectionSeeReviewsCellModel self = this;
    Team user;
     Review writedReview;

    String timeAgoString = "";
    int ratingValue  = 0;
    String reviewContent = "";

    public SectionSeeReviewsCellModel(IEAEditKey editKey,Object user,Object review) {
        super(editKey);

        self.user = (Team)user;

        self.writedReview = (Review)review ;

        self.timeAgoString = writedReview.getTimeAgoString();
        self.ratingValue = writedReview.rate;
        self.reviewContent = writedReview.content;
    }

}
