package com.ieatta.android.modules.view.edit.model;

/**
 * Created by djzhang on 12/4/15.
 */

abstract class  IEAEditAbstractManager implements IEAEditBaseManager{
    @Override
    public ParseModelAbstract convertToEditModel(Object[] rowModels, ParseModelAbstract model) {
//        for array in rowModels{
//            let sectionModels = (array as! [AnyObject])
//            self.convertSectionArrayToEditModel(fromSectionModels: sectionModels, to: model)
//        }
        // ***** Important ***** Update created data.
        model.updateCreatedDate();
        return model;
    }

    private func convertSectionArrayToEditModel(fromSectionModels rowModels:[AnyObject], to model:ParseModelAbstract) -> ParseModelAbstract{
        for row in rowModels{
            if let editCellModel = row as? EditWaiterCellModel{
                updateRow(editCellModel,forModel: model)
            }else if let editCellModel = row as? WriteReviewInEventCellModel{
                updateRow(editCellModel,forModel: model)
            }else if let editCellModel = row as? DatePickerCellModel{
                updateRow(editCellModel,forModel: model)
            }else if let editCellModel = row as? EditCellModel{
                updateRow(editCellModel,forModel: model)
            }
        }
        return model
    }

    private func updateRow(row:EditCellModel,forModel model:ParseModelAbstract){
        switch(row.editKey){
            // ++++++++++++++++++++++ Restaurant ++++
            case IEAEditKey.rest_name:
                (model as! Restaurant).displayName = row.editValue
                break;
            // ++++++++++++++++++++++ Event ++++
            case IEAEditKey.event_name:
                (model as! Event).displayName = row.editValue
                break;
            // ++++++++++++++++++++++ Team ++++
            case IEAEditKey.person_name   :
                (model as! Team).displayName = row.editValue
                break;
            case IEAEditKey.person_address :
                (model as! Team).address = row.editValue
                break;
            case IEAEditKey.person_email   :
                (model as! Team).email = row.editValue
                break;
            // ++++++++++++++++++++++ Recipe ++++
            case IEAEditKey.recipe_name :
                (model as! Recipe).displayName = row.editValue
                break;
            case IEAEditKey.recipe_cost :
                if let n = NSNumberFormatter().numberFromString(row.editValue) {
                (model as! Recipe).cost = CGFloat(n)
            }
            break;
            default:
                break;
        }
    }

    private func updateRow(row:EditWaiterCellModel,forModel model:ParseModelAbstract){
        (model as! Event).waiter = row.editValue
    }

    private func updateRow(row:WriteReviewInEventCellModel,forModel model:ParseModelAbstract){
    }

    private func updateRow(row:DatePickerCellModel,forModel model:ParseModelAbstract){
        switch(row.editKey){
            case IEAEditKey.event_starttime:
                (model as! Event).startDate = row.date
                break;
            case IEAEditKey.event_endtime:
                (model as! Event).endDate = row.date
                break;
            default:
                break;
        }
    }
}
