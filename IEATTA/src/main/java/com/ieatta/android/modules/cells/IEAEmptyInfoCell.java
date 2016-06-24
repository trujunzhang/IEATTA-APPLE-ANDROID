package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.tableview.storage.models.CellType;
import com.tableview.adapter.IEAViewHolder;


public class IEAEmptyInfoCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEAEmptyInfoCell.class, R.layout.empty_info_cell);
    }

    @Override
    protected boolean shouldClickItem() {
        return false;
    }

    private IEAEmptyInfoCell self = this;

    private TextView infoLabel;

    public IEAEmptyInfoCell(View itemView) {
        super(itemView);

        self.infoLabel = (TextView) itemView.findViewById(R.id.emptyInfoTextView);
    }

    @Override
    public void render(Object value) {
        String more = (String) value;
        self.infoLabel.setText(more);
    }
}
