package com.yelp.android.ui.activities.camera;

import android.content.Context;
import android.hardware.Camera.Size;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class YelpSurfaceView extends SurfaceView
{
//  private CameraWrangler a;

  public YelpSurfaceView(Context paramContext)
  {
    super(paramContext);
  }

  public YelpSurfaceView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public YelpSurfaceView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  private float a(int paramInt1, int paramInt2, Size paramSize)
  {
    return Math.min(paramInt1 / paramSize.width, paramInt2 / paramSize.height);
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
//    if ((this.a == null) || (this.a.b() == null))
//    {
//      super.onMeasure(paramInt1, paramInt2);
//      return;
//    }
//    Size localSize = this.a.b();
//    float f = a(MeasureSpec.getSize(paramInt1), MeasureSpec.getSize(paramInt2), localSize);
//    setMeasuredDimension(Math.round(f * localSize.width), Math.round(f * localSize.height));
  }

//  public void setCameraWrangler(CameraWrangler paramCameraWrangler)
//  {
//    this.a = paramCameraWrangler;
//    requestLayout();
//  }
}

/* Location:
 * Qualified Name:     com.yelp.android.ui.activities.camera.YelpSurfaceView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.6.1-SNAPSHOT
 */