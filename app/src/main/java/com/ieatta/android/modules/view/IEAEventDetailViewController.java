package com.ieatta.android.modules.view;

import android.os.Bundle;
import android.virtualbreak.com.manualdatabase.ActivityModelDebug;

import com.ieatta.android.R;
import com.ieatta.android.modules.IEAReviewsInDetailTableViewController;
import com.ieatta.android.modules.cells.IEAOrderedPeopleCell;
import com.ieatta.android.modules.cells.headerview.IEAEventHeaderCell;
import com.ieatta.android.modules.cells.model.IEAEventHeader;
import com.ieatta.android.modules.cells.model.IEAOrderedPeople;
import com.ieatta.android.modules.common.edit.IEAEditKey;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.android.modules.tools.CollectionUtils;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Event;
import com.ieatta.com.parse.models.PeopleInEvent;
import com.ieatta.com.parse.models.Team;

import java.util.List;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 12/1/15.
 */
enum EventDetailSection {
    sectionHeader,        //= 0
    sectionOrderedPeople, //= 1
    sectionReviews,       //= 2
}

public class IEAEventDetailViewController extends IEAReviewsInDetailTableViewController implements IEAChoicePeopleViewController.IEAChoicePeopleViewProtocol {
    private IEAEventDetailViewController self = this;


    public ParseModelAbstract getPageModel() {
        return self.event;
    }

    public boolean shouldShowHUD() {
        return true;
    }

    // Transferd Model from previous page.
    private Event event;
    // Selected model from tableview.
    private Team selectedModel;
    // Fetched list by quering database.
    private List<ParseModelAbstract/*Team*/> fetchedPeople;
    private List<ParseModelAbstract/*PeopleInEvent*/> fetchedPeopleInEvent;
    private boolean isTabBarHidden = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO djzhang(test)
        self.event = ActivityModelDebug.getEventForEventDetail();


        // Do any additional setup after loading the view.
//        assert(self.event != nil, "Must setup Event's instance.")
//        assert(self.event?.belongToModel != nil, "Must setup Restaurant's instance.")

//        NSNotificationCenter.defaultCenter().addObserver(self, selector: "EventWasCreated:", name: PAModelCreateEventNotification, object: nil)
//        NSNotificationCenter.defaultCenter().addObserver(self, selector: "RecipeWasCreated:", name: PARecipeCreatedNotification, object: nil)

