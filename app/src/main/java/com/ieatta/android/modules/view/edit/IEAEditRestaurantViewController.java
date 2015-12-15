package com.ieatta.android.modules.view.edit;

import android.os.Bundle;
import android.virtualbreak.com.manualdatabase.ActivityModelDebug;

import com.ieatta.android.R;
import com.ieatta.android.modules.cells.edit.IEAEditTextFieldCell;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.view.edit.model.IEAEditBaseManager;
import com.ieatta.android.modules.view.edit.model.IEAEditRestaurantManager;
import com.ieatta.android.notification.NSNotificationCenter;
import com.ieatta.android.notification.NotifyType;

/**
 * Created by djzhang on 12/1/15.
 */

enum EditRestaurantSection {
    sectionInformation,//= 0
    sectionPhotos,//= 1
    sectionGoogleMapAddress,//= 2
}

public class IEAEditRestaurantViewController extends IEAEditBaseViewController {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO djzhang(test)
        self.setEditModel(ActivityModelDebug.getRestaurantForRestaurantDetail());

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void prepareForEditTableView() {
        // Add rows for sections.
        self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Restaurant_Information), EditRestaurantSection.sectionInformation.ordinal());

        self.setRegisterCellClass(IEAEditTextFieldCell.getType(), EditRestaurantSection.sectionInformation.ordinal());

        if (self.newModel == false) {
            self.showGoogleMapAddress(EditRestaurantSection.sectionGoogleMapAddress.ordinal());
        }
    }

    @Override
    protected IEAEditBaseManager getEditManager() {
        return new IEAEditRestaurantManager();
    }

    @Override
    protected void postSaveModelSucess() {
        NSNotificationCenter.defaultCenter().postNotificationName(NotifyType.PAModelCreatedRestaurantNotification, null);

//        // If current device is iPad. we will toggle to the detail view to show it.
//        if(self.newModel == true){
//            IEASplitMasterViewController.showRestaurantDetailViewController(self.editedModel!)
//        }
    }

}
