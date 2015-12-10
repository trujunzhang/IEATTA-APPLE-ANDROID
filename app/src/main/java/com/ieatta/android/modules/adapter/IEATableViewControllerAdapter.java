package com.ieatta.android.modules.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ieatta.android.extensions.storage.DTTableViewManager;

/**
 * Created by djzhang on 12/1/15.
 */
public class IEATableViewControllerAdapter extends RecyclerView.Adapter<IEAViewHolder> {
    private IEATableViewControllerAdapter self = this;

    private Context context;
    private DTTableViewManager manager;

    public IEATableViewControllerAdapter(DTTableViewManager manager, Context context) {
        self.manager = manager;
        self.context = context;
    }

    @Override
    public IEAViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return self.manager.createViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(IEAViewHolder holder, int position) {
        Object model = self.manager.memoryStorage.getRowModel(position);
        holder.render(model);
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    Toast.makeText(self.context, "#" + position + " - " + " (Long click)", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(self.context, "#" + position + " - ", Toast.LENGTH_SHORT).show();
                }
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
