package com.ieatta.android.modules.cells.edit;
import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.com.parse.models.Restaurant;

/**
 * Created by djzhang on 12/2/15.
 */
public class IEAEditTextFieldCell  extends IEAViewHolder {
    public static int layoutResId = R.layout.google_address_cll;

    private IEAEditTextFieldCell self = this;

    private TextView formattedAddressLabel;

    public IEAEditTextFieldCell(View itemView) {
        super(itemView);

        self.formattedAddressLabel = (TextView) itemView.findViewById(R.id.formattedAddressLabel);
    }

    @Override
    public void updateWithModel(Object model) {
        Restaurant more  = (Restaurant) model;
        self.formattedAddressLabel.setText(more.getGoogleMapAddress());
    }
}
