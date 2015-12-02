package com.ieatta.android.modules.common.edit;

import com.ieatta.android.modules.view.photogallery.IEAPhotoGalleryViewController;

public class SectionPhotoGalleryFooterCellModel extends EditBaseCellModel {
    IEAPhotoGalleryViewController viewController;
    int photosCount = 0;

    public SectionPhotoGalleryFooterCellModel(IEAEditKey editKey,int photosCount , IEAPhotoGalleryViewController viewController) {
        super(editKey);
        this.photosCount = photosCount;
        this.viewController = viewController;
    }

}
