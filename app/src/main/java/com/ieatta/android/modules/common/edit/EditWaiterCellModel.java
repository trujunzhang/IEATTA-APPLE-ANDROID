package com.ieatta.android.modules.common.edit;

import com.ieatta.android.modules.view.edit.IEAEditBaseViewController;

/**
 * Created by djzhang on 12/1/15.
 */
public class EditWaiterCellModel extends EditCellModel{
    int editPlaceHolderResId;
    IEAEditBaseViewController viewController;

    public EditWaiterCellModel(IEAEditKey editKey, int editValueResId, int editPlaceHolderResId,IEAEditBaseViewController viewController) {
        super(editKey,editValueResId,editPlaceHolderResId);

        this.viewController = viewController;
    }
}


