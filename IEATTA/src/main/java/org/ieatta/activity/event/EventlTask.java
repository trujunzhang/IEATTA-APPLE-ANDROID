package org.ieatta.activity.event;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ieatta.android.R;
import com.ieatta.android.modules.cells.IEAOrderedPeopleCell;
import com.ieatta.android.modules.cells.IEAReviewsCell;
import com.ieatta.android.modules.cells.headerview.IEAEventHeaderCell;
import com.ieatta.android.modules.cells.model.IEAEventHeader;
import com.ieatta.android.modules.common.MainSegueIdentifier;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.android.modules.tools.CollectionUtils;
import com.ieatta.provide.AppConstant;
import com.ieatta.provide.IEAEditKey;
import com.tableview.adapter.NSIndexPath;
import com.tableview.model.IEAOrderedPeople;

import org.ieatta.activity.LeadImageCollection;
import org.ieatta.database.models.DBEvent;
import org.ieatta.database.models.DBPeopleInEvent;
import org.ieatta.database.models.DBPhoto;
import org.ieatta.database.models.DBRestaurant;
import org.ieatta.database.models.DBTeam;
import org.ieatta.database.provide.PhotoUsedType;
import org.ieatta.database.provide.ReviewType;
import org.ieatta.database.query.IEAReviewsCellModel;
import org.ieatta.database.query.LocalDatabaseQuery;
import org.ieatta.database.realm.RealmModelReader;

import org.ieatta.tasks.FragmentTask;
import org.ieatta.utils.DBConvert;
import org.wikipedia.util.DimenUtil;

import java.util.List;

import bolts.Continuation;
import bolts.Task;
import io.realm.RealmResults;

public class EventlTask extends FragmentTask {
    public EventlTask(Activity activity, RecyclerView recyclerView) {
        super(activity, recyclerView);
    }

    @Override
    public void onItemClick(View view, NSIndexPath indexPath, Object model, int position, boolean isLongClick) {
        if (model instanceof IEAOrderedPeople) {
            IEAOrderedPeople item = (IEAOrderedPeople) model;

//            ((PageActivity) activity).loadPage(
//                    new HistoryEntry(MainSegueIdentifier.detailOrderedRecipesSegueIdentifier, item.getEventUUID(), item.getTeamUUID()));
        }
    }

    enum EventDetailSection {
        section_header,
        section_ordered_people, //= 0
        section_reviews,       //= 1
    }

    public DBRestaurant restaurant;
    public DBEvent event;
    public List<IEAOrderedPeople> orderedPeopleList;

    /**
     * Execute Task for Event detail.
     *
     * @return
     */
    @Override
    public Task<Void> executeTask() {
        Bundle extras = this.activity.getIntent().getExtras();
        final String _eventUUID = extras.getString(EXTRA_ID);

        return new RealmModelReader<DBEvent>(DBEvent.class).getFirstObject(LocalDatabaseQuery.get(_eventUUID), false, this.realmList).onSuccessTask(new Continuation<DBEvent, Task<DBRestaurant>>() {
            @Override
            public Task<DBRestaurant> then(Task<DBEvent> task) throws Exception {
                DBEvent event = task.getResult();
                EventlTask.this.event = event;
                EventlTask.this.mRestaurantUUID = event.getRestaurantRef();
                return new RealmModelReader<DBRestaurant>(DBRestaurant.class).getFirstObject(LocalDatabaseQuery.get(EventlTask.this.mRestaurantUUID), false, realmList);
            }
        }).onSuccessTask(new Continuation<DBRestaurant, Task<RealmResults<DBPhoto>>>() {
            @Override
            public Task<RealmResults<DBPhoto>> then(Task<DBRestaurant> task) throws Exception {
                EventlTask.this.restaurant = task.getResult();
                return LocalDatabaseQuery.queryPhotosByModel(EventlTask.this.mRestaurantUUID, PhotoUsedType.PU_Restaurant.getType(), realmList);
            }
        }).onSuccessTask(new Continuation<RealmResults<DBPhoto>, Task<RealmResults<DBPeopleInEvent>>>() {
            @Override
            public Task<RealmResults<DBPeopleInEvent>> then(Task<RealmResults<DBPhoto>> task) throws Exception {
                return new RealmModelReader<DBPeopleInEvent>(DBPeopleInEvent.class).fetchResults(
                        LocalDatabaseQuery.getQueryOrderedPeople(_eventUUID), false, realmList);
            }
        }).onSuccessTask(new Continuation<RealmResults<DBPeopleInEvent>, Task<RealmResults<DBTeam>>>() {
            @Override
            public Task<RealmResults<DBTeam>> then(Task<RealmResults<DBPeopleInEvent>> task) throws Exception {
                List<String> peoplePoints = DBConvert.getPeoplePoints(task.getResult());
                return new RealmModelReader<DBTeam>(DBTeam.class).fetchResults(LocalDatabaseQuery.getObjectsByUUIDs(peoplePoints), false, realmList);
            }
        }).onSuccessTask(new Continuation<RealmResults<DBTeam>, Task<List<IEAReviewsCellModel>>>() {
            @Override
            public Task<List<IEAReviewsCellModel>> then(Task<RealmResults<DBTeam>> task) throws Exception {
                EventlTask.this.orderedPeopleList = EventlTask.this.toOrderedPeopleList(task.getResult(), EventlTask.this.event);
                return reviewQuery.queryReview(_eventUUID, ReviewType.Review_Event, AppConstant.limit_reviews);
            }
        }).onSuccess(new Continuation<List<IEAReviewsCellModel>, Void>() {
            @Override
            public Void then(Task<List<IEAReviewsCellModel>> task) throws Exception {
                EventlTask.this.reviewsCellModelList = task.getResult();
                return null;
            }
        });
    }

//    @Override
//    public Task<Void> executeUpdateTask(UpdateEntry entry) {
//        return null;
//    }


    @Override
    public void prepareUI() {
        super.prepareUI();

        this.manager.setRegisterCellClass(IEAOrderedPeopleCell.getType(), EventDetailSection.section_ordered_people.ordinal());
        this.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.People_Ordered), EventDetailSection.section_ordered_people.ordinal());
    }

    @Override
    public void postUI() {
        this.manager.setSectionItems(IEAEventHeaderCell.getType(),
                CollectionUtils.createList(new IEAEventHeader(this.restaurant.getDisplayName(), this.event.getDisplayName(), this.event.getUUID())),
                EventDetailSection.section_header.ordinal());
        this.manager.setSectionItems(this.orderedPeopleList, EventDetailSection.section_ordered_people.ordinal());

        postReviews(EventDetailSection.section_reviews.ordinal(), mEventUUID, ReviewType.Review_Event, AppConstant.limit_reviews);
    }

//    @Override
//    /**
//     * Edit the detailed Event.
//     */
//    public void onEditClick() {
//        ((PageActivity) activity).loadPage(
//                new HistoryEntry(MainSegueIdentifier.editEventSegueIdentifier, this.event.getUUID(),false));
//    }
//
//    @Override
//    /**
//     * Choice an ordered people.
//     */
//    public void onChoicePersonClick() {
//        ((PageActivity) activity).loadPage(
//                new HistoryEntry(MainSegueIdentifier.choicePeopleSegueIdentifier, this.event.getUUID(),true));
//    }
}
