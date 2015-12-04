package com.ieatta.android.modules.view.edit;

import android.os.Bundle;

import com.ieatta.android.R;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.android.modules.view.edit.model.IEAEditBaseManager;
import com.ieatta.android.modules.view.edit.model.IEAEditPeopleManager;
import com.ieatta.com.parse.models.Team;

/**
 * Created by djzhang on 12/1/15.
 */

enum EditPeopleSection {
    sectionInformation, // =  0
    sectionPhotos, // =  1
}

public class IEAEditPeopleViewController extends IEAEditBaseViewController {

    IEAEditPeopleViewController transfer(Team selectedModel) {
        self.setEditModel(selectedModel, false);
        return this;
    }

    IEAEditPeopleViewController transfer(Team selectedModel, boolean newModel) {
        self.setEditModel(selectedModel, newModel);
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void prepareForEditTableView() {
        // Add rows for sections.
        self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.People_Information), EditPeopleSection.sectionInformation.ordinal());
    }

    @Override
    protected IEAEditBaseManager getEditManager() {
        return new IEAEditPeopleManager();
    }

    @Override
    protected void registerEditSection() {

    }

    @Override
    protected void postSaveModelSucess() {
//        dispatch_async(dispatch_get_main_queue(), { () -> Void in
//                NSNotificationCenter.defaultCenter().postNotificationName(PAPeopleCreatedNotification, object: nil)
//        })
    }


}
