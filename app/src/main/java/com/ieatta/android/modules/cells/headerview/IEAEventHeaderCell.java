package com.ieatta.android.modules.cells.headerview;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.cells.model.IEAEventHeader;
import com.ieatta.android.modules.cells.model.IEARestaurantDetailHeader;

/**
 * Created by djzhang on 12/2/15.
 */
public class IEAEventHeaderCell  extends IEAViewHolder {
    public static int layoutResId = R.layout.event_header_cell;

    private IEAEventHeaderCell self = this;

    private TextView formattedAddressLabel;

    public IEAEventHeaderCell(View itemView) {
        super(itemView);

//        self.formattedAddressLabel = (TextView) itemView.findViewById(R.id.formattedAddressLabel);
    }

    @Override
    public void updateWithModel(Object model) {
        IEAEventHeader more  = (IEAEventHeader) model;
//        self.formattedAddressLabel.setText(more.model.getGoogleMapAddress());

    }
}
