package com.ieatta.android.extensions.viewkit;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.ieatta.android.cache.IEACache;



import bolts.Continuation;
import bolts.Task;

public class RatingImageView extends ImageView {
    private RatingImageView self = this;

    public RatingImageView(Context context) {
        super(context);
    }

    public RatingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RatingImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RatingImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

//
//    public void queryRatingInReviewsByModel(ParseModelAbstract model) {
//        self.queryRatingInReviewsByReview(new Review(model));
//    }
//
//    public void queryRatingInReviewsByReview(final Review review) {
//        Integer integer = IEACache.sharedInstance.avarageRating(review);
//        if (integer != null) {
//            self.setImageLevel(integer.intValue());
//        } else {
//            review.queryRatingInReviews().onSuccess(new Continuation<Integer, Object>() {
//                @Override
//                public Object then(Task<Integer> task) throws Exception {
//
//                    final int count = task.getResult();
//                    IEACache.sharedInstance.setAvarageRating(count, review);
//                    self.setImageLevel(count);
//
//                    return null;
//                }
//            }, Task.UI_THREAD_EXECUTOR);
//        }
//    }

}