        PeopleInEvent.queryOrderedPeople(ParseModelAbstract.getPoint(self.event))
                .onSuccessTask(new Continuation<List<ParseModelAbstract>, Task<List<ParseModelAbstract>>>() {
                    @Override
                    public Task<List<ParseModelAbstract>> then(Task<List<ParseModelAbstract>> task) throws Exception {
                        self.fetchedPeopleInEvent = task.getResult();
//  Sort, by fetchedPeopleInEvent
//                        PeopleInEvent.sortOrderedPeople(task, self.fetchedPeopleInEvent);

                        // 2. Get all people in the event.
                        return Team.queryTeamByPoints(self.fetchedPeopleInEvent);
                    }
                }).onSuccessTask(new Continuation<List<ParseModelAbstract>, Task<Boolean>>() {
            @Override
            public Task<Boolean> then(Task<List<ParseModelAbstract>> task) throws Exception {
                self.fetchedPeople = task.getResult();
//
//                // Next, fetch related photos
                return self.getPhotosForModelsTask(task);
            }
////        }).continueWith(new Continuation<Object, Object>() {
////            @Override
////            public Object then(Task<Object> task) throws Exception {
////                // Next, Load Reviews.
////                return self.getReviewsReleatdModelQueryTask();
////            }
        }).onSuccess(new Continuation<Boolean, Void>() {
            @Override
            public Void then(Task<Boolean> task) throws Exception {

                // Finally, hide hud.
                self.hideHUD();

                self.setRegisterCellClass(IEAEventHeaderCell.class, IEAEventHeaderCell.layoutResId, EventDetailSection.sectionHeader.ordinal());
                self.setSectionItems(CollectionUtils.createList(new IEAEventHeader(self, self.event)), EventDetailSection.sectionHeader.ordinal());

                self.setRegisterCellClassWhenSelected(IEAOrderedPeopleCell.class, IEAOrderedPeopleCell.layoutResId, EventDetailSection.sectionOrderedPeople.ordinal());
                self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.People_Ordered), EventDetailSection.sectionOrderedPeople.ordinal());

                    self.addOrderedPeopleSection(self.fetchedPeople);
//                    self.configureReviewsSection(task.result as! [Team]);


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


//        PeopleInEvent.queryOrderedPeople(ParseModelAbstract.getPoint(self.event))
//                .continueWith(new Continuation<Object, Object>() {
//                    @Override
//                    public Object then(Task<Object> task) throws Exception {
//                        self.fetchedPeopleInEvent = new LinkedList((Collection<? extends PeopleInEvent>) task.getResult());
// // Sort, by fetchedPeopleInEvent
////                        PeopleInEvent.sortOrderedPeople(task, self.fetchedPeopleInEvent);
//
//                        // 2. Get all people in the event.
//                        return Team.queryTeam(self.fetchedPeopleInEvent);
//                    }
//                }).continueWith(new Continuation<Object, Object>() {
//            @Override
//            public Object then(Task<Object> task) throws Exception {
//                Object object = task;
//                self.fetchedPeople = new LinkedList((Collection<? extends Team>) task.getResult());
//
//                // Next, fetch related photos
//                return self.getPhotosForModelsTask(task);
//            }
////        }).continueWith(new Continuation<Object, Object>() {
////            @Override
////            public Object then(Task<Object> task) throws Exception {
////                // Next, Load Reviews.
////                return self.getReviewsReleatdModelQueryTask();
////            }
//        }).continueWith(new Continuation<Object, Object>() {
//            @Override
//            public Object then(Task<Object> task) throws Exception {
//                Object object = task;
//                return null;
//            }
//        });

//        PeopleInEvent.queryOrderedPeople(ParseModelAbstract.getPoint(self.event))
//                .continueWith(new Continuation<Object, Object>() {
//                    @Override
//                    public Object then(Task<Object> task) throws Exception {
//                        self.fetchedPeopleInEvent = new LinkedList((Collection<? extends PeopleInEvent>) task.getResult());
//
//                        // 2. Get all people in the event.
//                        return Team.queryTeam(self.fetchedPeopleInEvent);
//                    }
//                }).continueWith(new Continuation<Object, Object>() {
//            @Override
//            public Object then(Task<Object> task) throws Exception {
//                Object object = task;
//
//                // Next, fetch related photos
//                return self.getPhotosForModelsTask(task);
//            }
//        }).continueWith(new Continuation<Object, Object>() {
//            @Override
//            public Object then(Task<Object> task) throws Exception {
//                // Sort, by fetchedPeopleInEvent
//                return PeopleInEvent.sortOrderedPeople(task, self.fetchedPeopleInEvent);
//            }
//        }).continueWith(new Continuation<Object, Object>() {
//            @Override
//            public Object then(Task<Object> task) throws Exception {
//                self.fetchedPeople = new LinkedList<Team>((Collection<? extends Team>) task.getResult());
//                // Next, Load Reviews.
//                return self.getReviewsReleatdModelQueryTask();
//            }
//        }).continueWith(new Continuation<Object, Object>() {
//            @Override
//            public Object then(Task<Object> task) throws Exception {
//                    // Finally, hide hud.
//                    self.hideHUD();
//
////                    self.setRegisterCellClass(IEAEventHeaderCell);
////                    self.setRegisterCellClassWhenSelected(IEAOrderedPeopleCell.self);
//
////                    self.appendSectionTitleCell(SectionTitleCellModel(editKey: IEAEditKey.Section_Title, title: ""), forSectionIndex: EventDetailSection.sectionHeader.rawValue)
////                    self.appendSectionTitleCell(SectionTitleCellModel(editKey: IEAEditKey.Section_Title, title: L10n.PeopleOrdered.string), forSectionIndex: EventDetailSection.sectionOrderedPeople.rawValue)
//
////                    self.setSectionItems([new  IEAEventHeader(viewController: self, model:self.event!)], forSectionIndex: EventDetailSection.sectionHeader.ordinal());
//
////                    self.addOrderedPeopleSection(self.fetchedPeople);
////                    .configureReviewsSection(task.result as! [Team]);
//
//
//                return null;
//            }
//        });

    }

    /// Add rows for section "Ordered People".
    private void addOrderedPeopleSection(List<ParseModelAbstract> orderedPeople){
        setSectionItems(IEAOrderedPeople.convertToOrderedPeople(self.fetchedPeople, self.event),  EventDetailSection.sectionOrderedPeople.ordinal());
    }

    @Override
    public void didSelectPeople(Team people) {

    }
}
