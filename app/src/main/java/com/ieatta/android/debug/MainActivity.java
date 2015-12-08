package com.ieatta.android.debug;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.ieatta.android.R;
import com.ieatta.android.modules.cells.headerfooterview.IEAPhotoGalleryHeaderCell;
import com.ieatta.android.modules.cells.photos.IEAPhotoGalleryCell;
import com.ieatta.android.modules.common.edit.PhotoGallery;
import com.ieatta.android.modules.common.edit.SectionPhotoGalleryHeaderCellModel;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.tools.CollectionUtils;
import com.ieatta.android.modules.view.photogallery.IEAPhotoGalleryViewController;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Photo;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends IEAPhotoGalleryViewController {
private MainActivity self = this;

    private List<ParseModelAbstract> fetchedPhotos;
    private PhotoGallery photoGallery = new PhotoGallery(IEAEditKey.photo_gallery, self);
    private RecyclerView collectionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        self.fetchedPhotos = new LinkedList<>();
        self.fetchedPhotos.add(new Photo());

//        self.showPhotoGalleryCell();

        self.showPhotoGallery();
    }

    private void showPhotoGallery() {
        self.setContentView(R.layout.photo_gallery_cell);

        self.collectionView = (RecyclerView) self.findViewById(R.id.section_list);

        self.photoGallery.refreshCollection(self.fetchedPhotos);
        self.photoGallery.setCollectionView(self.collectionView);

    }

    private void showPhotoGalleryCell() {


        // Register Cells by class.
        self.setRegisterCellClass(IEAPhotoGalleryCell.getType(), self.getPhotoGallerySectionIndex());

        self.setRegisterHeaderClass(IEAPhotoGalleryHeaderCell.getType());
//        self.setRegisterFooterClass(IEAPhotoGalleryFooterCell.getType());

        // 1. Set photo gallery section title(contains a 'take a photo' icon).
        self.appendSectionTitleCell(new SectionPhotoGalleryHeaderCellModel(IEAEditKey.Section_Title, self), self.getPhotoGallerySectionIndex(), IEAPhotoGalleryHeaderCell.getType());

        // 2. Set empty items for the photo gallery collection cell.
        self.setSectionItems(CollectionUtils.createList(self.photoGallery), self.getPhotoGallerySectionIndex());

        self.photoGallery.refreshCollection(self.fetchedPhotos);

    }

}
