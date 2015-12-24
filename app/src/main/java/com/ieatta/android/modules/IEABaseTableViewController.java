package com.ieatta.android.modules;

import android.os.Bundle;

import com.ieatta.android.R;
import com.ieatta.android.cache.IEACache;
import com.ieatta.android.modules.cells.IEAGoogleMapAddressCell;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.tools.CollectionUtils;
import com.ieatta.android.modules.tools.TableViewHeightInfo;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Photo;
import com.walnutlabs.android.ProgressHUD;

import java.util.List;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 12/1/15.
 */
public class IEABaseTableViewController extends IEADTTableViewManagerViewController {
    private IEABaseTableViewController self = this;
    ProgressHUD mProgressHUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (self.shouldShowHUD() == true) {
            self.showHUD();
        }

    }

    public void showHUD() {
        mProgressHUD = ProgressHUD.show(IEABaseTableViewController.this, "Loading", true, true, null);
    }

    public void hideHUD() {
        mProgressHUD.dismiss();
    }

    public ParseModelAbstract getPageModel() {
//        fatalError("getPageModel() has not been implemented")
        return null;
    }

    public Task<Boolean> getPhotosForModelsTask(final Task<List<ParseModelAbstract>> previous) {
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


}
