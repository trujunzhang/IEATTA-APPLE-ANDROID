package com.ieatta.android.modules.cells.edit;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.common.edit.EditCellModel;
import com.ieatta.com.parse.models.Restaurant;

/**
 * Created by djzhang on 12/2/15.
 */
public class IEAEditTextFieldCell  extends IEAViewHolder {
    public static CellType  getType() {
        return new CellType(IEAEditTextFieldCell.class,R.layout.edit_text_field_cell);
    }

    private IEAEditTextFieldCell self = this;

    private TextView editText;
    private EditCellModel model;

    public IEAEditTextFieldCell(View itemView) {
        super(itemView);

        self.editText = (EditText) itemView.findViewById(R.id.editText);
    }

    @Override
    public void updateWithModel(Object model) {
        self.model  = (EditCellModel) model;
        self.editText.setText(self.model.editValue);
        self.editText.setHint(self.model.editPlaceHolderResId);
    }
}
