package com.ieatta.android.modules.adopter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.ieatta.android.extensions.storage.DTTableViewManager;

/**
 * Created by djzhang on 12/1/15.
 */
public class IEATableAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private DTTableViewManager manager;

    private IEATableAdapter self = this;

    public IEATableAdapter(DTTableViewManager manager) {
        self.manager = manager;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
