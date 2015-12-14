package com.ieatta.android.modules.cells.enums;

import com.ieatta.com.parse.models.enums.ReviewType;

/**
 * Created by djzhang on 12/2/15.
 */
public enum IEAReadReviewsHeaderSegmentedType {
    Segment_Restaurant(0),//= 0
    Segment_Event(1),//= 1
    Segment_Recipe(2),//= 2
    Segment_Unknow(3);//= -1

    private int position = -1;

    IEAReadReviewsHeaderSegmentedType(int position) {
        this.position = position;
    }

    public static ReviewType convertToReviewType(int index) {
        int object = index;
        if (index == IEAReadReviewsHeaderSegmentedType.Segment_Restaurant.ordinal()) {
            return ReviewType.Review_Restaurant;
        } else if (index == IEAReadReviewsHeaderSegmentedType.Segment_Event.ordinal()) {
            return ReviewType.Review_Event;
        } else if (index == IEAReadReviewsHeaderSegmentedType.Segment_Recipe.ordinal()) {
            return ReviewType.Review_Recipe;
        }
        return ReviewType.Review_Unknow;
    }

}
