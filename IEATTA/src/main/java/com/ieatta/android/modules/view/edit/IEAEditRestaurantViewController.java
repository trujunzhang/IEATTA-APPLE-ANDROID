package com.ieatta.android.modules.view.edit;

import android.os.Bundle;

import com.ieatta.android.R;
import com.ieatta.android.modules.cells.edit.IEAEditTextFieldCell;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.provide.IEAEditKey;
import com.ieatta.android.modules.view.edit.model.IEAEditBaseManager;
import com.ieatta.android.modules.view.edit.model.IEAEditRestaurantManager;
import com.ieatta.android.notification.NSNotificationCenter;
import com.ieatta.android.notification.NotifyType;



public class IEAEditRestaurantViewController extends IEAEditBaseViewController {

    enum EditRestaurantSection {
        sectionInformation,//= 0
        sectionPhotos,//= 1
        sectionGoogleMapAddress,//= 2
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void prepareForEditTableView() {
        // Add rows for sections.
//        this.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Restaurant_Information), EditRestaurantSection.sectionInformation.ordinal());

        this.setRegisterCellClass(IEAEditTextFieldCell.getType(), EditRestaurantSection.sectionInformation.ordinal());

        if (this.newModel == false) {
            this.showGoogleMapAddress(EditRestaurantSection.sectionGoogleMapAddress.ordinal());
        }
    }

    @Override
    protected IEAEditBaseManager getEditManager() {
        return new IEAEditRestaurantManager();
    }

    @Override
    protected void postSaveModelSuccess() {
        NSNotificationCenter.defaultCenter().postNotificationName(NotifyType.PAModelCreatedRestaurantNotification, null);

//        // If current device is iPad. we will toggle to the detail view to show it.
//        if(this.newModel == true){
//            IEASplitMasterViewController.showRestaurantDetailViewController(this.editedModel!)
//        }
    }

}
