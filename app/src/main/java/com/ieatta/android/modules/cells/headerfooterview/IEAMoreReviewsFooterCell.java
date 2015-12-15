package com.ieatta.android.modules.cells.headerfooterview;

import android.view.View;
import android.widget.Button;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.badoo.mobile.util.WeakHandler;
import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.adapter.enums.ViewHolderType;
import com.ieatta.android.modules.common.edit.SectionMoreReviewsFooterCellModel;
import com.ieatta.com.parse.models.Review;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 12/2/15.
 */
public class IEAMoreReviewsFooterCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEAMoreReviewsFooterCell.class, R.layout.businesspage_section_footer);
    }

    @Override
    protected boolean shouldClickItem() {
        return false;
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.footer;
    }

    private IEAMoreReviewsFooterCell self = this;
    private SectionMoreReviewsFooterCellModel model;

    private Button footerLargeButton;
    private WeakHandler mHandler = new WeakHandler(); // We still need at least one hard reference to WeakHandler

    public IEAMoreReviewsFooterCell(View itemView) {
        super(itemView);

        self.footerLargeButton = (Button) itemView.findViewById(R.id.footer_large_button);
        self.footerLargeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                self.model.viewController.performSegueForSeeReviews();
            }
        });
    }

    @Override
    public void render(Object value) {
        self.model = (SectionMoreReviewsFooterCellModel) value;

        // update UI
        new Review(model.reviewForModel).queryReviewsCount().onSuccess(new Continuation<Integer, Object>() {
            @Override
            public Object then(Task<Integer> task) throws Exception {
                final int count = task.getResult();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        self.configureButton(count);
                    }
                }, 1);

                return null;
            }
        }).continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
                if (task.isFaulted() == true) {
                }
                return null;
            }
        });
    }

    private void configureButton(int reviewsCount) {
        String buttonTitle = EnvironmentUtils.sharedInstance.getGlobalContext().getResources().getString(R.string.No_More_Reviews);
        self.footerLargeButton.setEnabled(true);
        if (reviewsCount > 0) {
            String moreReviews = EnvironmentUtils.sharedInstance.getGlobalContext().getResources().getString(R.string.See_More_Reviews);
            buttonTitle = reviewsCount +" "+ moreReviews;
        } else {
            self.footerLargeButton.setEnabled(false);
        }

        self.footerLargeButton.setText(buttonTitle);
    }

}
