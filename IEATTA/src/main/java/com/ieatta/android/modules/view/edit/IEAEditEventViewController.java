package com.ieatta.android.modules.view.edit;

import android.os.Bundle;

import com.ieatta.android.R;
import com.ieatta.android.modules.cells.edit.IEADatePickerCell;
import com.ieatta.android.modules.cells.edit.IEAEditTextFieldCell;
import com.ieatta.android.modules.cells.edit.IEAEditWaiterTextFieldCell;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.view.edit.model.IEAEditBaseManager;
import com.ieatta.android.modules.view.edit.model.IEAEditEventManager;
import com.ieatta.android.notification.NSNotificationCenter;
import com.ieatta.android.notification.NotifyType;


public class IEAEditEventViewController extends IEAEditBaseViewController {


    enum EditEventSection {
        sectionInformation,//= 0
        sectionPhotos,//= 1
        sectionDurationDate,//= 2
    }

    // enum showing Row index in the section.
    enum EditEventRows {
        RowWaiter(1);

        int row;

        EditEventRows(int p) {
            row = p;
        }

        int getRow() {
            return row;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void prepareForEditTableView() {
        this.setRegisterCellClass(IEAEditTextFieldCell.getType(), EditEventSection.sectionInformation.ordinal());
        this.setRegisterCellClassInSpecialRow(IEAEditWaiterTextFieldCell.getType(), EditEventSection.sectionInformation.ordinal(), EditEventRows.RowWaiter.getRow());

        this.setRegisterCellClass(IEADatePickerCell.getType(), EditEventSection.sectionDurationDate.ordinal());

        // Add rows for sections.
        this.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Event_Information), EditEventSection.sectionInformation.ordinal());
        this.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Date_of_Event), EditEventSection.sectionDurationDate.ordinal());
    }

    @Override
    protected IEAEditBaseManager getEditManager() {
        return new IEAEditEventManager();
    }
//
//
//    // MARK: Override IEAPhotoGalleryViewController methods
//
//    @Override
//    protected int getPhotoGallerySectionIndex() {
//        return EditEventSection.sectionPhotos.ordinal();
//    }
//
//    @Override
//    protected void postSaveModelSuccess() {
//        NSNotificationCenter.defaultCenter().postNotificationName(NotifyType.PAModelCreateEventNotification, null);
//    }


}
