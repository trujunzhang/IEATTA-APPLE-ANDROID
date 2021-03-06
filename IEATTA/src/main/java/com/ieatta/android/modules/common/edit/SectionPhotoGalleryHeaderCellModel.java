package com.ieatta.android.modules.common.edit;

import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.view.photogallery.IEAPhotoGalleryViewController;

public class SectionPhotoGalleryHeaderCellModel extends EditBaseCellModel {
    public IEAPhotoGalleryViewController viewController;

    public SectionPhotoGalleryHeaderCellModel(IEAEditKey editKey, IEAPhotoGalleryViewController viewController) {
        super(editKey);
        this.viewController = viewController;
    }

}
