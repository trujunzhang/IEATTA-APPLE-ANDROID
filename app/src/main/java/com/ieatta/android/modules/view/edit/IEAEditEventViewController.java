package com.ieatta.android.modules.view.edit;

import com.ieatta.android.R;
import com.ieatta.android.modules.cells.edit.IEADatePickerCell;
import com.ieatta.android.modules.cells.edit.IEAEditWaiterTextFieldCell;
import com.ieatta.android.modules.common.edit.IEAEditKey;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.android.modules.view.edit.model.IEAEditBaseManager;
import com.ieatta.android.modules.view.edit.model.IEAEditEventManager;

/**
 * Created by djzhang on 12/1/15.
 */
enum EditEventSection {
    sectionInformation,//= 0
    sectionPhotos,//= 1
    sectionDurationDate,//= 2
    sectionGoogleMapAddress,//= 3
}

public class IEAEditEventViewController extends IEAEditBaseViewController {

    @Override
    protected void prepareForEditTableView() {
//        self.setRegisterCellClass(IEAEditWaiterTextFieldCell.class,IEAEditWaiterTextFieldCell.);
//        self.setRegisterCellClass(IEADatePickerCell.class,IEADatePickerCell.);

        // Add rows for sections.
        self.appendSectionTitleCell(new  SectionTitleCellModel( IEAEditKey.Section_Title, R.string.Event_Information),  EditEventSection.sectionInformation.ordinal());
        self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Date_of_Event), EditEventSection.sectionDurationDate.ordinal());
    }

    @Override
    protected IEAEditBaseManager getEditManager() {
        return new IEAEditEventManager();
    }

    // MARK: Override IEAPhotoGalleryViewController methods

    @Override
    protected int getPhotoGallerySectionIndex() {
        return EditEventSection.sectionPhotos.ordinal();
    }

    @Override
    protected void postSaveModelSucess() {
//        dispatch_async(dispatch_get_main_queue(), { () -> Void in
//                NSNotificationCenter.defaultCenter().postNotificationName(PAModelCreateEventNotification, object: nil)
//        })
    }


}
