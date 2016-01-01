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
    private void showImage(final Photo photo) {

        Task.forResult(true)
                .onSuccessTask(new Continuation<Boolean, Task<Boolean>>() {
                    @Override
                    public Task<Boolean> then(Task<Boolean> task) throws Exception {
                        Bitmap image = CacheImageUtils.sharedInstance.getTakenPhoto(photo);
                        return self.showImage(image, false);
                    }
                }).onSuccessTask(new Continuation<Boolean, Task<Boolean>>() {
            @Override
            public Task<Boolean> then(Task<Boolean> task) throws Exception {
                Bitmap image = OriginalImageUtils.sharedInstance.getTakenPhoto(photo);
                return self.showImage(image, false);
            }
        }).onSuccessTask(new Continuation<Boolean, Task<Boolean>>() {
            @Override
            public Task<Boolean> then(Task<Boolean> task) throws Exception {
                Bitmap image = ThumbnailImageUtils.sharedInstance.getTakenPhoto(photo);
                return self.showImage(image, true);
            }
        }).onSuccessTask(new Continuation<Boolean, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Boolean> task) throws Exception {
                return photo.downloadCacheImageFromServer();
            }
        }).onSuccess(new Continuation<Void, Object>() {
            @Override
            public Object then(final Task<Void> task) throws Exception {
                Bitmap image = CacheImageUtils.sharedInstance.getTakenPhoto(photo);
                return self.showImage(image, false);
            }
        });
    }

    private Task<Boolean> showImage(Bitmap image, final boolean shouldNextTask) {
        if (image == null) {
            return Task.forResult(true);
        }

        return self.convertImageView(image).onSuccessTask(new Continuation<Bitmap, Task<Boolean>>() {
            @Override
            public Task<Boolean> then(final Task<Bitmap> task) throws Exception {

                self.imageView.setImage(ImageSource.bitmap(task.getResult()));
                if (shouldNextTask == true) {
                    return Task.forResult(true);
                }

                return Task.forError(new Exception("Already found it"));
            }
        }, Task.UI_THREAD_EXECUTOR);
    }

    private Task<Bitmap> convertImageView(Bitmap image) {
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
