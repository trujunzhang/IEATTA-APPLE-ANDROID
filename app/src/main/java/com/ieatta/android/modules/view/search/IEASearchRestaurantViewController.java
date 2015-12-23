package com.ieatta.android.modules.view.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ieatta.android.R;
import com.ieatta.android.modules.IEASplitDetailViewController;
import com.ieatta.android.modules.adapter.NSIndexPath;
import com.ieatta.android.modules.cells.IEANearRestaurantsCell;
import com.ieatta.android.modules.common.MainSegueIdentifier;
import com.ieatta.android.modules.tools.RestaurantSortUtils;
import com.ieatta.android.modules.view.IEARestaurantDetailViewController;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Restaurant;

import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

enum SearchRestaurantSection {
    sectionRestaurants //;= 0
}

/**
 * Created by djzhang on 12/1/15.
 */
public class IEASearchRestaurantViewController extends IEASplitDetailViewController {
    private IEASearchRestaurantViewController self = this;
    private EditText searchTextView;
    private ImageView search_clear_Button;
    private List<ParseModelAbstract/*Restaurant*/> fetchedRestaurants;
    private Restaurant selectedModel;

    private String keyword = "";

    protected int getContentView() {
        return R.layout.table_controller_serch_view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        self.searchTextView = (EditText) self.findViewById(R.id.searchTextView);
        self.search_clear_Button = (ImageView) self.findViewById(R.id.search_clear);

        self.searchTextView.setHint(R.string.Search_Hint_Restaurant);
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
                self.queryNearRestaurant(self.keyword);
            }
        });

        self.search_clear_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                self.searchTextView.setText("");
            }
        });

        self.setRegisterCellClassWhenSelected(IEANearRestaurantsCell.getType(), SearchRestaurantSection.sectionRestaurants.ordinal());
    }

    private void queryNearRestaurant(String keyword) {
        self.setSectionItems(new LinkedList<ParseModelAbstract>(), SearchRestaurantSection.sectionRestaurants.ordinal());
        if (keyword.isEmpty() == true) {
            return;
        }
        new Restaurant().queryParseModels(keyword).onSuccessTask(new Continuation<List<ParseModelAbstract>, Task<Boolean>>() {
            @Override
            public Task<Boolean> then(Task<List<ParseModelAbstract>> task) throws Exception {
                Object object = task;
                self.fetchedRestaurants = task.getResult();
                self.fetchedRestaurants = RestaurantSortUtils.sort(self.fetchedRestaurants);

                // Next, fetch related photos
                return self.getPhotosForModelsTask(task);
            }
        }).onSuccess(new Continuation<Boolean, Object>() {
            @Override
            public Object then(Task<Boolean> task) throws Exception {
                self.setSectionItems(self.fetchedRestaurants, SearchRestaurantSection.sectionRestaurants.ordinal());
                return null;
            }
        }).continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
                return null;
            }
        });
    }

    @Override
    public void whenSelectedEvent(Object model, NSIndexPath indexPath) {
        self.showSelectedModel((Restaurant) model);
    }

    private void showSelectedModel(Restaurant model) {
        self.selectedModel = (Restaurant) model;
        self.performSegueWithIdentifier(MainSegueIdentifier.detailRestaurantSegueIdentifier, self);
    }

    @Override
    protected void segueForRestaurantDetailViewController(IEARestaurantDetailViewController destination, Intent sender) {
        /// Show detailed restaurant
        self.setTransferedModel(sender, self.selectedModel);
    }

}
