package com.ieatta.android.modules.cells.photos;

import org.ieatta.activity.gallery.GalleryCollection;
import org.ieatta.activity.gallery.GalleryThumbnailScrollView;

public class IEAGalleryThumbnail {
    private GalleryCollection result;
    public GalleryThumbnailScrollView.GalleryViewListener galleryViewListener;

    public IEAGalleryThumbnail(GalleryCollection result, GalleryThumbnailScrollView.GalleryViewListener galleryViewListener) {
        this.result = result;
        this.galleryViewListener = galleryViewListener;
    }

    public GalleryCollection getResult() {
        return result;
    }

    public void setResult(GalleryCollection result) {
        this.result = result;
    }
}
