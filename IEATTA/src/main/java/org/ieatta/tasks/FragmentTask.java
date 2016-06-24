package org.ieatta.tasks;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ieatta.android.R;
import com.ieatta.android.modules.cells.IEAReviewsCell;
import com.ieatta.android.modules.cells.headerfooterview.IEAMoreReviewsFooterCell;
import com.ieatta.android.modules.cells.headerfooterview.IEAViewForHeaderInSectionCell;
import com.ieatta.android.modules.cells.photos.IEAGalleryThumbnail;
import com.ieatta.android.modules.common.edit.SectionMoreReviewsFooterCellModel;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.provide.IEAEditKey;
import com.tableview.RecycleViewManager;
import com.tableview.adapter.NSIndexPath;
import com.tableview.adapter.RecyclerOnItemClickListener;
import com.tableview.model.EditBaseCellModel;
import com.tableview.model.IEAReviewsCellModel;
import com.tableview.utils.CollectionUtil;

import org.ieatta.activity.LeadImage;
import org.ieatta.activity.LeadImageCollection;
import org.ieatta.activity.gallery.GalleryCollection;
import org.ieatta.activity.gallery.GalleryItem;
import org.ieatta.activity.gallery.GalleryThumbnailScrollView;
import org.ieatta.activity.gallery.IEAGalleryThumbnailCell;
import org.ieatta.database.models.DBPhoto;
import org.ieatta.database.provide.ReviewType;
import org.ieatta.database.query.ReviewQuery;
import org.ieatta.server.cache.ThumbnailImageUtil;
import org.wikipedia.analytics.DBConvertFunnel;
import org.wikipedia.analytics.RecycleCellFunnel;
import org.wikipedia.util.DimenUtil;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;
import io.realm.Realm;
import io.realm.RealmResults;

public abstract class FragmentTask implements RecyclerOnItemClickListener {
    protected static final String EXTRA_ID = "model_id";

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

    public FragmentTask(Activity activity, RecyclerView recyclerView) {
        this.activity = activity;
        this.manager = new RecycleViewManager(activity.getApplicationContext());
//        Bundle extras = activity.getIntent().getExtras();

        this.setupRecycleView(recyclerView);
    }

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
        this.manager.setRegisterCellClass(IEAGalleryThumbnailCell.getType(), forSectionIndex);
        this.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.PhotosGallery), forSectionIndex);
        this.manager.setSectionItems(CollectionUtil.createList(new IEAGalleryThumbnail(this.thumbnailGalleryCollection, this.galleryViewListener)), forSectionIndex);
//         this.manager.setFooterModelInSection(new SectionPhotoGalleryFooterCellModel(mRestaurantUUID, ReviewType.Review_Restaurant), forSectionIndex, IEAPhotoGalleryFooterCell.getType());
    }

    public void postReviews(int forSectionIndex, String reviewRef, ReviewType type, int limit) {
        this.manager.setRegisterCellClass(IEAReviewsCell.getType(), forSectionIndex);

        this.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Reviews), forSectionIndex);
        this.manager.setSectionItems(this.reviewsCellModelList, forSectionIndex);

        int otherCount = this.reviewQuery.reviewsCount <= 0 ? 0 : (this.reviewQuery.reviewsCount - limit);
        new RecycleCellFunnel().logOtherReviewsCount(otherCount);
        this.manager.setFooterModelInSection(new SectionMoreReviewsFooterCellModel(otherCount), forSectionIndex, IEAMoreReviewsFooterCell.getType());
    }

    protected GalleryThumbnailScrollView.GalleryViewListener galleryViewListener
            = new GalleryThumbnailScrollView.GalleryViewListener() {
        @Override
        public void onGalleryItemClicked(String imageUUID) {
//            PageTitle imageTitle = new PageTitle(imageUUID,null,null);
//            GalleryActivity.showGallery(activity, model.getTitle(), imageTitle,
//                    GalleryFunnel.SOURCE_LINK_PREVIEW);
        }

        @Override
        public void onGalleryAddPhotoItemClicked() {

        }
    };

    public void setupRecycleView(RecyclerView recyclerView) {
        manager.startManagingWithDelegate(recyclerView);
        manager.setOnItemClickListener(this);
    }

    private void reloadTableView() {
        manager.reloadTableView();
    }

    protected void nextPage(Intent intent) {
        if (intent != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
        }
    }

    public void makeActivePage() {
        prepareUI();

        executeTask().onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                FragmentTask.this.postUI();
                FragmentTask.this.reloadTableView();
                return null;
            }
        }, Task.UI_THREAD_EXECUTOR).continueWith(new Continuation<Void, Void>() {
            @Override
            public Void then(Task<Void> task) throws Exception {
                return null;
            }
        }, Task.UI_THREAD_EXECUTOR);
    }


    protected LeadImageCollection toLeadImageCollection(RealmResults<DBPhoto> photos) {
        List<LeadImage> leadImages = new LinkedList<>();
        for (DBPhoto photo : photos) {
            File localFile = ThumbnailImageUtil.sharedInstance.getCacheImageUrl(photo);
            LeadImage item = new LeadImage("file://" + localFile.getAbsolutePath(), photo.getOriginalUrl());
            new DBConvertFunnel().logToLeadImageCollection(item.getLocalUrl(), item.getOnlineUrl());
            leadImages.add(item);
        }
        return new LeadImageCollection(leadImages);
    }


    protected GalleryCollection toGalleryCollection(List<File> files) {
        List<GalleryItem> galleryItems = new LinkedList<>();
        for (File photoFile : files) {
            String uuid = photoFile.getName().split("_")[1];
            String thumbUrl = "file://" + photoFile.getAbsolutePath();
            GalleryItem item = new GalleryItem(uuid, thumbUrl);
            galleryItems.add(item);
        }
        return new GalleryCollection(galleryItems);
    }

    public void appendSectionTitleCell(EditBaseCellModel cell, int forSectionIndex) {
        this.manager.appendSectionTitleCell(cell, forSectionIndex, IEAViewForHeaderInSectionCell.getType());
    }

}
