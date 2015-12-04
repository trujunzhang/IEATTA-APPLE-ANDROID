package com.ieatta.android.modules.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ieatta.android.extensions.storage.CellType;
import com.ieatta.android.extensions.viewkit.ModelTransfer;

/**
 * Created by djzhang on 12/1/15.
 */
public abstract class IEAViewHolder extends RecyclerView.ViewHolder implements ModelTransfer {


    public IEAViewHolder(View itemView) {
        super(itemView);
    }


}
