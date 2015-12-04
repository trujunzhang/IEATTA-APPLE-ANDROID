package com.ieatta.android.modules;

import android.os.Bundle;

import com.ieatta.android.R;
import com.ieatta.android.cache.IEACache;
import com.ieatta.android.modules.cells.IEAGoogleMapAddressCell;
import com.ieatta.android.modules.common.edit.IEAEditKey;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.android.modules.tools.CollectionUtils;
import com.ieatta.android.modules.tools.TableViewHeightInfo;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Photo;
import com.ieatta.com.parse.models.Restaurant;
import com.walnutlabs.android.ProgressHUD;

import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;

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

    public TableViewHeightInfo getTableViewHeightInfo() {
//        return TableViewHeightInfo.getEmptyInfo()
        return null;
    }

//    override func tableView(tableView: UITableView, willDisplayCell cell: UITableViewCell, forRowAtIndexPath indexPath: NSIndexPath) {
//        if let accessoryType = self.getTableViewHeightInfo().accessoryType[indexPath.section]{
//            cell.accessoryType = accessoryType
//        }else{
//            cell.selectionStyle = .None
//        }
//    }

//    override tableView(tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
//        if let height = self.getTableViewHeightInfo().getHeightForHeaderInSection(section){
//            return height
//        }
//        return DEFAULT_SECTION_TITLE_HEADER_HEIGHT
//    }

//    override tableView(tableView: UITableView, heightForRowAtIndexPath indexPath: NSIndexPath) -> CGFloat {
//        if let height = self.getTableViewHeightInfo().getHeightForRowAtIndexPath(indexPath){
//            return height
//        }
//
//        assert(false, "Must set row height at row: \(indexPath.row), section: \(indexPath.section)")
//        return 0.0
//    }

    public Task<Boolean> getPhotosForModelsTask(final Task<List<ParseModelAbstract>> previous) {
        // First of all, query relate photos task.
        return Photo.queryPhotosFromUsedRefs(ParseModelAbstract.getModelPoints(previous))
                .continueWith(new Continuation<List<ParseModelAbstract>, Boolean>() {
                    @Override
                    public Boolean then(Task<List<ParseModelAbstract>> task) throws Exception {
                        // Next, Cache all models' uuid as key and photo's uuid as value.
                        return IEACache.sharedInstance.setPhotoPointForModels(task);
                    }
                });
    }

    public void showGoogleMapAddress(int sectionIndex) {
        self.setRegisterCellClass(IEAGoogleMapAddressCell.getType() sectionIndex);
        self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Current_Address), sectionIndex);
        self.setSectionItems(CollectionUtils.createList(self.getPageModel()), sectionIndex);
    }


}
