package com.ieatta.android.modules.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ieatta.android.extensions.storage.CellType;
import com.ieatta.android.extensions.viewkit.ModelTransfer;

/**
 * Created by djzhang on 12/1/15.
 */
public abstract class IEAViewHolder extends RecyclerView.ViewHolder implements ModelTransfer,View.OnClickListener, View.OnLongClickListener {
    private ItemClickListener clickListener;


    public IEAViewHolder(View itemView) {
        super(itemView);

        itemView.setTag(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        clickListener.onClick(view, getPosition(), false);
    }

    @Override
    public boolean onLongClick(View view) {
        clickListener.onClick(view, getPosition(), true);
        return true;
    }
}
