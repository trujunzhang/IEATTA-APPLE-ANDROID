package com.ieatta.android.modules.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.ieatta.android.extensions.storage.DTTableViewManager;

/**
 * Created by djzhang on 12/1/15.
 */
public class IEATableViewControllerAdapter extends RecyclerView.Adapter<IEAViewHolder> {
    private  Context context;
    private DTTableViewManager manager;

    private IEATableViewControllerAdapter self = this;

    public IEATableViewControllerAdapter(DTTableViewManager manager, Context context) {
        self.manager = manager;
        self.context = context;
    }

    @Override
    public IEAViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return self.manager.createViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(IEAViewHolder holder, int position) {
        Object model = self.manager.memoryStorage.getRowModel(position);
        holder.updateWithModel(model);
    }

    @Override
    public int getItemCount() {
        return self.manager.getItemCount();
    }
}
