package com.ieatta.android.modules.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ieatta.android.extensions.storage.DTTableViewManager;

/**
 * Created by djzhang on 12/1/15.
 */
public class IEATableViewControllerAdapter extends RecyclerView.Adapter<IEAViewHolder> {
    private IEATableViewControllerAdapter self = this;

    private Context context;
    private DTTableViewManager manager;
    private RecyclerItemClickListener itemClickListener;

    public IEATableViewControllerAdapter(Context context, DTTableViewManager manager, RecyclerItemClickListener itemClickListener) {
        self.context = context;
        self.manager = manager;
        self.itemClickListener = itemClickListener;
    }

    @Override
    public IEAViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return self.manager.createViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(final IEAViewHolder holder, int position) {
        Object model = self.manager.memoryStorage.getRowModel(position);
        holder.render(model);
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Object item = self.manager.memoryStorage.getItem(position);
                self.itemClickListener.onItemClick(view,item,position, null, isLongClick);
            }
        });
    }

    @Override
    public int getItemCount() {
        int itemCount = self.manager.getItemCount();
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        int itemViewType = self.manager.memoryStorage.getItemViewType(position);
        return itemViewType;
    }
}
