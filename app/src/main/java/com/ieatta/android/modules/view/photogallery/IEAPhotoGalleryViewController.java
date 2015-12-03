package com.ieatta.android.modules.view.photogallery;

import com.ieatta.android.modules.IEASplitDetailViewController;

import java.util.LinkedList;

import bolts.Task;

/**
 * Created by djzhang on 12/1/15.
 */
public class IEAPhotoGalleryViewController extends IEASplitDetailViewController {

    private IEAPhotoGalleryViewController self = this;

    protected Task fetchedPhotosTask = Task.forResult(new LinkedList<>());

}
