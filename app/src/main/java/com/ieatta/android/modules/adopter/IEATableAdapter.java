package com.ieatta.android.modules.adopter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.DTTableViewManager;
import com.ieatta.android.modules.cells.IEANearRestaurantMoreCell;

/**
 * Created by djzhang on 12/1/15.
 */
public class IEATableAdapter extends RecyclerView.Adapter<IEAViewHolder> {
    private  Context context;
    private DTTableViewManager manager;

    private IEATableAdapter self = this;

    public IEATableAdapter(DTTableViewManager manager, Context context) {
        self.manager = manager;
        self.context = context;
    }

    @Override
    public IEAViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        IEANearRestaurantMoreCell holder = new IEANearRestaurantMoreCell(LayoutInflater.from(
                self.context).inflate(R.layout.near_restaurant_more_cell, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(IEAViewHolder holder, int position) {
        Object model = self.manager.getModel(position);
        holder.updateWithModel(model);
    }

    @Override
    public int getItemCount() {
        return self.manager.getItemCount();
    }
}
