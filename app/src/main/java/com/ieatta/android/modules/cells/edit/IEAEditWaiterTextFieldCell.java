package com.ieatta.android.modules.cells.edit;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.common.edit.EditWaiterCellModel;
import com.ieatta.com.parse.models.Restaurant;

/**
 * Created by djzhang on 12/2/15.
 */
public class IEAEditWaiterTextFieldCell  extends IEAViewHolder {
    public static CellType  getType() {
        return new CellType(IEAEditWaiterTextFieldCell.class,R.layout.edit_waiter_text_field_cell);
    }

    private IEAEditWaiterTextFieldCell self = this;

    private EditText editText;
    private EditWaiterCellModel model;

    public IEAEditWaiterTextFieldCell(View itemView) {
        super(itemView);

        self.editText = (EditText) itemView.findViewById(R.id.editText);
    }

    @Override
    public void updateWithModel(Object model) {
        self.model  = (EditWaiterCellModel) model;
        self.editText.setText(self.model.editValue);
        self.editText.setHint(self.model.editPlaceHolderResId);
    }
}
