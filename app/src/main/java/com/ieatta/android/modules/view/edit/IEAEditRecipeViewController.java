package com.ieatta.android.modules.view.edit;

import android.os.Bundle;
import android.virtualbreak.com.manualdatabase.ActivityModelDebug;

import com.ieatta.android.R;
import com.ieatta.android.modules.cells.edit.IEAEditTextFieldCell;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.view.edit.model.IEAEditBaseManager;
import com.ieatta.android.modules.view.edit.model.IEAEditRecipeManager;

/**
 * Created by djzhang on 12/1/15.
 */

enum EditRecipeSection {
    sectionInformation,//= 0
    sectionPhotos,//= 1
}

public class IEAEditRecipeViewController extends IEAEditBaseViewController {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO djzhang(test)
        self.setEditModel(ActivityModelDebug.getOrderedRecipe());

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void prepareForEditTableView() {
        // Add rows for sections.
        self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Recipe_Information), EditRecipeSection.sectionInformation.ordinal());
        self.setRegisterCellClass(IEAEditTextFieldCell.getType(), EditRecipeSection.sectionInformation.ordinal());
    }

    @Override
    protected IEAEditBaseManager getEditManager() {
        return new IEAEditRecipeManager();
    }


    @Override
    protected void postSaveModelSucess() {
//        dispatch_async(dispatch_get_main_queue(), { () -> Void in
//                NSNotificationCenter.defaultCenter().postNotificationName(PARecipeCreatedNotification, object: nil)
//        })
    }

}
