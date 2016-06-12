package com.ieatta.android.modules;

import android.os.Bundle;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.modules.cells.IEAEmptyInfoCell;
import com.ieatta.android.modules.cells.IEAGoogleMapAddressCell;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.tools.CollectionUtils;
import com.ieatta.com.parse.ParseModelAbstract;

import java.util.List;

import bolts.Task;

public class IEABaseTableViewController extends IEADTTableViewManagerViewController {
    private IEABaseTableViewController self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public ParseModelAbstract getPageModel() {
//        fatalError("getPageModel() has not been implemented")
        return null;
    }

    public Task<Boolean> getPhotosForModelsTask(final Task<List<ParseModelAbstract>> previous) {
        // **** Important ****
        // ignore these tasks.
        // First of all, query relate photos task.
//        return Photo.queryPhotosFromUsedRefs(ParseModelAbstract.getModelPoints(previous))
//                .onSuccess(new Continuation<List<ParseModelAbstract>, Boolean>() {
//                    @Override
//                    public Boolean then(Task<List<ParseModelAbstract>> task) throws Exception {
//                        // Next, Cache all models' uuid as key and photo's uuid as value.
//                        return IEACache.sharedInstance.setPhotoPointForModels(task);
//                    }
//                });
        return Task.forResult(true);
    }

    public void showGoogleMapAddress(int sectionIndex) {
        self.setRegisterCellClass(IEAGoogleMapAddressCell.getType(), sectionIndex);
        self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Current_Address), sectionIndex);
        self.setSectionItems(CollectionUtils.createList(self.getPageModel()), sectionIndex);
    }


    public void configureDetailSection(List items,int emptyInfoResId,CellType type, int forSectionIndex){
        if(items.size() == 0){
            self.setRegisterCellClass(IEAEmptyInfoCell.getType(), forSectionIndex);

            String emptyInfo = getResources().getString(emptyInfoResId);
            self.setSectionItems(CollectionUtils.createList(emptyInfo), forSectionIndex);
        }else{
            self.setRegisterCellClass(type, forSectionIndex);
            self.setSectionItems(items, forSectionIndex);
        }
    }

}
