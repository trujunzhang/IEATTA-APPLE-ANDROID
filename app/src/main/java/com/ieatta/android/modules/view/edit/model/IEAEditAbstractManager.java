package com.ieatta.android.modules.view.edit.model;

import com.ieatta.android.modules.common.edit.DatePickerCellModel;
import com.ieatta.android.modules.common.edit.EditCellModel;
import com.ieatta.android.modules.common.edit.EditWaiterCellModel;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Event;
import com.ieatta.com.parse.models.Recipe;
import com.ieatta.com.parse.models.Restaurant;
import com.ieatta.com.parse.models.Team;

/**
 * Created by djzhang on 12/4/15.
 */

public abstract class IEAEditAbstractManager implements IEAEditBaseManager {
    private IEAEditAbstractManager self = this;

    @Override
    public ParseModelAbstract convertToEditModel(Object[] rowModels, ParseModelAbstract model) {
        for (Object array : rowModels) {
            Object[] sectionModels = (Object[]) array;
            self.convertSectionArrayToEditModel(sectionModels, model);
        }
        // ***** Important ***** Update created data.
        model.updateCreatedDate();
        return model;
    }

    private ParseModelAbstract convertSectionArrayToEditModel(Object[] rowModels, ParseModelAbstract model) {
        for (Object row : rowModels) {
            if (row.getClass().equals(EditWaiterCellModel.class)) {
                updateRow((EditWaiterCellModel) row, model);
//            } else if (row.getClass().equals(WriteReviewInEventCellModel.class)) {
//                updateRow((WriteReviewInEventCellModel) row, model);
            } else if (row.getClass().equals(DatePickerCellModel.class)) {
                updateRow((DatePickerCellModel) row, model);
            } else if (row.getClass().equals(EditCellModel.class)) {
                updateRow((EditCellModel) row, model);
            }
        }
        return model;
    }

    private void updateRow(EditCellModel row, ParseModelAbstract model) {
        switch (row.editKey) {
            // ++++++++++++++++++++++ Restaurant ++++
            case rest_name:
                ((Restaurant) model).displayName = row.editValue;
                break;
            // ++++++++++++++++++++++ Event ++++
            case event_name:
                ((Event) model).displayName = row.editValue;
                break;
            // ++++++++++++++++++++++ Team ++++
            case person_name:
                ((Team) model).displayName = row.editValue;
                break;
            case person_address:
                ((Team) model).address = row.editValue;
                break;
            case person_email:
                ((Team) model).email = row.editValue;
                break;
            // ++++++++++++++++++++++ Recipe ++++
            case recipe_name:
                ((Recipe) model).displayName = row.editValue;
                break;
            case recipe_price:
                ((Recipe) model).price = row.editValue;
                break;
            default:
                break;
        }
    }

    private void updateRow(EditWaiterCellModel row, ParseModelAbstract model) {
        ((Event)model).waiter = row.editValue;
    }

//    private void updateRow(WriteReviewInEventCellModel row,ParseModelAbstract  model){
//    }

    private void updateRow(DatePickerCellModel row, ParseModelAbstract model) {
        switch (row.editKey) {
            case event_starttime:
                ((Event) model).startDate = row.date;
                break;
            case event_endtime:
                ((Event) model).endDate = row.date;
                break;
            default:
                break;
        }
    }
}
