package com.ieatta.android.modules.view.edit.model;

import com.ieatta.android.R;
import com.ieatta.android.modules.common.edit.DatePickerCellModel;
import com.ieatta.android.modules.common.edit.EditBaseCellModel;
import com.ieatta.android.modules.common.edit.EditCellModel;
import com.ieatta.android.modules.common.edit.EditWaiterCellModel;
import com.ieatta.android.modules.common.edit.PhotoGallery;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.view.edit.IEAEditBaseViewController;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Event;

/**
 * Created by djzhang on 12/4/15.
 */


public class IEAEditEventManager extends IEAEditAbstractManager {
    @Override
    public Object[] getRowsInSection(ParseModelAbstract model, IEAEditBaseViewController viewController) {
        Event _model = (Event) model;

        EditBaseCellModel[] section1 = {
                new EditCellModel(IEAEditKey.event_name, _model.displayName, R.string.Event_Name),
                new EditWaiterCellModel(IEAEditKey.event_nameofserver, _model.waiter, R.string.Name_of_Server, viewController),
        };
        EditBaseCellModel[] section2 = {
                new PhotoGallery(IEAEditKey.photo_gallery, viewController),
        };
        EditBaseCellModel[] section3 = {
                new DatePickerCellModel(IEAEditKey.event_starttime, _model.startDate, R.string.Start_Time),
                new DatePickerCellModel(IEAEditKey.event_endtime, _model.endDate, R.string.End_Time),

        };

//        Object[] sections = {section1,  section3};
        Object[] sections = {null,null,section3};

        return sections;
    }
}
