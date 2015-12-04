package com.ieatta.android.modules.cells.headerfooterview;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.cells.edit.IEADatePickerCell;
import com.ieatta.android.modules.common.edit.SectionChoicePeopleCellModel;

/**
 * Created by djzhang on 12/2/15.
 */
public class IEAChoicePeopleHeaderCell extends IEAViewHolder {
    @Override
    public CellType getType() {
        return new CellType(IEAChoicePeopleHeaderCell.class,R.layout.choice_people_header_cell);
    }

    private IEAChoicePeopleHeaderCell self = this;

    private TextView titleView;

    public IEAChoicePeopleHeaderCell(View itemView) {
        super(itemView);

        self.titleView = (TextView) itemView.findViewById(R.id.headerTextView);
    }

    @Override
    public void updateWithModel(Object model) {
        self.titleView.setText(R.string.Select_Person);

    }
}