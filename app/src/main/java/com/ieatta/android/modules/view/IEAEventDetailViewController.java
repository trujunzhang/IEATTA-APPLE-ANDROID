package com.ieatta.android.modules.view;

import android.content.Intent;
import android.os.Bundle;
import android.virtualbreak.com.manualdatabase.ActivityModelDebug;

import com.ieatta.android.R;
import com.ieatta.android.cache.IntentCache;
import com.ieatta.android.modules.IEAReviewsInDetailTableViewController;
import com.ieatta.android.modules.adapter.NSIndexPath;
import com.ieatta.android.modules.cells.IEAOrderedPeopleCell;
import com.ieatta.android.modules.cells.headerview.IEAEventHeaderCell;
import com.ieatta.android.modules.cells.model.IEAEventHeader;
import com.ieatta.android.modules.cells.model.IEAOrderedPeople;
import com.ieatta.android.modules.common.MainSegueIdentifier;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.tools.CollectionUtils;
import com.ieatta.android.modules.view.edit.IEAEditEventViewController;
import com.ieatta.android.notification.NSNotification;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Event;
import com.ieatta.com.parse.models.PeopleInEvent;
import com.ieatta.com.parse.models.Team;

import java.util.LinkedList;
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

public class IEAEventDetailViewController extends IEAReviewsInDetailTableViewController {
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
    private LinkedList<ParseModelAbstract/*Team*/> fetchedPeople;
    private List<ParseModelAbstract/*PeopleInEvent*/> fetchedPeopleInEvent;
    private boolean isTabBarHidden = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        self.event = (Event) self.getTransferedModel();
        // TODO: djzhang(test)
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

                        // 2. Get all people in the event.
                        return Team.queryTeamByPeopleInEvent(self.fetchedPeopleInEvent);
                    }
                }).onSuccessTask(new Continuation<List<ParseModelAbstract>, Task<List<ParseModelAbstract>>>() {
            @Override
            public Task<List<ParseModelAbstract>> then(Task<List<ParseModelAbstract>> task) throws Exception {
                self.fetchedPeople = new LinkedList<ParseModelAbstract>(task.getResult());

                //  Sort, by fetchedPeopleInEvent
                return PeopleInEvent.sortOrderedPeople(self.fetchedPeople, self.fetchedPeopleInEvent);
            }
        }).onSuccessTask(new Continuation<List<ParseModelAbstract>, Task<Boolean>>() {
            @Override
            public Task<Boolean> then(Task<List<ParseModelAbstract>> task) throws Exception {
                // Next, fetch related photos
                return self.getPhotosForModelsTask(task);
            }
        }).onSuccessTask(new Continuation<Boolean, Task<Boolean>>() {
            @Override
            public Task<Boolean> then(Task<Boolean> task) throws Exception {
                // Next, Load Reviews.
                return self.getReviewsRelatedModelQueryTask();
            }
        }).onSuccess(new Continuation<Boolean, Void>() {
            @Override
            public Void then(Task<Boolean> task) throws Exception {

                // Finally, hide hud.
                self.hideHUD();

                self.setRegisterCellClass(IEAEventHeaderCell.getType(), EventDetailSection.sectionHeader.ordinal());
                self.setSectionItems(CollectionUtils.createList(new IEAEventHeader(self, self.event)), EventDetailSection.sectionHeader.ordinal());

                self.setRegisterCellClassWhenSelected(IEAOrderedPeopleCell.getType(), EventDetailSection.sectionOrderedPeople.ordinal());
                self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.People_Ordered), EventDetailSection.sectionOrderedPeople.ordinal());

                self.addOrderedPeopleSection(self.fetchedPeople);
                self.configureReviewsSection();

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
    protected void onResume() {
        super.onResume();
        IntentCache.sharedInstance.orderedPeople = new LinkedList<>();
    }

    /// Add rows for section "Ordered People".
    private void addOrderedPeopleSection(List<ParseModelAbstract> orderedPeople) {
        setSectionItems(IEAOrderedPeople.convertToOrderedPeople(self.fetchedPeople, self.event), EventDetailSection.sectionOrderedPeople.ordinal());
    }

    @Override
    protected int getReviewsSectionIndex() {
        return EventDetailSection.sectionReviews.ordinal();
    }

    @Override
    protected void didSelectPeople(NSNotification note) {
        // 1. Add selected people to tableview.
        ParseModelAbstract people = (ParseModelAbstract) note.anObject;

        self.fetchedPeople.addFirst(people);
        self.addOrderedPeopleSection(self.fetchedPeople);

        // 2. Save selected people to database
        new PeopleInEvent(ParseModelAbstract.getPoint(people), ParseModelAbstract.getPoint(self.event)).saveTeam().continueWith(new Continuation<Void, Object>() {
            @Override
            public Object then(Task<Void> task) throws Exception {
                if (task.isFaulted() == true) {
//                    AppAlertView.showError(L10n.SelectPeopleFailure.string)
                }
                return null;
            }
        });
    }

    @Override
    public void whenSelectedEvent(Object model, NSIndexPath indexPath) {
        if (indexPath.section == EventDetailSection.sectionOrderedPeople.ordinal()) {
            IEAOrderedPeople people = (IEAOrderedPeople) model;

            people.model.belongToModel = self.event;
            self.selectedModel = people.model;
            self.performSegueWithIdentifier(MainSegueIdentifier.detailOrderedRecipesSegueIdentifier, self);
        } else {
            super.whenSelectedEvent(model, indexPath);
        }
    }

    @Override
    public void segueForOrderedRecipesViewController(IEAOrderedRecipesViewController destination, Intent sender) {
        self.setTransferedModel(sender, self.selectedModel);
    }

    @Override
    public void segueForChoicePeopleViewController(IEAChoicePeopleViewController destination, Intent sender) {
        IntentCache.sharedInstance.orderedPeople = self.fetchedPeople;
    }

    @Override
    public void segueForEditEventViewController(IEAEditEventViewController destination, Intent sender) {
        // Edit event
        self.setTransferedModelForEdit(sender, self.event);
    }

    // MARK: Header cell events
    public void addPeopleTaped() {
        self.performSegueWithIdentifier(MainSegueIdentifier.choicePeopleSegueIdentifier, self);
    }

    public void performSegueForEditingModel() {
        self.performSegueWithIdentifier(MainSegueIdentifier.editEventSegueIdentifier, self);
    }

    // MARK: NSNotificationCenter notification handlers
    @Override
    protected void RecipeWasCreated(NSNotification note) {
        self.addOrderedPeopleSection(self.fetchedPeople);
    }

    @Override
    protected void EventWasCreated(NSNotification note) {
        self.setSectionItems(CollectionUtils.createList(new IEAEventHeader(self, self.event)), EventDetailSection.sectionHeader.ordinal());
    }


}
