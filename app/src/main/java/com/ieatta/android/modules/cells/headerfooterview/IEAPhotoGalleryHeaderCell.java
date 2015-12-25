package com.ieatta.android.modules.cells.headerfooterview;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.adapter.enums.ViewHolderType;
import com.ieatta.android.modules.common.edit.SectionPhotoGalleryHeaderCellModel;

/**
 * Created by djzhang on 12/2/15.
 */
public class IEAPhotoGalleryHeaderCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEAPhotoGalleryHeaderCell.class, R.layout.photo_gallery_header_cell);
    }

    @Override
    protected boolean shouldClickItem() {
        return false;
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.None;
    }

    private IEAPhotoGalleryHeaderCell self = this;

    private SectionPhotoGalleryHeaderCellModel model;

    private TextView titleView;

    public IEAPhotoGalleryHeaderCell(View itemView) {
        super(itemView);

        self.titleView = (TextView) itemView.findViewById(R.id.headerTextView);

        itemView.findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                self.model.viewController.takeAPhotoButtonTapped();
            }
        });
    }

    @Override
    public void render(Object value) {
        self.model = (SectionPhotoGalleryHeaderCellModel) value;

        self.titleView.setText(R.string.PhotosGallery);

    }
}