package com.ieatta.android.modules.cells.edit;
import android.view.View;
import android.widget.EditText;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.adapter.enums.ViewHolderType;
import com.ieatta.android.modules.common.edit.EditWaiterCellModel;

/**
 * Created by djzhang on 12/2/15.
 */
public class IEAEditWaiterTextFieldCell  extends IEAViewHolder {
    public static CellType  getType() {
        return new CellType(IEAEditWaiterTextFieldCell.class,R.layout.edit_waiter_text_field_cell);
    }

    private IEAEditWaiterTextFieldCell self = this;

    @Override
    protected boolean shouldClickItem() {
        return false;
    }
    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.None;
    }

    private EditText editText;
    private EditWaiterCellModel model;

    public IEAEditWaiterTextFieldCell(View itemView) {
        super(itemView);

        self.editText = (EditText) itemView.findViewById(R.id.editText);
    }

    @Override
    public void render(Object value) {
        self.model  = (EditWaiterCellModel) value;
        self.editText.setText(self.model.editValue);
        self.editText.setHint(self.model.editPlaceHolderResId);
    }
}
