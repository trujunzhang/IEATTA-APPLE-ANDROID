package com.ieatta.android.modules.view.edit;

import android.os.Bundle;

import com.ieatta.android.R;
import com.ieatta.android.modules.cells.edit.IEAEditTextFieldCell;
import com.ieatta.android.modules.cells.edit.IEAEditTextRecipeFieldCell;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.view.edit.model.IEAEditBaseManager;
import com.ieatta.android.modules.view.edit.model.IEAEditRecipeManager;
import com.ieatta.android.notification.NSNotificationCenter;
import com.ieatta.android.notification.NotifyType;

enum EditRecipeSection {
    sectionInformation,//= 0
    sectionPhotos,//= 1
}

public class IEAEditRecipeViewController extends IEAEditBaseViewController {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void prepareForEditTableView() {
        // Add rows for sections.
        self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Recipe_Information), EditRecipeSection.sectionInformation.ordinal());
        self.setRegisterCellClass(IEAEditTextFieldCell.getType(), EditRecipeSection.sectionInformation.ordinal());
        // **** Important (just for android)****
        self.setRegisterCellClassInSpecialRow(IEAEditTextRecipeFieldCell.getType(), EditRecipeSection.sectionInformation.ordinal(), 1);
    }

    @Override
    protected IEAEditBaseManager getEditManager() {
        return new IEAEditRecipeManager();
    }


    @Override
    protected void postSaveModelSuccess() {
        NSNotificationCenter.defaultCenter().postNotificationName(NotifyType.PARecipeCreatedNotification, null);
    }

}
