package com.ieatta.android.modules.view.edit.model;

import com.ieatta.android.modules.common.edit.DatePickerCellModel;
import com.ieatta.android.modules.common.edit.EditCellModel;
import com.ieatta.android.modules.common.edit.EditWaiterCellModel;

import org.ieatta.database.models.DBEvent;
import org.ieatta.database.models.DBNewRecord;
import org.ieatta.database.models.DBPeopleInEvent;
import org.ieatta.database.models.DBPhoto;
import org.ieatta.database.models.DBRecipe;
import org.ieatta.database.models.DBRestaurant;
import org.ieatta.database.models.DBReview;
import org.ieatta.database.models.DBTeam;

import io.realm.Realm;

public abstract class IEAEditAbstractManager implements IEAEditBaseManager {
    private IEAEditAbstractManager self = this;

    @Override
    public Realm convertToEditModel(Object[] rowModels, Realm model) {
        for (Object array : rowModels) {
            Object[] sectionModels = (Object[]) array;
            self.convertSectionArrayToEditModel(sectionModels, model);
        }
        // ***** Important ***** Update created data.
//        model.updateCreatedDate();
        return model;
    }

    private Realm convertSectionArrayToEditModel(Object[] rowModels, Realm model) {
        for (Object row : rowModels) {
            if (row.getClass().equals(EditWaiterCellModel.class)) {
//                updateRow((DBEditWaiterCellModel) row, model);
//            } else if (row.getClass().equals(WriteReviewInEventCellModel.class)) {
//                updateRow((DBWriteReviewInEventCellModel) row, model);
            } else if (row.getClass().equals(DatePickerCellModel.class)) {
//                updateRow((DBDatePickerCellModel) row, model);
            } else if (row.getClass().equals(EditCellModel.class)) {
//                updateRow((DBEditCellModel) row, model);
            }
        }
        return model;
    }

    private void updateRow(EditCellModel row, Realm model) {
        switch (row.editKey) {
            // ++++++++++++++++++++++ Restaurant ++++
            case rest_name:
//                ((DBRestaurant) model).displayName = row.editValue;
                break;
            // ++++++++++++++++++++++ Event ++++
            case event_name:
//                ((DBEvent) model).displayName = row.editValue;
                break;
            // ++++++++++++++++++++++ Team ++++
            case person_name:
//                ((DBTeam) model).displayName = row.editValue;
                break;
            case person_address:
//                ((DBTeam) model).address = row.editValue;
                break;
            case person_email:
//                ((DBTeam) model).email = row.editValue;
                break;
            // ++++++++++++++++++++++ Recipe ++++
            case recipe_name:
//                ((DBRecipe) model).displayName = row.editValue;
                break;
            case recipe_price:
//                ((DBRecipe) model).price = row.editValue;
                break;
            default:
                break;
        }
    }

    private void updateRow(EditWaiterCellModel row, Realm model) {
//        ((DBEvent) model).waiter = row.editValue;
    }

//    private void updateRow(WriteReviewInEventCellModel row,Realm  model){
//    }

    private void updateRow(DatePickerCellModel row, Realm model) {
        switch (row.editKey) {
            case event_starttime:
//                ((DBEvent) model).startDate = row.date;
                break;
            case event_endtime:
//                ((DBEvent) model).endDate = row.date;
                break;
            default:
                break;
        }
    }
}
