package com.ieatta.android.debug;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.android.R;
import com.ieatta.android.modules.IEAReviewsInDetailTableViewController;
import com.ieatta.android.modules.adapter.IEAPhotoGalleryAdapter;
import com.ieatta.android.modules.cells.headerfooterview.IEAPhotoGalleryFooterCell;
import com.ieatta.android.modules.cells.headerfooterview.IEAPhotoGalleryHeaderCell;
import com.ieatta.android.modules.cells.photos.IEAPhotoGalleryCell;
import com.ieatta.android.modules.common.edit.PhotoGallery;
import com.ieatta.android.modules.common.edit.SectionPhotoGalleryFooterCellModel;
import com.ieatta.android.modules.common.edit.SectionPhotoGalleryHeaderCellModel;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.tools.CollectionUtils;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Restaurant;
import com.ieatta.com.parse.models.enums.PQueryModelType;

import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

public class MainActivity extends IEAReviewsInDetailTableViewController {
    private MainActivity self = this;

    private PhotoGallery photoGallery = new PhotoGallery(IEAEditKey.photo_gallery, self);

    private RecyclerView collectionView;
    public IEAPhotoGalleryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EnvironmentUtils.sharedInstance.registerCurrentActivity(self);

        List<String> list = new LinkedList<>();
        list.add("6825D75B-E4D5-4386-AA62-3D022B7A50B5");
        list.add("1CE562A4-A978-4B75-9B7B-2F3CF9F42A04");
        new Restaurant().queryParseModels(PQueryModelType.Restaurant, list).onSuccess(new Continuation<List<ParseModelAbstract>, Object>() {
            @Override
            public Object then(Task<List<ParseModelAbstract>> task) throws Exception {
                List<ParseModelAbstract> result = task.getResult();

                int count = result.size();

                return null;
            }
        });

//        self.setupPhotoGallerySection();

//        self.showReviewSection();

//        self.showContentView();
    }

    private void showReviewSection() {

    }

    private void setupPhotoGallerySection() {
        self.fetchedPhotos = new LinkedList<>();
//        self.fetchedPhotos.add(ActivityModelDebug.getPhoto());

        self.showPhotoGalleryCell();

//        self.showPhotoGallery();
    }

    private void showContentView() {
        self.setContentView(R.layout.read_reviews_header);
//        self.configureReviewsSection(self.fetchedReviews);
    }

    protected int getReviewsSectionIndex() {
        return 0;
    }

    private void showPhotoGallery() {
        self.setContentView(R.layout.photo_gallery_cell);

        self.collectionView = (RecyclerView) self.findViewById(R.id.section_list);
        self.collectionView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        self.adapter = new IEAPhotoGalleryAdapter(self, self.fetchedPhotos);

        self.collectionView.setAdapter(self.adapter);

        self.adapter.notifyDataSetChanged();
    }

    @Override
    protected int getPhotoGallerySectionIndex() {
        return 0;
    }

    private void showPhotoGalleryCell() {
        // Register Cells by class.
        self.setRegisterCellClass(IEAPhotoGalleryCell.getType(), self.getPhotoGallerySectionIndex());

//        self.setRegisterHeaderClass(IEAPhotoGalleryHeaderCell.getType());
//        self.setRegisterFooterClass(IEAPhotoGalleryFooterCell.getType());

        // 1. Set photo gallery section title(contains a 'take a photo' icon).
        self.appendSectionTitleCell(new SectionPhotoGalleryHeaderCellModel(IEAEditKey.Section_Title, self), self.getPhotoGallerySectionIndex(), IEAPhotoGalleryHeaderCell.getType());
        self.setFooterModelInSection(new SectionPhotoGalleryFooterCellModel(IEAEditKey.Section_Title, self.getPhotoGalleryCount(), self), self.getPhotoGallerySectionIndex(), IEAPhotoGalleryFooterCell.getType());

        // 2. Set empty items for the photo gallery collection cell.
        self.setSectionItems(CollectionUtils.createList(self.photoGallery), self.getPhotoGallerySectionIndex());

        self.photoGallery.refreshCollection(self.fetchedPhotos);

    }

}
