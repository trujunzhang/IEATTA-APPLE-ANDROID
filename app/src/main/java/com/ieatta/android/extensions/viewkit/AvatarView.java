package com.ieatta.android.extensions.viewkit;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;

import com.ieatta.android.cache.IEACache;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Photo;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 12/1/15.
 */
public class AvatarView extends RoundedImageView {
    private AvatarView self = this;

    private Context context;

    public AvatarView(Context context) {
        super(context);
        this.context = context;
        this.configureView();
    }

    public AvatarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.configureView();
    }

    public AvatarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        this.configureView();
    }

    private void configureView() {
        // Setup avatarBackgroundView
    }

    // MARK: Set circle effect.
    void setCircle(int placeHolder) {
        self.configureAvatar(placeHolder);
    }

    // MARK: Set rect effect.
    void setCornerRadius(int value, int placeHolder) {
//        self.cornerRadius(value)
//        self.avatarImageView.cornerRadius(value)

        self.configureAvatar(placeHolder);
    }

    // MARK: Setup image.
    void configureAvatar(int imageRefId) {
        self.setImageResource(imageRefId);
    }

    public Task loadNewPhotoByModel(ParseModelAbstract model, int placeHolder) {
        // Cache photo point.
        String photoPoint = IEACache.sharedInstance.photoPoint(model);

        if (photoPoint == null || photoPoint.isEmpty() == true) {
            self.configureAvatar(placeHolder);
        } else {
            self.loadNewPhotoByPhoto(Photo.getInstanceFromPhotoPoint(photoPoint), placeHolder);
        }

        return Task.forResult(true);
    }

    public Task loadNewPhotoByPhoto(Photo photo, final int placeHolder) {
        return photo.getThumbanilImage().onSuccess(new Continuation() {
            @Override
            public Object then(Task task) throws Exception {
                Object object = task;
                Bitmap bitmap = (Bitmap) task.getResult();
//                self.displayImage(task,placeHolder: placeHolder)
                self.setImageBitmap(bitmap);
                return null;
            }
        }).continueWith(new Continuation() {
            @Override
            public Object then(Task task) throws Exception {
                if (task.isFaulted()) {
                    self.configureAvatar(placeHolder);
                    return null;
                }
                return null;
            }
        });
    }
}
