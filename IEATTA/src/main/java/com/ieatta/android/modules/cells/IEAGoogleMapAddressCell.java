package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.tableview.adapter.IEAViewHolder;


public class IEAGoogleMapAddressCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEAGoogleMapAddressCell.class, R.layout.google_address_cll);
    }

    @Override
    protected boolean shouldClickItem() {
        return false;
    }

    private IEAGoogleMapAddressCell self = this;

    private TextView formattedAddressLabel;

    public IEAGoogleMapAddressCell(View itemView) {
        super(itemView);

        self.formattedAddressLabel = (TextView) itemView.findViewById(R.id.formattedAddressLabel);
    }

    @Override
    public void render(Object value) {
//        Restaurant more = (Restaurant) value;
//        self.formattedAddressLabel.setText(more.getGoogleMapAddress());
    }
}
