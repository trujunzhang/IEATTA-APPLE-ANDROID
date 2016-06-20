package com.ieatta.android.extensions.viewkit;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;


import com.facebook.drawee.view.SimpleDraweeView;

import org.ieatta.server.cache.ThumbnailImageUtil;
import org.wikipedia.util.log.L;
import org.wikipedia.views.ViewUtil;

import java.io.File;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

public class AvatarView extends SimpleDraweeView {
    public AvatarView(Context context) {
        super(context);
    }

    public AvatarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AvatarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AvatarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void loadNewPhotoByModel(String uuid) {
        ThumbnailImageUtil.sharedInstance.getImagesListTask(uuid).onSuccess(new Continuation<List<File>, Void>() {
            @Override
            public Void then(Task<List<File>> task) throws Exception {
                List<File> files = task.getResult();
                File first = files.get(0);
                String path = first.getAbsolutePath();
                L.d("cached path of the photo: " + path);
                String url = String.format("file://%s", path);
                ViewUtil.loadImageUrlInto(AvatarView.this, url);
                return null;
            }
        });
    }

}
