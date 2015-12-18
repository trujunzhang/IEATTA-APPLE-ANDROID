package com.ieatta.android.modules.cells.edit;
import android.view.View;
import android.widget.EditText;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.adapter.enums.ViewHolderType;
import com.ieatta.android.modules.common.edit.EditCellModel;

/**
 * Created by djzhang on 12/2/15.
 */
public class IEAEditTextFieldCell  extends IEAViewHolder {
    public static CellType  getType() {
        return new CellType(IEAEditTextFieldCell.class,R.layout.edit_text_field_cell);
    }

    private IEAEditTextFieldCell self = this;

    @Override
    protected boolean shouldClickItem() {
        return false;
    }
    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.None;
    }

    private EditText editText;
    private EditCellModel model;

    public IEAEditTextFieldCell(View itemView) {
        super(itemView);

        self.editText = (EditText) itemView.findViewById(R.id.editText);
    }

    @Override
    public void render(Object value) {
        self.model  = (EditCellModel) value;
        self.editText.setText(self.model.editValue);
        self.editText.setHint(self.model.editPlaceHolderResId);
    }
}
