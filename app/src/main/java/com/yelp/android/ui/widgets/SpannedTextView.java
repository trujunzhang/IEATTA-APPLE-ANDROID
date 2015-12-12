package com.yelp.android.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.TextView;

public class SpannedTextView extends TextView
{

    public SpannedTextView(Context paramContext)
    {
        this(paramContext, null);
    }

    public SpannedTextView(Context paramContext, AttributeSet paramAttributeSet)
    {
        this(paramContext, paramAttributeSet, 0);
    }

    public SpannedTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
        refreshDrawableState();
    }

    public boolean performClick()
    {
        return super.performClick();
    }

    public void setClickable(boolean paramBoolean)
    {
        if (paramBoolean != isClickable())
            refreshDrawableState();
        super.setClickable(paramBoolean);
    }

//    public void setLeft(boolean paramBoolean)
//    {
//        this.a.setLeft(paramBoolean);
//    }
//
//    public void setMiddle(boolean paramBoolean)
//    {
//        this.a.setMiddle(paramBoolean);
//    }
//
//    public void setOnCheckedChangeListener(n paramn)
//    {
//        this.a.setOnCheckedChangeListener(paramn);
//        refreshDrawableState();
//    }
//
//    public void setRight(boolean paramBoolean)
//    {
//        this.a.setRight(paramBoolean);
//    }
//
//    public void toggle()
//    {
//        this.a.c(this);
//    }
}

/* Location:
 * Qualified Name:     com.yelp.android.ui.widgets.SpannedTextView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.6.1-SNAPSHOT
 */