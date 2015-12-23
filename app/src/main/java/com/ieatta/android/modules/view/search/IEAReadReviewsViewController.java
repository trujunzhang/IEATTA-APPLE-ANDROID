package com.ieatta.android.modules.view.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.ieatta.android.R;
import com.ieatta.android.cache.RatedModelReviewCount;
import com.ieatta.android.modules.IEAReviewSegueTableViewController;
import com.ieatta.android.modules.adapter.NSIndexPath;
import com.ieatta.android.modules.cells.IEAReadReviewsCell;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.enums.ReviewType;

import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;
import info.hoang8f.android.segmented.SegmentedGroup;

enum ReadReviewsSection {
    //         sectionHeader                 ,//= 0
    sectionRatedModelReviewCounts //= 1
}

/**
 * Created by djzhang on 12/1/15.
 */
public class IEAReadReviewsViewController extends IEAReviewSegueTableViewController implements RadioGroup.OnCheckedChangeListener {
    private IEAReadReviewsViewController self = this;

    // Selected model from tableview.
    private RatedModelReviewCount selectedModel;
    private int reviewType = ReviewType.getInt(ReviewType.Review_Restaurant);
    private List<ParseModelAbstract> fetchedList = new LinkedList<>();
    private String keyword;

    protected int getContentView() {
        return R.layout.table_controller_reviews_view;
    }

    private SegmentedGroup segmentedControl;
    private EditText searchTextView;
    private ImageView search_clear_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        self.segmentedControl = (SegmentedGroup) self.findViewById(R.id.segmentedControl);
        self.segmentedControl.setOnCheckedChangeListener(this);

        self.searchTextView = (EditText) self.findViewById(R.id.searchTextView);
        self.search_clear_Button = (ImageView) self.findViewById(R.id.search_clear);

        self.searchTextView.setHint(R.string.Search_Hint_Team);
        self.searchTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                self.keyword = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                self.queryRatedModels();
            }
        });

        self.search_clear_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                self.searchTextView.setText("");
            }
        });


        self.setRegisterCellClassWhenSelected(IEAReadReviewsCell.getType(), ReadReviewsSection.sectionRatedModelReviewCounts.ordinal());

        // TODO djzhang:test
        self.searchTextView.setText("ma");
    }

    private void queryRatedModels() {

        self.setSectionItems(new LinkedList<ParseModelAbstract>(), ReadReviewsSection.sectionRatedModelReviewCounts.ordinal());
        if (keyword.isEmpty() == true) {
            return;
        }

        ParseModelAbstract model = ReviewType.getParseModelInstance(self.reviewType);
        model.queryParseModels(self.keyword).onSuccessTask(new Continuation<List<ParseModelAbstract>, Task<Boolean>>() {
            @Override
            public Task<Boolean> then(Task<List<ParseModelAbstract>> task) throws Exception {
                Object object = task.getResult();
                self.fetchedList = task.getResult();
                return self.getPhotosForModelsTask(task);
            }
        }).onSuccess(new Continuation<Boolean, Object>() {
            @Override
            public Object then(Task<Boolean> task) throws Exception {
                List<RatedModelReviewCount> list = RatedModelReviewCount.convertToRatedModelReviewCounts(self.fetchedList);
                self.setSectionItems(list,  ReadReviewsSection.sectionRatedModelReviewCounts.ordinal());
                return null;
            }
        }).continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
                if (task.isFaulted()) {

                }
                return null;
            }
        });
    }

    @Override
    public void whenSelectedEvent(Object model, NSIndexPath indexPath) {

        RatedModelReviewCount reviewCount = (RatedModelReviewCount) model;

        self.showSelectedModel(reviewCount);
    }

    @Override
    public ParseModelAbstract getPageModel() {
        return selectedModel.convertToModelForReview();
    }

    private void showSelectedModel(RatedModelReviewCount model) {
        self.selectedModel = model;
        self.prepareForSeeReviewSegueIdentifier(/*self.getManagerNavigationViewController()*/);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.button31:
                self.reviewType = ReviewType.Review_Restaurant.ordinal();
                break;
            case R.id.button32:
                self.reviewType = ReviewType.Review_Event.ordinal();
                break;
            case R.id.button33:
                self.reviewType = ReviewType.Review_Recipe.ordinal();
                break;
        }
        self.queryRatedModels();
    }
}
