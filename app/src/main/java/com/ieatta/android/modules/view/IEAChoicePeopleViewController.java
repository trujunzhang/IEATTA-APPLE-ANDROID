package com.ieatta.android.modules.view;

import android.os.Bundle;

import com.ieatta.android.modules.IEABaseTableViewController;
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
    private IEAChoicePeopleViewProtocol delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO djzhang(test)
        self.orderedPeople =new LinkedList<>();

        // Do any additional setup after loading the view.
//        assert(self.orderedPeople != nil, "Must setup orderedPeople's instance.")
//        assert(self.delegate != nil, "Must setup delegate's instance.")

//        NSNotificationCenter.defaultCenter().addObserver(self, selector: "PeopleWasCreated:", name: PAPeopleCreatedNotification, object: nil)

//        self.navigationItem.rightBarButtonItem  = UIBarButtonItem(title: L10n.AddRightButton.string,  style: .Plain, target: self, action: "addPeopleAction:")

        self.queryPeopleOrderedList();
    }

    private void queryPeopleOrderedList() {
        Team.queryTeam().onSuccessTask(new Continuation<List<ParseModelAbstract>, Task<List<ParseModelAbstract>>>() {
            @Override
            public Task<List<ParseModelAbstract>> then(Task<List<ParseModelAbstract>> task) throws Exception {
                Object object = task;
                // Next, filter ordered people
                return Team.filterFrom(task, self.orderedPeople);
            }
        }).onSuccessTask(new Continuation<List<ParseModelAbstract>, Task<Boolean>>() {
            @Override
            public Task<Boolean> then(Task<List<ParseModelAbstract>> task) throws Exception {
                // Next, fetch related photos
                return self.getPhotosForModelsTask(task);
            }
        }).onSuccess(new Continuation<Boolean, Void>() {
            @Override
            public Void then(Task<Boolean> task) throws Exception {
                // Finally, hide hud.
                self.hideHUD();

//                    self.setRegisterHeaderClass(IEAChoicePeopleHeaderCell);
//                    self.setRegisterCellClassWhenSelected(IEAPeopleInfoCell.self);

//                    self.appendSectionTitleCell(new  SectionChoicePeopleCellModel( IEAEditKey.Section_Title, viewController: self),  ChoicePeopleSection.sectionPeople.ordinal());

//                    self.setSectionItems(fetchedPeople,  ChoicePeopleSection.sectionPeople.ordinal());

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

    private void queryPeopleOrderedListxxx() {


//        Team.queryTeam().continueWith(new Continuation<LinkedList<ParseModelAbstract>, Object>() {
//            @Override
//            public Object then(Task<LinkedList<ParseModelAbstract>> task) throws Exception {
//                // Next, filter ordered people
//                return Team.filterFrom(task, self.orderedPeople);
//            }
//        }).continueWith(new Continuation<Object, Object>() {
//            @Override
//            public Object then(Task<Object> task) throws Exception {
//                // Next, fetch related photos
//                return self.getPhotosForModelsTask(task);
//            }
//        }).continueWith(new Continuation<Object, Object>() {
//            @Override
//            public Object then(Task<Object> task) throws Exception {
//                LinkedList<Object> fetchedPeople = new LinkedList<Object>((Collection<?>) task.getResult());
//
//                if (task.getError() != null) {
//
//                } else {
//                    // Finally, hide hud.
//                    self.hideHUD();
//
////                    self.setRegisterHeaderClass(IEAChoicePeopleHeaderCell);
////                    self.setRegisterCellClassWhenSelected(IEAPeopleInfoCell.self);
//
////                    self.appendSectionTitleCell(new  SectionChoicePeopleCellModel( IEAEditKey.Section_Title, viewController: self),  ChoicePeopleSection.sectionPeople.ordinal());
//
////                    self.setSectionItems(fetchedPeople,  ChoicePeopleSection.sectionPeople.ordinal());
//
//                }
//                return null;
//            }
//        });
    }
}
