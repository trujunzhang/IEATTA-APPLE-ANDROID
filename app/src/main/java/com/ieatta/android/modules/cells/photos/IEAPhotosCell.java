package com.ieatta.android.modules.cells.photos;

import android.view.View;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.extensions.viewkit.AvatarView;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.com.parse.models.Photo;

public class IEAPhotosCell extends IEAViewHolder {

    public static CellType getType() {
        return new CellType(IEAPhotosCell.class, R.layout.photo_cell);
    }

    private IEAPhotosCell self = this;

    private AvatarView avatarView;

    public IEAPhotosCell(View itemView) {
        super(itemView);
        self.avatarView = (AvatarView) itemView.findViewById(R.id.avatarView);
    }

    @Override
    public void render(Object value) {
        Photo model  = (Photo) value;
        self.avatarView.loadNewPhotoByPhoto(model,R.drawable.placeholder_photo);
    }
}
