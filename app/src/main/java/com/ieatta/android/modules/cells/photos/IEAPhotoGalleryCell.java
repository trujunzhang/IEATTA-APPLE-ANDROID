package com.ieatta.android.modules.cells.photos;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.common.edit.PhotoGallery;

public class IEAPhotoGalleryCell extends IEAViewHolder {

    public static CellType getType() {
        return new CellType(IEAPhotoGalleryCell.class, R.layout.photo_gallery_cell);
    }

    private IEAPhotoGalleryCell self = this;
    private RecyclerView collectionView;
    private PhotoGallery model;

    public IEAPhotoGalleryCell(View itemView) {
        super(itemView);

        self.collectionView = (RecyclerView) itemView.findViewById(R.id.section_list);
        self.collectionView.setLayoutManager(new LinearLayoutManager(EnvironmentUtils.sharedInstance.getGlobalContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void render(Object value) {
        self.model = (PhotoGallery) value;
        model.setCollectionView(self.collectionView);
    }
}
