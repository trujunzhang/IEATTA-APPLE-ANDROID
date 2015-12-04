package com.ieatta.android.modules.view.edit.model;

import com.ieatta.android.R;
import com.ieatta.android.modules.common.edit.DatePickerCellModel;
import com.ieatta.android.modules.common.edit.EditCellModel;
import com.ieatta.android.modules.common.edit.EditWaiterCellModel;
import com.ieatta.android.modules.common.edit.IEAEditKey;
import com.ieatta.android.modules.common.edit.PhotoGallery;
import com.ieatta.android.modules.view.edit.IEAEditBaseViewController;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Event;

/**
 * Created by djzhang on 12/4/15.
 */


class IEAEditEventManager  extends IEAEditAbstractManager{
    @Override
    public void getRowsInSection(ParseModelAbstract model, IEAEditBaseViewController viewController) {
        Event _model = (Event) model;

        return {
                {
new EditCellModel(IEAEditKey.event_name,         _model.displayName, R.string.Event_Name),
new EditWaiterCellModel(IEAEditKey.event_nameofserver,         _model.waiter, R.string.Name_of_Server, viewController),
        },
        {
   new PhotoGallery(IEAEditKey.photo_gallery, viewController),
        },
        {
      new DatePickerCellModel(IEAEditKey.event_starttime, _model.startDate,         R.string.Start_Time),
new        DatePickerCellModel(IEAEditKey.event_endtime, _model.endDate,         R.string.End_Time),
        }
        };
    }
}
