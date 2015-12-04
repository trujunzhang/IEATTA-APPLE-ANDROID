package com.ieatta.android.modules.common.edit;

import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.view.edit.IEAEditBaseViewController;

/**
 * Created by djzhang on 12/1/15.
 */
public class EditWaiterCellModel extends EditCellModel{
    int editPlaceHolderResId;
    IEAEditBaseViewController viewController;

    public EditWaiterCellModel(IEAEditKey editKey, String editValue, int editPlaceHolderResId,IEAEditBaseViewController viewController) {
        super(editKey,editValue,editPlaceHolderResId);

        this.viewController = viewController;
    }

}


