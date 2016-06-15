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

import org.ieatta.database.models.DBTeam;

import java.util.List;

import bolts.Continuation;
import bolts.Task;


public class IEAManagerPeopleViewController extends IEASplitDetailViewController {

    enum ManagerPeopleSection {
        sectionTeam;//= 0
    }

    private EditText searchTextView;
    private ImageView search_clear_Button;

    private List fetchedTeam;
    private DBTeam selectedModel;
    private boolean newModel = false;

    private String keyword = "";

    protected int getContentView() {
        return R.layout.table_controller_serch_view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setRightBarButtonItem(R.string.add, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IEAManagerPeopleViewController.this.addPeopleAction();
            }
        });

        this.searchTextView = (EditText) this.findViewById(R.id.searchTextView);
        this.search_clear_Button = (ImageView) this.findViewById(R.id.search_clear);

        this.searchTextView.setHint(R.string.Search_Hint_Team);
        this.searchTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                IEAManagerPeopleViewController.this.keyword = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                IEAManagerPeopleViewController.this.queryTeams(IEAManagerPeopleViewController.this.keyword);
            }
        });

        this.search_clear_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IEAManagerPeopleViewController.this.searchTextView.setText("");
            }
        });

        this.setRegisterCellClassWhenSelected(IEAPeopleInfoCell.getType(), ManagerPeopleSection.sectionTeam.ordinal());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void queryTeams(String keyword) {
        if (keyword == null || keyword.isEmpty() == true) {
            return;
        }
//        new DBTeam().queryParseModels(keyword)
//                .onSuccessTask(new Continuation<List , Task<Boolean>>() {
//                    @Override
//                    public Task<Boolean> then(Task<List > task) throws Exception {
//                        this.fetchedTeam = task.getResult();
//
//                        // Next, fetch related photos
//                        return this.getPhotosForModelsTask(task);
//                    }
//                }).onSuccess(new Continuation<Boolean, Object>() {
//            @Override
//            public Object then(Task<Boolean> task) throws Exception {
//                this.setSectionItems(this.fetchedTeam, ManagerPeopleSection.sectionTeam.ordinal());
//                return null;
//            }
//        }, Task.UI_THREAD_EXECUTOR);
    }

    @Override
    public void whenSelectedEvent(Object model, NSIndexPath indexPath) {
        this.selectedModel = (DBTeam) model;
        this.performSegueWithIdentifier(MainSegueIdentifier.editPeopleSegueIdentifier, this);
    }

    // MARK: Navigation item actions
    private void addPeopleAction() {
        this.newModel = true;
        this.selectedModel = new DBTeam();
        this.performSegueWithIdentifier(MainSegueIdentifier.editPeopleSegueIdentifier, this);
    }

    @Override
    protected void segueForEditPeopleViewController(IEAEditPeopleViewController destination, Intent sender) {
        /// Show detailed people
//        this.setTransferedModelForEdit(sender, this.selectedModel, this.newModel);
    }

    @Override
    protected void PeopleWasCreated(NSNotification note) {
        this.queryTeams(this.keyword);
    }

}
