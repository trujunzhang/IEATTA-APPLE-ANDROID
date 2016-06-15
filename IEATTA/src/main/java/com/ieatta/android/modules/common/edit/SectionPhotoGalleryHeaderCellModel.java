package com.ieatta.android.modules.common.edit;

import com.ieatta.provide.IEAEditKey;
import com.ieatta.android.modules.view.photogallery.IEAPhotoGalleryViewController;
import com.tableview.model.EditBaseCellModel;

public class SectionPhotoGalleryHeaderCellModel extends EditBaseCellModel {
    public IEAPhotoGalleryViewController viewController;

    public SectionPhotoGalleryHeaderCellModel(IEAEditKey editKey, IEAPhotoGalleryViewController viewController) {
        super(editKey);
        this.viewController = viewController;
    }

}
