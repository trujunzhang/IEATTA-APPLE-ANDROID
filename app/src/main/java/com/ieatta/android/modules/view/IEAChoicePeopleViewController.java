package com.ieatta.android.modules.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ieatta.android.R;
import com.ieatta.android.modules.IEABaseTableViewController;
import com.ieatta.android.modules.adapter.NSIndexPath;
import com.ieatta.android.modules.cells.IEAPeopleInfoCell;
import com.ieatta.android.modules.cells.headerfooterview.IEAChoicePeopleHeaderCell;
import com.ieatta.android.modules.common.MainSegueIdentifier;
import com.ieatta.android.modules.common.edit.SectionChoicePeopleCellModel;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.view.edit.IEAEditPeopleViewController;
import com.ieatta.android.notification.NSNotification;
import com.ieatta.android.notification.NSNotificationCenter;
import com.ieatta.android.notification.NotifyType;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Team;

import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;


/**
 * Created by djzhang on 12/1/15.
 */


enum ChoicePeopleSection {
    sectionPeople,  //= 0
}

public class IEAChoicePeopleViewController extends IEABaseTableViewController {
    private IEAChoicePeopleViewController self = this;

    public interface IEAChoicePeopleViewProtocol {
        public void didSelectPeople(Team people);
    }

    @Override
    public boolean shouldShowHUD() {
        return true;
    }

    // MARK: Already ordered people
    private List<Team> orderedPeople = null;// **** Important ****(init null)
    private List<ParseModelAbstract/*Team*/> fetchedPeople = new LinkedList<>();
    private IEAChoicePeopleViewProtocol delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO djzhang(test)
        self.orderedPeople = new LinkedList<>();

        // Do any additional setup after loading the view.
//        assert(self.orderedPeople != nil, "Must setup orderedPeople's instance.")
//        assert(self.delegate != nil, "Must setup delegate's instance.")

//        NSNotificationCenter.defaultCenter().addObserver(self, selector: "PeopleWasCreated:", name: PAPeopleCreatedNotification, object: nil)

//        self.navigationItem.rightBarButtonItem  = UIBarButtonItem(title: L10n.AddRightButton.string,  style: .Plain, target: self, action: "addPeopleAction:")
        self.setRightBarButtonItem(R.string.Add_Right_Button, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                self.addPeopleAction();
            }
        });

        self.queryPeopleOrderedList();
    }

    private void queryPeopleOrderedList() {
        Team.queryTeam().onSuccessTask(new Continuation<List<ParseModelAbstract>, Task<List<ParseModelAbstract>>>() {
            @Override
            public Task<List<ParseModelAbstract>> then(Task<List<ParseModelAbstract>> task) throws Exception {
                // Next, filter ordered people
                return Team.filterFrom(task, self.orderedPeople);
            }
        }).onSuccessTask(new Continuation<List<ParseModelAbstract>, Task<Boolean>>() {
            @Override
            public Task<Boolean> then(Task<List<ParseModelAbstract>> task) throws Exception {
                self.fetchedPeople = task.getResult();
                // Next, fetch related photos
                return self.getPhotosForModelsTask(task);
            }
        }).onSuccess(new Continuation<Boolean, Void>() {
            @Override
            public Void then(Task<Boolean> task) throws Exception {
                // Finally, hide hud.
                self.hideHUD();

                self.setRegisterHeaderClass(IEAChoicePeopleHeaderCell.getType());
                self.appendSectionTitleCell(new SectionChoicePeopleCellModel(IEAEditKey.Section_Title, self), ChoicePeopleSection.sectionPeople.ordinal(), IEAChoicePeopleHeaderCell.getType());

                self.setRegisterCellClassWhenSelected(IEAPeopleInfoCell.getType(), ChoicePeopleSection.sectionPeople.ordinal());
                self.setSectionItems(self.fetchedPeople, ChoicePeopleSection.sectionPeople.ordinal());

                return null;
            }
        }).continueWith(new Continuation<Void, Object>() {
            @Override
            public Object then(Task<Void> task) throws Exception {
                if (task.isFaulted() == true) {
                }
                return null;
            }
        });

    }

    @Override
    public void whenSelectedEvent(Object model, NSIndexPath indexPath) {
        Team team = (Team) model;
        NSNotificationCenter.defaultCenter().postNotificationName(NotifyType.PAModelCreatedRestaurantNotification, team);
        self.navigationController.popViewControllerAnimated(true);
    }

    @Override
    protected void segueForEditPeopleViewController(IEAEditPeopleViewController destination, Intent sender) {
        // Add people
        self.setTransferedModelForEdit(sender,new Team(),true);
    }

    // MARK: NSNotificationCenter notification handlers
    @Override
    protected void PeopleWasCreated(NSNotification note) {
        self.queryPeopleOrderedList();
    }

    // MARK: Navigation item actions
    private void addPeopleAction() {
        self.performSegueWithIdentifier(MainSegueIdentifier.editPeopleSegueIdentifier, self);
    }
}
