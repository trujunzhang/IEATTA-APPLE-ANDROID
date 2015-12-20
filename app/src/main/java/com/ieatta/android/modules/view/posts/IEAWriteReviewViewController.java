package com.ieatta.android.modules.view.posts;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.cache.IEACache;
import com.ieatta.android.extensions.viewkit.AvatarView;
import com.ieatta.android.modules.IEAAppSegureTableViewController;
import com.ieatta.android.modules.common.MainSegueIdentifier;
import com.ieatta.android.notification.NSNotification;
import com.ieatta.android.notification.NSNotificationCenter;
import com.ieatta.android.notification.NotifyType;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Review;
import com.ieatta.com.parse.models.Team;
import com.ieatta.com.parse.models.enums.ReviewType;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 12/1/15.
 */
public class IEAWriteReviewViewController extends IEAAppSegureTableViewController {
    private IEAWriteReviewViewController self = this;

    private View rateButton1;
    private View rateButton2;
    private View rateButton3;
    private View rateButton4;
    private View rateButton5;

    private View[] rateButtons = new View[5];

    private LinearLayout ratingImageView;

    private AvatarView avatarView;
    private TextView nameLabel;
    private Button selectPeopleButton;

    private EditText reviewTextView;

    private int ratingValue = 5;
    private ReviewType type;
    private ParseModelAbstract reviewForModel;
    private Team people = Team.getAnonymousUser();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        self.reviewForModel = self.getTransferedModel();
        self.type = self.reviewForModel.getReviewType();

        self.findAllViews();

        self.ratingImageView.setBackgroundResource(self.getRatingTitleImage(ratingValue));
    }

    @Override
    protected int getContentView() {
        return R.layout.table_controller_post_review;
    }
    @Override
    protected boolean hasRecycleView(){
        return false;
    }

    private void findAllViews() {
        rateButton1 = this.findViewById(R.id.rating_button1);
        rateButton2 = this.findViewById(R.id.rating_button2);
        rateButton3 = this.findViewById(R.id.rating_button3);
        rateButton4 = this.findViewById(R.id.rating_button4);
        rateButton5 = this.findViewById(R.id.rating_button5);

        rateButtons = new View[]{rateButton1, rateButton2, rateButton3, rateButton4, rateButton5};

        int step = 1;
        for (View button : rateButtons) {
            button.setTag(step++);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int tag = (int) v.getTag();
                    setRatingImageViewBackground(tag);
                }
            });
        }


        ratingImageView = (LinearLayout) this.findViewById(R.id.rating_background);
        avatarView = (AvatarView) this.findViewById(R.id.user_photo);
        nameLabel = (TextView) this.findViewById(R.id.user_name);
        selectPeopleButton = (Button) this.findViewById(R.id.select_people_button);

        this.selectPeopleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                self.performSegueWithIdentifier(MainSegueIdentifier.choicePeopleSegueIdentifier, self);
            }
        });

        reviewTextView = (EditText) this.findViewById(R.id.post_edittext);
    }

    private void setupPeopleInformation() {

    }

    private void setRatingImageViewBackground(int tag) {
        this.ratingValue = tag;
        ratingImageView.setBackgroundResource(self.getRatingTitleImage(tag));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_post_review) {
//
//            this.saveModel();
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    // MARK: Navigation item actions
    private void postAction() {

        Review savedReview = new Review(self.people, self.reviewForModel, self.reviewTextView.getText().toString(), self.ratingValue);
        savedReview.pinInBackgroundWithNewRecord().onSuccess(new Continuation<Void, Object>() {
            @Override
            public Object then(Task<Void> task) throws Exception {
                self.postSaveModelSucess();
                return null;
            }
        }).continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
                if (task.isFaulted()) {
//                    AppAlertView.showError(L10n.SavedFailure.string)
                }
                return null;
            }
        });

    }

    protected void postSaveModelSucess() {
        // 1. Clear up avarage rating cache.
        IEACache.sharedInstance.clearAvarageRatingCache();

        // 2. Nofity "ReviewPost".
        NSNotificationCenter.defaultCenter().postNotificationName(NotifyType.PAReviewPostNotification, null);
    }

    private static int[] ratingTitleImages = {
            -1,
            R.drawable.review_stars_1_inline,
            R.drawable.review_stars_2_inline,
            R.drawable.review_stars_3_inline,
            R.drawable.review_stars_4_inline,
            R.drawable.review_stars_5_inline,
    };

    private int getRatingTitleImage(int rate) { // 141,25
        return ratingTitleImages[rate];
    }

    @Override
    protected void didSelectPeople(NSNotification note) {
        this.people = (Team) note.anObject;

        self.avatarView.loadNewPhotoByModel(people, R.drawable.blank_user_small);
        this.nameLabel.setText(this.people.displayName);
    }
}
