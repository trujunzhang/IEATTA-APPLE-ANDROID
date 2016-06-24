package org.ieatta.activity.event;


import android.os.Bundle;

import com.ieatta.android.IEAPageActivity;

public class IEAEventActivity extends IEAPageActivity {

    private EventlTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        task = new EventlTask(this, this.recyclerView);
        task.makeActivePage();
    }

//    public ParseModelAbstract getPageModel() {
//        return this.event;
//    }
//
//    public boolean shouldShowHUD() {
//        return true;
//    }
//
//    // Transferd Model from previous page.
//    private Event event;
//    // Selected model from tableview.
//    private Team selectedModel;
//    // Fetched list by quering database.
//    private LinkedList<ParseModelAbstract/*Team*/> fetchedPeople;
//    private List<ParseModelAbstract/*PeopleInEvent*/> fetchedPeopleInEvent;
//    private boolean isTabBarHidden = false;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        this.event = (Event) this.getTransferedModel();
//
//        // Do any additional setup after loading the view.
////        assert(this.event != nil, "Must setup Event's instance.")
////        assert(this.event?.belongToModel != nil, "Must setup Restaurant's instance.")
//
////        NSNotificationCenter.defaultCenter().addObserver(this, selector: "EventWasCreated:", name: PAModelCreateEventNotification, object: nil)
////        NSNotificationCenter.defaultCenter().addObserver(this, selector: "RecipeWasCreated:", name: PARecipeCreatedNotification, object: nil)
//
//        PeopleInEvent.queryOrderedPeople(ParseModelAbstract.getPoint(this.event))
//                .onSuccessTask(new Continuation<List , Task<List >>() {
//                    @Override
//                    public Task<List > then(Task<List > task) throws Exception {
//                        this.fetchedPeopleInEvent = task.getResult();
//
//                        // 2. Get all people in the event.
//                        return Team.queryTeamByPeopleInEvent(this.fetchedPeopleInEvent);
//                    }
//                }).onSuccessTask(new Continuation<List , Task<List >>() {
//            @Override
//            public Task<List > then(Task<List > task) throws Exception {
//                this.fetchedPeople = new LinkedList (task.getResult());
//
//                //  Sort, by fetchedPeopleInEvent
//                return PeopleInEvent.sortOrderedPeople(this.fetchedPeople, this.fetchedPeopleInEvent);
//            }
//        }).onSuccessTask(new Continuation<List , Task<Boolean>>() {
//            @Override
//            public Task<Boolean> then(Task<List > task) throws Exception {
//                // Next, fetch related photos
//                return this.getPhotosForModelsTask(task);
//            }
//        }).onSuccessTask(new Continuation<Boolean, Task<Boolean>>() {
//            @Override
//            public Task<Boolean> then(Task<Boolean> task) throws Exception {
//
//                // Next, Load Reviews.
//                return this.getReviewsRelatedModelQueryTask();
//            }
//        }).onSuccess(new Continuation<Boolean, Void>() {
//            @Override
//            public Void then(Task<Boolean> task) throws Exception {
//
//                this.setRegisterCellClass(IEAEventHeaderCell.getType(), EventDetailSection.sectionHeader.ordinal());
//                this.setSectionItems(CollectionUtils.createList(new IEAEventHeader(this, this.event)), EventDetailSection.sectionHeader.ordinal());
//
//                this.setRegisterCellClassWhenSelected(IEAOrderedPeopleCell.getType(), EventDetailSection.sectionOrderedPeople.ordinal());
//                this.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.People_Ordered), EventDetailSection.sectionOrderedPeople.ordinal());
//
//                this.addOrderedPeopleSection(this.fetchedPeople);
//                this.configureReviewsSection();
//
//                return null;
//            }
//        }, Task.UI_THREAD_EXECUTOR);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        IntentCache.sharedInstance.orderedPeople = new LinkedList<>();
//    }
//
//    /// Add rows for section "Ordered People".
//    private void addOrderedPeopleSection(List  orderedPeople) {
//        List items = IEAOrderedPeople.convertToOrderedPeople(this.fetchedPeople, this.event);
//
//        this.configureDetailSection(items, R.string.Empty_for_Ordered_People, IEAOrderedPeopleCell.getType(), EventDetailSection.sectionOrderedPeople.ordinal());
//    }
//
//    @Override
//    protected int getReviewsSectionIndex() {
//        return EventDetailSection.sectionReviews.ordinal();
//    }
//
//    @Override
//    protected void didSelectPeople(NSNotification note) {
//        // 1. Add selected people to tableview.
//        ParseModelAbstract people = (ParseModelAbstract) note.anObject;
//        if (this.verifyExist(people) == true) {
//            return;
//        }
//
//        this.fetchedPeople.addFirst(people);
//        this.addOrderedPeopleSection(this.fetchedPeople);
//
//        // 2. Save selected people to database
//        new PeopleInEvent(ParseModelAbstract.getPoint(people), ParseModelAbstract.getPoint(this.event)).saveTeam().
//                continueWith(new Continuation<Void, Object>() {
//                    @Override
//                    public Object then(Task<Void> task) throws Exception {
//                        if (task.isFaulted() == true) {
//                            AppAlertView.showError(R.string.Select_people_failure);
//                        }
//                        return null;
//                    }
//                });
//    }
//
//    private boolean verifyExist(ParseModelAbstract people) {
//        for (ParseModelAbstract model : this.fetchedPeople) {
//            if (model.equals(people)) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    @Override
//    public void whenSelectedEvent(Object model, NSIndexPath indexPath) {
//        if (indexPath.section == EventDetailSection.sectionOrderedPeople.ordinal()) {
//            IEAOrderedPeople people = (IEAOrderedPeople) model;
//
//            people.model.belongToModel = this.event;
//            this.selectedModel = people.model;
//            this.performSegueWithIdentifier(MainSegueIdentifier.detailOrderedRecipesSegueIdentifier, this);
//        } else {
//            super.whenSelectedEvent(model, indexPath);
//        }
//    }
//
//    @Override
//    public void segueForOrderedRecipesViewController(IEAOrderedRecipesViewController destination, Intent sender) {
//        this.setTransferedModel(sender, this.selectedModel);
//    }
//
//    @Override
//    public void segueForChoicePeopleViewController(IEAChoicePeopleViewController destination, Intent sender) {
//        IntentCache.sharedInstance.orderedPeople = this.fetchedPeople;
//    }
//
//    @Override
//    public void segueForEditEventViewController(IEAEditEventViewController destination, Intent sender) {
//        // Edit event
//        this.setTransferedModelForEdit(sender, this.event);
//    }
//
//    // MARK: Header cell events
//    public void addPeopleTaped() {
//        this.performSegueWithIdentifier(MainSegueIdentifier.choicePeopleSegueIdentifier, this);
//    }
//
//    public void performSegueForEditingModel() {
//        this.performSegueWithIdentifier(MainSegueIdentifier.editEventSegueIdentifier, this);
//    }
//
//    // MARK: NSNotificationCenter notification handlers
//    @Override
//    protected void RecipeWasCreated(NSNotification note) {
//        this.addOrderedPeopleSection(this.fetchedPeople);
//    }
//
//    @Override
//    protected void EventWasCreated(NSNotification note) {
//        this.setSectionItems(CollectionUtils.createList(new IEAEventHeader(this, this.event)), EventDetailSection.sectionHeader.ordinal());
//    }


}
