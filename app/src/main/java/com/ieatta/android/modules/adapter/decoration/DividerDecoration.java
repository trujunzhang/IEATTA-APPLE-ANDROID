package com.ieatta.android.modules.adapter.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ieatta.android.modules.adapter.enums.ViewHolderType;

/**
 * ItemDecoration implementation that applies and inset margin
 * around each child of the RecyclerView. It also draws item dividers
 * that are expected from a vertical list implementation, such as
 * ListView.
 */
public class DividerDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = {android.R.attr.listDivider};

    private Drawable mDivider;
    private int mInsets;

    public DividerDecoration(Context context) {
        TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();

//        mInsets = 8;//context.getResources().getDimensionPixelSize(R.dimen.card_insets);

        mInsets = 0;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawVertical(c, parent);
    }

    /**
     * Draw dividers underneath each child view
     */
    public void drawVertical(Canvas c, RecyclerView parent) {

        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = this.getLeft(parent, child, i, childCount);

            final int top = child.getBottom() + params.bottomMargin + mInsets;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private int getLeft(RecyclerView parent, View child, int position, int childCount) {
        int tag = (int) child.getTag();
//        Log.v("decoration","tag: "+tag);
        View nextChild = null;
        if (position < childCount - 1) {
            nextChild = parent.getChildAt(position + 1);
            int nextTag = (int) nextChild.getTag();
            if(nextTag == ViewHolderType.cell.ordinal()) {
                if (((tag == ViewHolderType.cell.ordinal()) || (tag == ViewHolderType.special.ordinal()))) {
                    return 120;
                }
            }
        }

        return 0;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //We can supply forced insets for each item view here in the Rect
        outRect.set(mInsets, mInsets, mInsets, mInsets);
    }
}
