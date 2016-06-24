package com.ieatta.android.modules.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.cache.IntentCache;
import com.tableview.storage.models.CellType;
import com.ieatta.android.modules.IEABaseTableViewController;
import com.ieatta.android.modules.adapter.NSIndexPath;
import com.ieatta.android.modules.cells.IEAEmptyInfoCell;
import com.ieatta.android.modules.cells.IEAPeopleInfoCell;
import com.ieatta.android.modules.cells.headerfooterview.IEAChoicePeopleHeaderCell;
import com.ieatta.android.modules.common.MainSegueIdentifier;
import com.ieatta.android.modules.common.edit.SectionChoicePeopleCellModel;
import com.ieatta.provide.IEAEditKey;
import com.ieatta.android.modules.tools.CollectionUtils;
import com.ieatta.android.modules.view.edit.IEAEditPeopleViewController;
import com.ieatta.android.notification.NSNotification;
import com.ieatta.android.notification.NSNotificationCenter;
import com.ieatta.android.notification.NotifyType;

import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

public class IEAChoicePeopleViewController extends IEABaseTableViewController {

    enum ChoicePeopleSection {
        sectionPeople,  //= 0
    }

    private TextView infoLabel;

    @Override
    public boolean shouldShowHUD() {
        return true;
    }

    // MARK: Already ordered people
    private List orderedPeople = null;// **** Important ****(init null)
    private List fetchedPeople = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        this.orderedPeople = IntentCache.sharedInstance.orderedPeople;

        // Do any additional setup after loading the view.
//        assert(this.orderedPeople != nil, "Must setup orderedPeople's instance.")
//        assert(this.delegate != nil, "Must setup delegate's instance.")

//        NSNotificationCenter.defaultCenter().addObserver(this, selector: "PeopleWasCreated:", name: PAPeopleCreatedNotification, object: nil)

//        this.navigationItem.rightBarButtonItem  = UIBarButtonItem(title: L10n.AddRightButton.string,  style: .Plain, target: this, action: "addPeopleAction:")
        this.setRightBarButtonItem(R.string.Add_Right_Button, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IEAChoicePeopleViewController.this.addPeopleAction();
            }
        });

        this.infoLabel = (TextView) this.findViewById(R.id.emptyInfoTextView);

        this.queryPeopleOrderedList();
    }

    @Override
    protected int getContentView() {
        return R.layout.table_controller_view_with_emptyinfo;
    }

    private void queryPeopleOrderedList() {
//        Team.queryTeam().onSuccessTask(new Continuation<List , Task<List >>() {
//            @Override
//            public Task<List > then(Task<List > task) throws Exception {
//                // Next, filter ordered people
//                return Team.filterFrom(task, this.orderedPeople);
//            }
//        }).onSuccessTask(new Continuation<List , Task<Boolean>>() {
//            @Override
//            public Task<Boolean> then(Task<List > task) throws Exception {
//                this.fetchedPeople = task.getResult();
//                // Next, fetch related photos
//                return this.getPhotosForModelsTask(task);
//            }
//        }).onSuccess(new Continuation<Boolean, Void>() {
//            @Override
//            public Void then(Task<Boolean> task) throws Exception {
//
//                this.setRegisterHeaderClass(IEAChoicePeopleHeaderCell.getType());
//                this.appendSectionTitleCell(new SectionChoicePeopleCellModel(IEAEditKey.Section_Title, this), ChoicePeopleSection.sectionPeople.ordinal(), IEAChoicePeopleHeaderCell.getType());
//
//                this.setRegisterCellClassWhenSelected(IEAPeopleInfoCell.getType(), ChoicePeopleSection.sectionPeople.ordinal());
//                this.setSectionItems(this.fetchedPeople, ChoicePeopleSection.sectionPeople.ordinal());
//
//                this.configureDetailSection(this.fetchedPeople,R.string.Empty_for_Friends,IEAPeopleInfoCell.getType(), ChoicePeopleSection.sectionPeople.ordinal());
//
//                return null;
//            }
//        }, Task.UI_THREAD_EXECUTOR);

    }

    @Override
    public void whenSelectedEvent(Object model, NSIndexPath indexPath) {
        NSNotificationCenter.defaultCenter().postNotificationName(NotifyType.PANotificationChoicePerson, model);
        this.popViewControllerAnimated(true);
    }

    @Override
    protected void segueForEditPeopleViewController(IEAEditPeopleViewController destination, Intent sender) {
        // Add people
//        this.setTransferedModelForEdit(sender, new Team(), true);
    }

    // MARK: NSNotificationCenter notification handlers
    @Override
    protected void PeopleWasCreated(NSNotification note) {
        this.queryPeopleOrderedList();
    }

    // MARK: Navigation item actions
    private void addPeopleAction() {
        this.performSegueWithIdentifier(MainSegueIdentifier.editPeopleSegueIdentifier, this);
    }

    public void configureDetailSection(List items,int emptyInfoResId,CellType type, int forSectionIndex){

        if(items.size() == 0){
            this.infoLabel.setVisibility(View.VISIBLE);
            this.infoLabel.setText(emptyInfoResId);
        }else{
            this.infoLabel.setVisibility(View.GONE);

//            this.setRegisterCellClass(type, forSectionIndex);
//            this.setSectionItems(items, forSectionIndex);
        }
    }
}
