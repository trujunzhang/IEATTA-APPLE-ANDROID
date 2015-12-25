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
import com.ieatta.android.modules.cells.IEAPeopleInfoCell;
import com.ieatta.android.modules.common.MainSegueIdentifier;
import com.ieatta.android.modules.view.edit.IEAEditPeopleViewController;
import com.ieatta.android.notification.NSNotification;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Team;

import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

enum ManagerPeopleSection {
    sectionTeam;//= 0
}

/**
 * Created by djzhang on 12/1/15.
 */
public class IEAManagerPeopleViewController extends IEASplitDetailViewController {
    private IEAManagerPeopleViewController self = this;

    private EditText searchTextView;
    private ImageView search_clear_Button;

    private List<ParseModelAbstract/*Restaurant*/> fetchedTeam;
    private Team selectedModel;

    private String keyword = "";

    protected int getContentView() {
        return R.layout.table_controller_serch_view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        self.setRightBarButtonItem(R.string.add, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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
                self.queryTeams(self.keyword);
            }
        });

        self.search_clear_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                self.searchTextView.setText("");
            }
        });

        self.setRegisterCellClassWhenSelected(IEAPeopleInfoCell.getType(), ManagerPeopleSection.sectionTeam.ordinal());
    }

    private void queryTeams(String keyword) {
        self.setSectionItems(new LinkedList<ParseModelAbstract>(), ManagerPeopleSection.sectionTeam.ordinal());
        if (keyword == null || keyword.isEmpty() == true) {
            return;
        }
        new Team().queryParseModels(keyword).onSuccessTask(new Continuation<List<ParseModelAbstract>, Task<Boolean>>() {
            @Override
            public Task<Boolean> then(Task<List<ParseModelAbstract>> task) throws Exception {
                Object object = task;
                self.fetchedTeam = task.getResult();

                // Next, fetch related photos
                return self.getPhotosForModelsTask(task);
            }
        }).onSuccess(new Continuation<Boolean, Object>() {
            @Override
            public Object then(Task<Boolean> task) throws Exception {
                self.setSectionItems(self.fetchedTeam, ManagerPeopleSection.sectionTeam.ordinal());
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
        self.showSelectedModel((Team) model);
    }

    private void showSelectedModel(Team model) {
        self.selectedModel = (Team) model;
        self.performSegueWithIdentifier(MainSegueIdentifier.editPeopleSegueIdentifier, self);
    }

    // MARK: Navigation item actions
    private void addPeopleAction() {
        self.performSegueWithIdentifier(MainSegueIdentifier.editPeopleSegueIdentifier, self);
    }

    @Override
    protected void segueForEditPeopleViewController(IEAEditPeopleViewController destination, Intent sender) {
        /// Show detailed people
        self.setTransferedModel(sender, self.selectedModel);
    }

    @Override
    protected void PeopleWasCreated(NSNotification note) {
        self.queryTeams(self.keyword);
    }

}
