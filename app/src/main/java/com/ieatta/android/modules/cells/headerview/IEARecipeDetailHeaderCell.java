package com.ieatta.android.modules.cells.headerview;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.cells.model.IEARecipeHeader;

/**
 * Created by djzhang on 12/2/15.
 */
public class IEARecipeDetailHeaderCell  extends IEAViewHolder {
    public static CellType  getType() {
        return new CellType(IEARecipeDetailHeaderCell.class, R.layout.restaurant_detail_header_cell);
    }

    @Override
    protected boolean shouldClickItem() {
        return false;
    }

    private IEARecipeDetailHeaderCell self = this;

    private TextView formattedAddressLabel;

    public IEARecipeDetailHeaderCell(View itemView) {
        super(itemView);

//        self.formattedAddressLabel = (TextView) itemView.findViewById(R.id.formattedAddressLabel);
    }

    @Override
    public void render(Object value) {
        IEARecipeHeader more  = (IEARecipeHeader) value;
//        self.formattedAddressLabel.setText(more.model.getGoogleMapAddress());

    }
}