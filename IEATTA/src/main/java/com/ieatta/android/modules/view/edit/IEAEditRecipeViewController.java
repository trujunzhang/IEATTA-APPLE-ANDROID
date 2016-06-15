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



public class IEAEditRecipeViewController extends IEAEditBaseViewController {

    enum EditRecipeSection {
        sectionInformation,//= 0
        sectionPhotos,//= 1
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void prepareForEditTableView() {
        // Add rows for sections.
        this.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Recipe_Information), EditRecipeSection.sectionInformation.ordinal());
        this.setRegisterCellClass(IEAEditTextFieldCell.getType(), EditRecipeSection.sectionInformation.ordinal());
        // **** Important (just for android)****
        this.setRegisterCellClassInSpecialRow(IEAEditTextRecipeFieldCell.getType(), EditRecipeSection.sectionInformation.ordinal(), 1);
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
