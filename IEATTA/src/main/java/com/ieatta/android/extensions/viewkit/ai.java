package com.ieatta.android.extensions.viewkit;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;

class ai extends Drawable {
    private final int a;
    private final BitmapShader b;
    private final Matrix c = new Matrix();
    private final Paint d = new Paint();
    private final RectF e = new RectF();
    private final RectF f = new RectF();

    public ai(Bitmap paramBitmap, int paramInt) {
        this.f.set(0.0F, 0.0F, paramBitmap.getWidth(), paramBitmap.getHeight());
        this.a = paramInt;
        this.b = new BitmapShader(paramBitmap, TileMode.CLAMP, TileMode.CLAMP);
        this.b.setLocalMatrix(this.c);
        this.d.setAntiAlias(true);
        this.d.setShader(this.b);
    }

    public void draw(Canvas paramCanvas) {
        paramCanvas.drawRoundRect(this.e, this.a, this.a, this.d);
    }

    public int getOpacity() {
        return -3;
    }

    protected void onBoundsChange(Rect paramRect) {
        super.onBoundsChange(paramRect);
        this.e.set(paramRect);
        this.c.setRectToRect(this.f, this.e, ScaleToFit.CENTER);
        this.b.setLocalMatrix(this.c);
    }

    public void setAlpha(int paramInt) {
        this.d.setAlpha(paramInt);
    }

    public void setColorFilter(ColorFilter paramColorFilter) {
        this.d.setColorFilter(paramColorFilter);
    }
}

/* Location:
 * Qualified Name:     com.yelp.android.ui.widgets.ai
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.6.1-SNAPSHOT
 */