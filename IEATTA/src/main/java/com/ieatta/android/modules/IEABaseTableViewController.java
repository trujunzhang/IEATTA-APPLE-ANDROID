package com.ieatta.android.modules;

import android.os.Bundle;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.modules.cells.IEAEmptyInfoCell;
import com.ieatta.android.modules.cells.IEAGoogleMapAddressCell;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.tools.CollectionUtils;

import java.util.List;

import bolts.Task;

public class IEABaseTableViewController extends IEADTTableViewManagerViewController {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public Object getPageModel() {
//        fatalError("getPageModel() has not been implemented")
        return null;
    }

    public Task<Boolean> getPhotosForModelsTask(final Task<List> previous) {
        // **** Important ****
        // ignore these tasks.
        // First of all, query relate photos task.
//        return Photo.queryPhotosFromUsedRefs(ParseModelAbstract.getModelPoints(previous))
//                .onSuccess(new Continuation<List , Boolean>() {
//                    @Override
//                    public Boolean then(Task<List > task) throws Exception {
//                        // Next, Cache all models' uuid as key and photo's uuid as value.
//                        return IEACache.sharedInstance.setPhotoPointForModels(task);
//                    }
//                });
        return Task.forResult(true);
    }

    public void showGoogleMapAddress(int sectionIndex) {
        this.setRegisterCellClass(IEAGoogleMapAddressCell.getType(), sectionIndex);
        this.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Current_Address), sectionIndex);
//        this.setSectionItems(CollectionUtils.createList(this.getPageModel()), sectionIndex);
    }


    public void configureDetailSection(List items,int emptyInfoResId,CellType type, int forSectionIndex){
        if(items.size() == 0){
            this.setRegisterCellClass(IEAEmptyInfoCell.getType(), forSectionIndex);

            String emptyInfo = getResources().getString(emptyInfoResId);
            this.setSectionItems(CollectionUtils.createList(emptyInfo), forSectionIndex);
        }else{
            this.setRegisterCellClass(type, forSectionIndex);
            this.setSectionItems(items, forSectionIndex);
        }
    }

}
