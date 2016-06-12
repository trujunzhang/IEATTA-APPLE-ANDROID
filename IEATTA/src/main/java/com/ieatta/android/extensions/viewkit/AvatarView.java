package com.ieatta.android.extensions.viewkit;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;




import java.util.List;

import bolts.Continuation;
import bolts.Task;

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
    public void configureAvatar(int imageRefId) {
        self.setImageResource(imageRefId);
    }
//
//    public void loadNewPhotoByModel(ParseModelAbstract model, final int placeHolder) {
//        self.configureAvatar(placeHolder);
//
//        new Photo().queryPhotosByModel(model)
//                .onSuccess(new Continuation<List , Void>() {
//                    @Override
//                    public Void then(Task<List > task) throws Exception {
//                        List  taskResult = task.getResult();
//                        ParseModelAbstract first = taskResult.get(0);
//                        if (first != null) {
//                            self.loadNewPhotoByPhoto((Photo) first, placeHolder);
//                        }
//                        return null;
//                    }
//                });
//    }
//
//    public Task loadNewPhotoByPhoto(Photo photo, final int placeHolder) {
//        self.configureAvatar(placeHolder);
//
//        return photo.getThumbanilImage()
//                .onSuccess(new Continuation() {
//                    @Override
//                    public Object then(Task task) throws Exception {
//
//                        final Bitmap bitmap = (Bitmap) task.getResult();
//                        self.setImageBitmap(bitmap);
//
//                        return null;
//                    }
//                }, Task.UI_THREAD_EXECUTOR);
//    }
}
