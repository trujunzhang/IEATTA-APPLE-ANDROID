package com.ieatta.android.modules.adapter;

import android.view.View;

/**
 * Created by djzhang on 12/10/15.
 */
public interface RecyclerItemClickListener {
    void onItemClick(View view, NSIndexPath indexPath, Object model, int position, boolean isLongClick);
}
