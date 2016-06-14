package org.ieatta.tasks;

import android.app.Activity;
import android.support.annotation.VisibleForTesting;
import android.view.View;

import com.ieatta.android.R;
import com.ieatta.android.modules.common.edit.SectionMoreReviewsFooterCellModel;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.tableview.RecycleViewManager;
import com.tableview.adapter.NSIndexPath;
import com.tableview.adapter.RecyclerOnItemClickListener;
import com.tableview.model.IEAGalleryThumbnail;
import com.tableview.model.IEAReviewsCellModel;
import com.tableview.utils.CollectionUtil;

import org.ieatta.activity.gallery.GalleryActivity;
import org.ieatta.activity.gallery.GalleryCollection;
import org.ieatta.activity.gallery.GalleryThumbnailScrollView;

import org.ieatta.database.provide.ReviewType;
import org.ieatta.database.query.LocalDatabaseQuery;
import org.ieatta.database.query.ReviewQuery;

import org.wikipedia.analytics.GalleryFunnel;
import org.wikipedia.analytics.RecycleCellFunnel;
import org.wikipedia.util.DimenUtil;

import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;
import io.realm.Realm;

public abstract class FragmentTask implements RecyclerOnItemClickListener {
    protected String mRestaurantUUID;
    protected String mEventUUID;
    protected String mTeamUUID;
    protected String mRecipeUUID;

    public RecycleViewManager manager;
    protected Activity activity;

    // For showing photo's thumbnail gallery.
    public GalleryCollection thumbnailGalleryCollection = new GalleryCollection();

    // For showing reviews list.
    public List<IEAReviewsCellModel> reviewsCellModelList;
    protected static List<Realm> realmList = new LinkedList<>();
    protected ReviewQuery reviewQuery = new ReviewQuery();

    public void onItemClick(View view, NSIndexPath indexPath, Object model, int position, boolean isLongClick) {

    }

    public Task<Void> executeTask() {
        return Task.forResult(null);
    }

//    public  Task<Void> executeUpdateTask(UpdateEntry entry){
//        return Task.forResult(null);
//    }

    public Task<Void> executePhotosGalleryTask() {
        return Task.forResult(null);
    }

    public void prepareUI() {
//        this.manager.setRegisterHeaderView(IEAHeaderView.getType());
//        this.manager.setRegisterFooterView(IEAFooterView.getType());
    }

    public void postUI() {

    }

    public void postPhotosGallery(int forSectionIndex) {
//        this.manager.setRegisterCellClass(IEAGalleryThumbnailCell.getType(), forSectionIndex);
//        this.manager.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.PhotosGallery), forSectionIndex);
//        this.manager.setSectionItems(CollectionUtil.createList(new IEAGalleryThumbnail(this.thumbnailGalleryCollection, this.galleryViewListener)), forSectionIndex);
        // this.manager.setFooterModelInSection(new SectionPhotoGalleryFooterCellModel(mRestaurantUUID, ReviewType.Review_Restaurant), forSectionIndex, IEAPhotoGalleryFooterCell.getType());
    }

    public void postReviews(int forSectionIndex, String reviewRef, ReviewType type, int limit) {
//        this.manager.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Reviews), forSectionIndex);
        this.manager.setSectionItems(this.reviewsCellModelList, forSectionIndex);

        int otherCount = this.reviewQuery.reviewsCount <= 0 ? 0 : (this.reviewQuery.reviewsCount - limit);
        new RecycleCellFunnel().logOtherReviewsCount(otherCount);
//        this.manager.setFooterModelInSection(new SectionMoreReviewsFooterCellModel(otherCount), forSectionIndex, IEAMoreReviewsFooterCell.getType());
    }

    protected int getEmptyHeaderViewHeight() {
        return DimenUtil.getDisplayWidthPx();
    }


//    protected GalleryThumbnailScrollView.GalleryViewListener galleryViewListener
//            = new GalleryThumbnailScrollView.GalleryViewListener() {
//        @Override
//        public void onGalleryItemClicked(String imageUUID) {
////            PageTitle imageTitle = new PageTitle(imageUUID,null,null);
////            GalleryActivity.showGallery(activity, model.getTitle(), imageTitle,
////                    GalleryFunnel.SOURCE_LINK_PREVIEW);
//        }
//
//        @Override
//        public void onGalleryAddPhotoItemClicked() {
//
//        }
//    };


    public void makeActivePage() {
        this.prepareUI();

        this.executeTask().onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                FragmentTask.this.postUI();
                return null;
            }
        }, Task.UI_THREAD_EXECUTOR).continueWith(new Continuation<Void, Void>() {
            @Override
            public Void then(Task<Void> task) throws Exception {
                if (task.getError() != null) {
                    Exception error = task.getError();
                }
                return null;
            }
        }, Task.UI_THREAD_EXECUTOR);
    }


}
