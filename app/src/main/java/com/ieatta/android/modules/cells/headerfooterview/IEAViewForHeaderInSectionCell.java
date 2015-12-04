package com.ieatta.android.modules.cells.headerfooterview;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;

/**
 * Created by djzhang on 12/2/15.
 */
public class IEAViewForHeaderInSectionCell extends IEAViewHolder {
    public static CellType  getType() {
        return new CellType(IEAViewForHeaderInSectionCell.class, R.layout.view_for_header_in_section_cell);
    }

    private IEAViewForHeaderInSectionCell self = this;

    private TextView titleLabel;

    public IEAViewForHeaderInSectionCell(View itemView) {
        super(itemView);

        self.titleLabel = (TextView) itemView.findViewById(R.id.headerTextView);
    }

    @Override
    public void updateWithModel(Object model) {
        SectionTitleCellModel more  = (SectionTitleCellModel) model;
        self.titleLabel.setText(more.titleResId);
    }
}