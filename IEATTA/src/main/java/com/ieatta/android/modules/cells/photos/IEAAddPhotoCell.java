package com.ieatta.android.modules.cells.photos;

import android.view.View;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.common.edit.AddPhotoModel;

public class IEAAddPhotoCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEAAddPhotoCell.class, R.layout.add_photo_cell);
    }

    private IEAAddPhotoCell self = this;

    public IEAAddPhotoCell(View itemView) {
        super(itemView);
    }

    @Override
    public void render(Object value) {
        AddPhotoModel model = (AddPhotoModel) value;

    }
}
