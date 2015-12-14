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
import com.ieatta.android.modules.cells.headerview.IEAReadReviewsHeader;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.enums.ReviewType;

import java.util.LinkedList;

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
    private int reviewType = ReviewType.Review_Restaurant.ordinal();

    protected int getContentView() {
        return R.layout.table_reviews_view_controller;
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
                self.queryRatedModels(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        self.search_clear_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                self.searchTextView.setText("");
            }
        });


        self.setRegisterCellClassWhenSelected(IEAReadReviewsCell.getType(), ReadReviewsSection.sectionRatedModelReviewCounts.ordinal());
    }

    private void queryRatedModels(String keyword) {
        self.setSectionItems(new LinkedList<ParseModelAbstract>(), ManagerPeopleSection.sectionTeam.ordinal());
        if (keyword.isEmpty() == true) {
            return;
        }
        ParseModelAbstract model = ReviewType.getParseModelInstance(self.reviewType);

    }

    @Override
    public void whenSelectedEvent(Object model, NSIndexPath indexPath) {
        super.whenSelectedEvent(model, indexPath);
    }

    @Override
    public ParseModelAbstract getPageModel() {
        return super.getPageModel();
    }

    private void showSelectedModel(RatedModelReviewCount model) {
//        self.selectedModel = model
//        self.prepareForSeeReviewSegueIdentifier(self.getManagerNavigationViewController())
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        self.reviewType = IEAReadReviewsHeader.convertToReviewType(checkedId).ordinal();
    }
}
