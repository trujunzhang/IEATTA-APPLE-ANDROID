package com.ieatta.android.modules.view.edit.model;

/**
 * Created by djzhang on 12/4/15.
 */


class IEAEditEventManager  extends IEAEditAbstractManager{

    override func getRowsInSection(model:ParseModelAbstract,viewController: IEAEditBaseViewController) -> [AnyObject]{
        let _model:Event = model as! Event

        return [
        [
        EditCellModel(editKey: IEAEditKey.event_name, editValue: _model.displayName, editPlaceHolder: L10n.EventName.string),
        EditWaiterCellModel(editKey: IEAEditKey.event_nameofserver, editValue: _model.waiter, editPlaceHolder: L10n.NameOfServer.string,viewController:viewController),
        ],
        [
        PhotoGallery(editKey: IEAEditKey.photo_gallery, delegate: viewController)
        ],
        [
        DatePickerCellModel(editKey: IEAEditKey.event_starttime,  date: _model.startDate ,dateTitle: L10n.StartTime.string),
        DatePickerCellModel(editKey: IEAEditKey.event_endtime,  date: _model.endDate ,dateTitle: L10n.EndTime.string)
        ]
        ]
    }
}
