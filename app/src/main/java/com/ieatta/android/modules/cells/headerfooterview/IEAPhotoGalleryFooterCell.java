package com.ieatta.android.modules.cells.headerfooterview;

import com.ieatta.android.modules.adapter.IEAViewHolder;
import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;

/**
 * Created by djzhang on 12/2/15.
 */
public class IEAPhotoGalleryFooterCell  extends IEAViewHolder {
    public static CellType  getType() {
        return new CellType(IEAPhotoGalleryFooterCell.class, R.layout.choice_people_header_cell);
    }


    private IEAPhotoGalleryFooterCell self = this;

    private TextView titleView;

    public IEAPhotoGalleryFooterCell(View itemView) {
        super(itemView);

        self.titleView = (TextView) itemView.findViewById(R.id.headerTextView);
    }

    @Override
    public void updateWithModel(Object model) {
        self.titleView.setText(R.string.Select_Person);

    }
}