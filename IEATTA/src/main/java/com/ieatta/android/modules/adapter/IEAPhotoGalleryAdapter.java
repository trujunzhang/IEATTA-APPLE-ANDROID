package com.ieatta.android.modules.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ieatta.android.modules.cells.photos.IEAPhotosCell;


import java.util.List;

public class IEAPhotoGalleryAdapter extends RecyclerView.Adapter<IEAPhotosCell> {
    private IEAPhotoGalleryAdapter self = this;

    private List  fetchedPhotos;
    private Context context;

    public IEAPhotoGalleryAdapter(Context context, List  fetchedPhotos) {
        self.context = context;
        self.fetchedPhotos = fetchedPhotos;
    }

    @Override
    public IEAPhotosCell onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(self.context).inflate(IEAPhotosCell.getType().layoutResId, parent, false);
        return new IEAPhotosCell(view);
    }

    @Override
    public void onBindViewHolder(IEAPhotosCell holder, int position) {
        holder.render(self.fetchedPhotos.get(position));
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
        int itemCount = self.fetchedPhotos.size();
        return itemCount;
    }
}
