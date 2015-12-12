package com.yelp.android.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.RelativeLayout;

public class SpannableRelativeLayout extends RelativeLayout

{
    

    public SpannableRelativeLayout(Context paramContext)
    {
        this(paramContext, null);
    }

    public SpannableRelativeLayout(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);

    }

    public SpannableRelativeLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
    }

//    public boolean isChecked()
//    {
//
//    }
//
//    protected int[] onCreateDrawableState(int paramInt)
//    {
//        int[] arrayOfInt2;
//        if (this.mUtil == null)
//            arrayOfInt2 = super.onCreateDrawableState(paramInt);
//        int[] arrayOfInt1;
//        do
//        {
//            return arrayOfInt2;
//            arrayOfInt1 = this.mUtil.a(this);
//            arrayOfInt2 = super.onCreateDrawableState(paramInt + arrayOfInt1.length);
//        }
//        while (arrayOfInt1.length <= 0);
//        mergeDrawableStates(arrayOfInt2, arrayOfInt1);
//        return arrayOfInt2;
//    }
//
//    public boolean performClick()
//    {
//        this.mUtil.b(this);
//        return super.performClick();
//    }
//
//    public void setChecked(boolean paramBoolean)
//    {
//        this.mUtil.a(this, paramBoolean);
//    }
//
//    public void setClickable(boolean paramBoolean)
//    {
//        if (paramBoolean != isClickable())
//            refreshDrawableState();
//        super.setClickable(paramBoolean);
//    }
//
//    public void setLeft(boolean paramBoolean)
//    {
//        this.mUtil.setLeft(paramBoolean);
//    }
//
//    public void setMiddle(boolean paramBoolean)
//    {
//        this.mUtil.setMiddle(paramBoolean);
//    }
//
//    public void setOnCheckedChangeListener(n paramn)
//    {
//        this.mUtil.setOnCheckedChangeListener(paramn);
//        refreshDrawableState();
//    }
//
//    public void setRight(boolean paramBoolean)
//    {
//        this.mUtil.setRight(paramBoolean);
//    }
//
//    public void toggle()
//    {
//        this.mUtil.c(this);
//    }
}

/* Location:
 * Qualified Name:     com.yelp.android.ui.widgets.SpannableRelativeLayout
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.6.1-SNAPSHOT
 */