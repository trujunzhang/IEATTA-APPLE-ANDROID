package com.ieatta.android.modules.view.posts;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
public class IEAWriteReviewViewController extends AppCompatActivity {
    private IEAWriteReviewViewController self = this;

    private Context context;

    private View rateButton1;
    private View rateButton2;
    private View rateButton3;
    private View rateButton4;
    private View rateButton5;

    private View[] rateButtons = new View[5];

    private LinearLayout ratingImageView;

    private AvatarView avatarBackgroundView;
    private TextView nameLabel;
    private Button selectPeopleButton;

    private EditText reviewTextView;

    private int ratingValue = 5;
    public String reviewRef;
    private ReviewType type;
    private ParseModelAbstract reviewForModel;
    private Team people = Team.getAnonymousUser();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.table_controller_post_review);
        this.context = this;

//        this.type = IntentUtils.sharedInstance.reviewType;
//        this.reviewRef = IntentUtils.sharedInstance.getStringExtra(IntentUtils.key_reviewRef, this.getIntent());

        this.findAllViews();

//        ratingImageView.setBackgroundResource(UIImage.getRatingTitleImage(ratingValue));
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
        avatarBackgroundView = (AvatarView) this.findViewById(R.id.user_photo);
        nameLabel = (TextView) this.findViewById(R.id.user_name);
        selectPeopleButton = (Button) this.findViewById(R.id.select_people_button);

        this.selectPeopleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                IntentUtils.sharedInstance.showSelectPeopleActivity(context, false);
            }
        });

        reviewTextView = (EditText) this.findViewById(R.id.post_edittext);
    }

    private void setupPeopleInformation() {
//        this.avatarBackgroundView.loadPhoto(this.people, UIImage.DefaultPeopleCellIcon(), null);
//        this.nameLabel.setText(this.people.displayName);
    }

    private void setRatingImageViewBackground(int tag) {
        this.ratingValue = tag;
//        ratingImageView.setBackgroundResource(UIImage.getRatingTitleImage(tag));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_post_review, menu);
        return true;
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


}
