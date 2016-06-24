package org.ieatta.activity.restaurant;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ieatta.android.R;
import com.ieatta.android.modules.cells.IEARestaurantEventsCell;
import com.ieatta.android.modules.cells.IEAReviewsCell;
import com.ieatta.android.modules.cells.headerview.IEARestaurantDetailHeaderCell;
import com.ieatta.android.modules.cells.model.IEARestaurantDetailHeader;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.android.modules.tools.CollectionUtils;
import com.ieatta.provide.AppConstant;
import com.ieatta.provide.IEAEditKey;
import com.tableview.adapter.NSIndexPath;
import com.tableview.model.IEAReviewsCellModel;

import org.ieatta.activity.LeadImageCollection;
import org.ieatta.database.models.DBEvent;
import org.ieatta.database.models.DBPhoto;
import org.ieatta.database.models.DBRestaurant;
import org.ieatta.database.provide.PhotoUsedType;
import org.ieatta.database.provide.ReviewType;
import org.ieatta.database.query.LocalDatabaseQuery;
import org.ieatta.database.realm.DBBuilder;
import org.ieatta.database.realm.RealmModelReader;
import org.ieatta.server.cache.ThumbnailImageUtil;
import org.ieatta.tasks.FragmentTask;

import java.io.File;
import java.util.List;

import bolts.Continuation;
import bolts.Task;
import io.realm.RealmResults;

public class RestaurantDetailTask extends FragmentTask {
    public RestaurantDetailTask(Activity activity, RecyclerView recyclerView) {
        super(activity, recyclerView);
    }


    public DBRestaurant restaurant;
    public RealmResults<DBEvent> events;
    private LeadImageCollection leadImageCollection; // for restaurants


    @Override
    public void onItemClick(View view, NSIndexPath indexPath, Object model, int position, boolean isLongClick) {
        if (model instanceof DBEvent) {
            DBEvent item = (DBEvent) model;

//            ((PageActivity) activity).loadPage(
//                    new HistoryEntry(MainSegueIdentifier.detailEventSegueIdentifier, item.getUUID()));
        }
    }

    enum RestaurantDetailSection {
        section_header,
        section_events,//= 0
        section_gallery_thumbnail,//= 1
        section_reviews,//= 2
    }

    /**
     * Execute Task for Restaurant detail.
     *
     * @return
     */
    public Task<Void> executeTask() {
        final String _restaurantUUID = "1CE562A4-A978-4B75-9B7B-2F3CF9F42A04";//this.entry.getHPara();

        return new RealmModelReader<DBRestaurant>(DBRestaurant.class).getFirstObject(LocalDatabaseQuery.get(_restaurantUUID), false, realmList).onSuccessTask(new Continuation<DBRestaurant, Task<RealmResults<DBPhoto>>>() {
            @Override
            public Task<RealmResults<DBPhoto>> then(Task<DBRestaurant> task) throws Exception {
                RestaurantDetailTask.this.restaurant = task.getResult();
                return LocalDatabaseQuery.queryPhotosByModel(_restaurantUUID, PhotoUsedType.PU_Restaurant.getType(), realmList);
            }
        }).onSuccessTask(new Continuation<RealmResults<DBPhoto>, Task<List<File>>>() {
            @Override
            public Task<List<File>> then(Task<RealmResults<DBPhoto>> task) throws Exception {
                RestaurantDetailTask.this.leadImageCollection = RestaurantDetailTask.this.toLeadImageCollection(task.getResult());
                return ThumbnailImageUtil.sharedInstance.getImagesListTask(_restaurantUUID);
            }
        }).onSuccessTask(new Continuation<List<File>, Task<RealmResults<DBEvent>>>() {
            @Override
            public Task<RealmResults<DBEvent>> then(Task<List<File>> task) throws Exception {
                RestaurantDetailTask.this.thumbnailGalleryCollection = RestaurantDetailTask.this.toGalleryCollection(task.getResult());
                return new RealmModelReader<DBEvent>(DBEvent.class).fetchResults(
                        new DBBuilder().whereEqualTo(AppConstant.kPAPFieldLocalRestaurantKey, _restaurantUUID), false, realmList);
            }
        }).onSuccessTask(new Continuation<RealmResults<DBEvent>, Task<List<IEAReviewsCellModel>>>() {
            @Override
            public Task<List<IEAReviewsCellModel>> then(Task<RealmResults<DBEvent>> task) throws Exception {
                RestaurantDetailTask.this.events = task.getResult();
                return reviewQuery.queryReview(_restaurantUUID, ReviewType.Review_Restaurant, AppConstant.limit_reviews);
            }
        }).onSuccess(new Continuation<List<IEAReviewsCellModel>, Void>() {
            @Override
            public Void then(Task<List<IEAReviewsCellModel>> task) throws Exception {
                RestaurantDetailTask.this.reviewsCellModelList = task.getResult();
                return null;
            }
        });
    }

    @Override
    public Task<Void> executePhotosGalleryTask() {
        return Task.forResult(null);
    }

    @Override
    public void prepareUI() {
        super.prepareUI();
        this.manager.setRegisterCellClass(IEAReviewsCell.getType(), RestaurantDetailSection.section_reviews.ordinal());
        this.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Events_Recorded), RestaurantDetailSection.section_events.ordinal());
    }

    @Override
    public void postUI() {
        this.manager.setSectionItems(IEARestaurantDetailHeaderCell.getType(),CollectionUtils.createList(new IEARestaurantDetailHeader(this.restaurant)), RestaurantDetailSection.section_header.ordinal());
        this.manager.setSectionItems(IEARestaurantEventsCell.getType(), this.events, RestaurantDetailSection.section_events.ordinal());

        postPhotosGallery(RestaurantDetailSection.section_gallery_thumbnail.ordinal());
//        postReviews(RestaurantDetailSection.section_reviews.ordinal(), mRestaurantUUID, ReviewType.Review_Restaurant, AppConstant.limit_reviews);
    }

}
