package com.ieatta.android.modules.common.edit;

import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.view.photogallery.IEAPhotoGalleryViewController;

public class SectionPhotoGalleryFooterCellModel extends EditBaseCellModel {
    public IEAPhotoGalleryViewController viewController;
    public int photosCount = 0;

    public SectionPhotoGalleryFooterCellModel(IEAEditKey editKey,int photosCount , IEAPhotoGalleryViewController viewController) {
        super(editKey);
        this.photosCount = photosCount;
        this.viewController = viewController;
    }

}
