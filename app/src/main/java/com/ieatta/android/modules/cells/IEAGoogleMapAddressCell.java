package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.com.parse.models.Restaurant;

public class IEAGoogleMapAddressCell extends IEAViewHolder {
    public static int layoutResId = R.layout.google_address_cll;

    private IEAGoogleMapAddressCell self = this;

    private TextView formattedAddressLabel;

    public IEAGoogleMapAddressCell(View itemView) {
        super(itemView);

        self.formattedAddressLabel = (TextView) itemView.findViewById(R.id.formattedAddressLabel);
    }

    @Override
    public void updateWithModel(Object model) {
        Restaurant more  = (Restaurant) model;
        self.formattedAddressLabel.setText(more.getGoogleMapAddress());
    }
}
