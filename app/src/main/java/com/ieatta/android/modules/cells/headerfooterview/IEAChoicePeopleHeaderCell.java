package com.ieatta.android.modules.cells.headerfooterview;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;

/**
 * Created by djzhang on 12/2/15.
 */
public class IEAChoicePeopleHeaderCell extends IEAViewHolder {
    public static CellType  getType() {
        return new CellType(IEAChoicePeopleHeaderCell.class,R.layout.choice_people_header_cell);
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