package com.ieatta.android.modules.cells.headerfooterview;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.tableview.adapter.IEAViewHolder;
import com.tableview.adapter.enums.ViewHolderType;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.tableview.storage.models.CellType;


public class IEAViewForHeaderInSectionCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEAViewForHeaderInSectionCell.class, R.layout.header_view_in_section_cell);
    }

    @Override
    protected boolean shouldClickItem() {
        return false;
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.header;
    }

    private IEAViewForHeaderInSectionCell self = this;

    private TextView titleLabel;

    public IEAViewForHeaderInSectionCell(View itemView) {
        super(itemView);

        self.titleLabel = (TextView) itemView.findViewById(R.id.headerTextView);
    }

    @Override
    public void render(Object value) {
        SectionTitleCellModel more = (SectionTitleCellModel) value;
        self.titleLabel.setText(more.titleResId);
    }
}