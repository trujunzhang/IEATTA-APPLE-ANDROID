package com.ieatta.android.extensions.viewkit;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;

import com.ieatta.com.parse.models.Photo;

/**
 * Created by djzhang on 12/1/15.
 */
public class AvatarView extends RoundedImageView{

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
//        this.setBorderWidth(0.0f);
    }

}
