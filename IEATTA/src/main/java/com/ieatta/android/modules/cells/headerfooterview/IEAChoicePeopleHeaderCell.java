package com.ieatta.android.modules.cells.headerfooterview;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.tableview.storage.models.CellType;
import com.tableview.adapter.IEAViewHolder;
import com.tableview.adapter.enums.ViewHolderType;

public class IEAChoicePeopleHeaderCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEAChoicePeopleHeaderCell.class, R.layout.choice_people_header_cell);
    }

    @Override
    protected boolean shouldClickItem() {
        return false;
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.header;
    }

    private IEAChoicePeopleHeaderCell self = this;

    private TextView titleView;

    public IEAChoicePeopleHeaderCell(View itemView) {
        super(itemView);

        self.titleView = (TextView) itemView.findViewById(R.id.headerTextView);
    }

    @Override
    public void render(Object value) {
        self.titleView.setText(R.string.Select_Person);

    }
}