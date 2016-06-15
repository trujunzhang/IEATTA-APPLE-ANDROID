package com.ieatta.android.modules.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ieatta.android.extensions.storage.DTTableViewManager;
import com.ieatta.android.extensions.storage.models.RowModel;

import com.tableview.adapter.IEAViewHolder;

public class IEATableViewControllerAdapter extends RecyclerView.Adapter<IEAViewHolder> {
    private Context context;
    private DTTableViewManager manager;
    private RecyclerItemClickListener itemClickListener;

    public IEATableViewControllerAdapter(Context context, DTTableViewManager manager, RecyclerItemClickListener itemClickListener) {
        this.context = context;
        this.manager = manager;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public IEAViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return this.manager.createViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(final IEAViewHolder holder, int position) {
        Object model = this.manager.memoryStorage.getRowModel(position);
        holder.render(model);
//        holder.setClickListener(new ItemClickListener() {
//            @Override
//            public void onClick(View view, int position, boolean isLongClick) {
//                RowModel item = IEATableViewControllerAdapter.this.manager.memoryStorage.getItem(position);
//                IEATableViewControllerAdapter.this.itemClickListener.onItemClick(view, item.indexPath, item.model, position, isLongClick);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        int itemCount = this.manager.getItemCount();
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        int itemViewType = this.manager.memoryStorage.getItemViewType(position);
        return itemViewType;
    }
}
