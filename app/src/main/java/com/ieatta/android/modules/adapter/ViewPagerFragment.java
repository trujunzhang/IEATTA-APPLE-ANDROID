/*
Copyright 2014 David Morrissey

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.ieatta.android.modules.adapter;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.badoo.mobile.util.WeakHandler;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.ieatta.android.R;
import com.ieatta.android.cache.IntentCache;
import com.ieatta.com.parse.models.Photo;
import com.ieatta.com.parse.tools.Thumbnail;
import com.ieatta.com.parse.utils.cache.CacheImageUtils;
import com.ieatta.com.parse.utils.cache.OriginalImageUtils;
import com.ieatta.com.parse.utils.cache.ThumbnailImageUtils;

import bolts.Continuation;
import bolts.Task;

public class ViewPagerFragment extends Fragment {
    private ViewPagerFragment self = this;
    private static final String BUNDLE_ASSET = "asset";

    private int asset = -1;
    private SubsamplingScaleImageView imageView;

    private WeakHandler mHandler = new WeakHandler();
    ; // We still need at least one hard reference to WeakHandler

    public ViewPagerFragment() {
    }

    public void setAsset(int asset) {
        this.asset = asset;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_pager_page, container, false);

        if (savedInstanceState != null) {
            if (asset == -1 && savedInstanceState.containsKey(BUNDLE_ASSET)) {
                asset = savedInstanceState.getInt(BUNDLE_ASSET);
            }
        }

        if (asset != -1) {
            self.imageView = (SubsamplingScaleImageView) rootView.findViewById(R.id.imageView);
            Photo photo = (Photo) IntentCache.sharedInstance.photoGalleryItem.get(this.asset);
            self.showImage(photo);
        }

        return rootView;
    }

    /// How to store a taken photo to decrease client storage.
    ///   1.  When taken a photo, save it as original and thumbnail formats.
    ///   2.1 When pushing to server, push original and thumbnail images to server.
    ///   2.2 When pushed successfully, delete offline original image.
    ///   3.  When pulling from server, just download a thumbnail image from server.
//    private void showImage(final Photo photo) {
//        Bitmap image = CacheImageUtils.sharedInstance.getTakenPhoto(photo);
//        if (image != null) {
//            self.imageView.setImage(ImageSource.bitmap(image));
//            return;
//        }
//
//        image = OriginalImageUtils.sharedInstance.getTakenPhoto(photo);
//        if (image != null) {
//            self.imageView.setImage(ImageSource.bitmap(image));
//        } else {
//            Bitmap bitmap = Thumbnail.create(ThumbnailImageUtils.sharedInstance.getTakenPhoto(photo)).zoom(720, 720).getBitmap();
//            self.imageView.setImage(ImageSource.bitmap(bitmap));
//        }
//
//        photo.downloadCacheImageFromServer()
//                .onSuccess(new Continuation<Bitmap, Object>() {
//                    @Override
//                    public Object then(final Task<Bitmap> task) throws Exception {
//                        mHandler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                self.imageView.setImage(ImageSource.bitmap(CacheImageUtils.sharedInstance.getTakenPhoto(photo)));
//                            }
//                        }, 1);
//                        return null;
//                    }
//                });
//    }

    private void showImage(final Photo photo) {
        Bitmap image = CacheImageUtils.sharedInstance.getTakenPhoto(photo);
        if (image != null) {
            self.showImage(image);
            return;
        }

        image = OriginalImageUtils.sharedInstance.getTakenPhoto(photo);
        if (image != null) {
            self.showImage(image);
        } else {
            self.showImage(ThumbnailImageUtils.sharedInstance.getTakenPhoto(photo));
        }

        photo.downloadCacheImageFromServer()
                .onSuccess(new Continuation<Bitmap, Object>() {
                    @Override
                    public Object then(final Task<Bitmap> task) throws Exception {
                        self.showImage(CacheImageUtils.sharedInstance.getTakenPhoto(photo));
                        return null;
                    }
                });
    }

    private void showImage(Bitmap image) {
        self.setImageView(image).onSuccess(new Continuation<Bitmap, Object>() {
            @Override
            public Object then(final Task<Bitmap> task) throws Exception {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        self.imageView.setImage(ImageSource.bitmap(task.getResult()));
                    }
                }, 1);
                return null;
            }
        });

    }

    private Task<Bitmap> setImageView(Bitmap image) {
        Bitmap bitmap = Thumbnail.create(image).zoom(1024, 1024).getBitmap();

        return Task.forResult(bitmap);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        View rootView = getView();
        if (rootView != null) {
            outState.putInt(BUNDLE_ASSET, asset);
        }
    }

}
