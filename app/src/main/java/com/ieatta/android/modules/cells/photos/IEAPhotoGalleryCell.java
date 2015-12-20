package com.ieatta.android.modules.cells.photos;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.DTTableViewManager;
import com.ieatta.android.extensions.storage.TableViewConfiguration;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.adapter.NSIndexPath;
import com.ieatta.android.modules.adapter.RecyclerItemClickListener;
import com.ieatta.android.modules.adapter.decoration.DividerDecoration;
import com.ieatta.android.modules.common.edit.PhotoGallery;

public class IEAPhotoGalleryCell extends IEAViewHolder {

    public static CellType getType() {
        return new CellType(IEAPhotoGalleryCell.class, R.layout.photo_gallery_cell);
    }

    private IEAPhotoGalleryCell self = this;
    private DTTableViewManager manager = null;
    private RecyclerView collectionView;

    private PhotoGallery model;

    public IEAPhotoGalleryCell(View itemView) {
        super(itemView);

        self.collectionView = (RecyclerView) itemView.findViewById(R.id.section_list);

        Context context = EnvironmentUtils.sharedInstance.getGlobalContext();
        TableViewConfiguration config =
                new TableViewConfiguration.Builder(context)
                        .setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false))
                        .setOnItemClickListener(new RecyclerItemClickListener() {
                            @Override
                            public void onItemClick(View view, NSIndexPath indexPath, Object model, int position, boolean isLongClick) {
                                self.model.viewController.didSelectItemAtIndexPathOnCollectionView(new NSIndexPath(0,position));
                            }
                        })
                        .setDebugInfo("Activity_Table_View")
                        .build();
        self.manager = new DTTableViewManager(config);
        self.startManagingWithDelegate(self.manager);

        self.manager.registerCellClass(IEAPhotosCell.getType(),0);
    }

    protected void startManagingWithDelegate(DTTableViewManager manager) {
        self.collectionView.setAdapter(manager.getAdapter());
        self.collectionView.setLayoutManager(manager.configuration.builder.manager);
    }

    @Override
    public void render(Object value) {
        self.model = (PhotoGallery) value;
        model.setCollectionView(self.collectionView,self.manager);
    }
}
