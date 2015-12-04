package com.ieatta.android.modules.cells.headerview;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.cells.model.IEARecipeHeader;
import com.ieatta.android.modules.cells.model.IEARestaurantDetailHeader;
import com.ieatta.com.parse.models.Restaurant;

/**
 * Created by djzhang on 12/2/15.
 */
public class IEARecipeDetailHeaderCell  extends IEAViewHolder {
    @Override
    public CellType getType() {
        return new CellType(IEARecipeDetailHeaderCell.class, R.layout.restaurant_detail_header_cell);
    }

    private IEARecipeDetailHeaderCell self = this;

    private TextView formattedAddressLabel;

    public IEARecipeDetailHeaderCell(View itemView) {
        super(itemView);

//        self.formattedAddressLabel = (TextView) itemView.findViewById(R.id.formattedAddressLabel);
    }

    @Override
    public void updateWithModel(Object model) {
        IEARecipeHeader more  = (IEARecipeHeader) model;
//        self.formattedAddressLabel.setText(more.model.getGoogleMapAddress());

    }
}