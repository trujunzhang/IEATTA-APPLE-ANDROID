package com.ieatta.android.extensions.viewkit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.ieatta.android.R;


public class RoundedImageView extends ImageView {
    private static int cornerRadius;

    public RoundedImageView(Context paramContext) {
        super(paramContext);
        setupView();
    }

    public RoundedImageView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet, 0);
        setupView();
    }

    public RoundedImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        setupView();
    }

    private static Bitmap a(Drawable paramDrawable) {
        if ((paramDrawable instanceof BitmapDrawable))
            return ((BitmapDrawable) paramDrawable).getBitmap();
        int i = paramDrawable.getIntrinsicWidth();
        int j = paramDrawable.getIntrinsicHeight();
        if ((i == -1) || (j == -1))
            return null;
        Bitmap localBitmap = Bitmap.createBitmap(i, j, Config.ARGB_8888);
        Canvas localCanvas = new Canvas(localBitmap);
        paramDrawable.setBounds(0, 0, localCanvas.getWidth(), localCanvas.getHeight());
        paramDrawable.draw(localCanvas);
        return localBitmap;
    }

    private void setupView() {
        cornerRadius = (int) getResources().getDimension(R.dimen.corner_radius);
    }

    public void setImageBitmap(Bitmap paramBitmap) {
        if (paramBitmap == null) {
            super.setImageBitmap(null);
            return;
        }
        super.setImageDrawable(new ai(paramBitmap, cornerRadius));
    }

    public void setImageDrawable(Drawable paramDrawable) {
        if (paramDrawable == null) {
            super.setImageDrawable(null);
            return;
        }
        Bitmap localBitmap = a(paramDrawable);
        if (localBitmap == null) {
            super.setImageDrawable(paramDrawable);
            return;
        }
        super.setImageDrawable(new ai(localBitmap, cornerRadius));
    }

    public void setImageResource(int paramInt) {
        Bitmap localBitmap = BitmapFactory.decodeResource(getResources(), paramInt);
        if (localBitmap == null) {
            localBitmap = a(getResources().getDrawable(paramInt));
            if (localBitmap == null) {
                super.setImageResource(paramInt);
                return;
            }
        }
        setImageBitmap(localBitmap);
    }
}

/* Location:
 * Qualified Name:     com.yelp.android.ui.widgets.RoundedImageView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.6.1-SNAPSHOT
 */