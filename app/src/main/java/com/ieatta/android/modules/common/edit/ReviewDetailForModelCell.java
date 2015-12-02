package com.ieatta.android.modules.common.edit;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Review;

/**
 * Created by djzhang on 12/1/15.
 */
public class ReviewDetailForModelCell {

    ParseModelAbstract reviewForModel;
    int ratingValue = Review.Rating_Default_Value;
    String timeAgoString = "";

    public ReviewDetailForModelCell(ParseModelAbstract reviewForModel, Review review) {
        this.reviewForModel = reviewForModel;
        this.ratingValue = review.rate;
//        this.timeAgoString = review.objectCreatedDate.formatted;
    }
}


