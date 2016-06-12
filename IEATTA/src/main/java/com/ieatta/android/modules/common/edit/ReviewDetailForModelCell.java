package com.ieatta.android.modules.common.edit;




import java.text.SimpleDateFormat;

/**
 * Created by djzhang on 12/1/15.
 */
public class ReviewDetailForModelCell {

    public ParseModelAbstract reviewForModel;
    public int ratingValue = Review.Rating_Default_Value;
    public String timeAgoString = "";

    public ReviewDetailForModelCell(ParseModelAbstract reviewForModel, Review review) {
        this.reviewForModel = reviewForModel;
        this.ratingValue = review.rate;

        this.timeAgoString = new SimpleDateFormat("MM/dd/yy").format(review.objectCreatedDate);
    }
}


