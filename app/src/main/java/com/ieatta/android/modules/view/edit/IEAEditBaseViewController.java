package com.ieatta.android.modules.view.edit;

import android.os.Bundle;

import com.ieatta.android.apps.AppAlertView;
import com.ieatta.android.modules.cells.edit.IEAEditTextFieldCell;
import com.ieatta.android.modules.tools.CollectionUtils;
import com.ieatta.android.modules.view.edit.model.IEAEditBaseManager;
import com.ieatta.android.modules.view.photogallery.IEAPhotoGalleryViewController;
import com.ieatta.android.observers.EditChangedObserver;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Photo;

import java.util.LinkedList;
import java.util.List;

import bolts.Task;

/**
 * Created by djzhang on 12/1/15.
 */
public abstract class IEAEditBaseViewController extends IEAPhotoGalleryViewController {
    protected IEAEditBaseViewController self = this;

    @Override
    public boolean havePhotoGallery() {
        return true;
    }

    @Override
    public boolean shouldShowHUD() {
        if (self.newModel == true) {
            return false;
        }
        return true;
    }

    @Override
    public boolean navigationShouldPopOnBackButton() {
        if (self.newModel == false) {
            return true;
        }
//        if(EditChangedObserver.sharedInstance.hasTakenPhoto == true){
//            let refreshAlert = UIAlertController(title: L10n.Alert.string, message: L10n.AlertTakePhotoLost.string, preferredStyle: UIAlertControllerStyle.Alert)
//
//            refreshAlert.addAction(UIAlertAction(title: L10n.OK.string, style: .Default, handler: { (action: UIAlertAction!) in
//                self.navigationController?.popViewControllerAnimated(true)
//            }))
//
//            refreshAlert.addAction(UIAlertAction(title: L10n.Cancel.string, style: .Default, handler: { (action: UIAlertAction!) in
//
//            }))
//
//            presentViewController(refreshAlert, animated: true, completion: nil)
//            return false;
//        }
        return true;
    }


    protected boolean newModel = false;
    protected ParseModelAbstract editedModel;

    protected Object[] rowModels = new Object[0];
    private IEAEditBaseManager editManager;


    public IEAEditBaseViewController setEditModel(ParseModelAbstract editedModel) {
        self.editedModel = editedModel;
        self.newModel = false;

        return self;
    }

    public IEAEditBaseViewController setEditModel(ParseModelAbstract editedModel, boolean newModel) {
        self.editedModel = editedModel;
        self.newModel = newModel;

        return self;
    }

//    var rightButtonTitle: String{
//        if(self.newModel == true){
//            return L10n.Save.string
//        }
//        return L10n.Update.string
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO djzhang(test)
        self.hideHUD();

//        assert(self.editedModel != nil, "Must setup editedModel's instance.")

//        self.navigationItem.rightBarButtonItem = UIBarButtonItem(title: rightButtonTitle, style: .Plain, target: self, action: "saveModelAction")

//        .sharedInstance.resetObserver();

        self.editManager = self.getEditManager();

        // TODO djzhang(test)
        self.prepareForEditTableView();
        self.setItemsInSection(self.editManager.getRowsInSection(self.editedModel, self));


//        self.getQueryPhotosTask()
//                .continueWithBlock { (task) -> AnyObject? in
//
//            dispatch_async(dispatch_get_main_queue(), { () -> Void in
//            if let _ = task.error{
//
//            }else{
//                // Finally, hide hud.
//                if(self.newModel == false){
//                    self.hideHUD()
//                }
//
//                self.prepareForEditTableView()
//
//                self.setItemsInSection(self.editManager!.getRowsInSection(self.editedModel!,viewController: self))
//
//                self.configurePhotoGallerySection(task)
//            }
//            })
//
//            return nil
//        }

    }

    protected Task<List<ParseModelAbstract>> getQueryPhotosTask() {
        if (self.newModel == true) {
            List<ParseModelAbstract> empty = new LinkedList<>();
            return Task.forResult(empty);
        }
        return Photo.queryPhotosByModel(self.getPageModel());
    }

    protected abstract void prepareForEditTableView();

    protected abstract IEAEditBaseManager getEditManager();

    // MARK: Override IEABaseTableViewController methods
    @Override
    public ParseModelAbstract getPageModel() {
        return self.editedModel;
    }

    private void setItemsInSection(Object[] rows) {
        self.rowModels = rows;
        for (int i = 0; i < rows.length; i++) {
            Object[] items = (Object[]) rows[i];
            if(items == null){
                continue;
            }
            setSectionItems(CollectionUtils.createList(items), i);
        }
    }

    // MARK: Override IEAPhotoGalleryViewController methods

    @Override
    protected int getPhotoGallerySectionIndex() {
        return self.rowModels.length;
    }

    protected void postSaveModelSucess() {
//        fatalError("postSaveModelSucess() has not been implemented")
    }

    // MARK: NavigationBarItem Events
    protected void saveModelAction() {
        /// **** important ****
//        self.navigationItem.rightBarButtonItem?.enabled = false

//        let model = self.editManager!.convertToEditModel(from: self.rowModels, to: self.editedModel!)
//
//        if(self.newModel == true){
//            self.saveNewModel(model).continueWithBlock({ (task) -> AnyObject? in
//            return self.afterSaveNewModelTask(task)
//            })
//        }else{
//            /// The model already exist, delete it first.
//            (model as! ParseModelQuery).unpinInBackgroundWithNewRecord().continueWithBlock({ (task) -> AnyObject? in
//            if let _ = task.error{
//                dispatch_async(dispatch_get_main_queue(), { () -> Void in
//                        AppAlertView.showError(L10n.UpdateFailure.string)
//                })
//            }else{
//                return self.saveNewModel(model).continueWithBlock({ (task) -> AnyObject? in
//                return self.afterSaveNewModelTask(task)
//                })
//            }
//            return BFTask(error: task.error!)
//            })
//        }
    }

//    func afterSaveNewModelTask(task:BFTask) -> BFTask?{
//        dispatch_async(dispatch_get_main_queue(), { () -> Void in
//        if let _ = task.error{
//            AppAlertView.showError(L10n.SavedFailure.string)
//        }else{
//            self.postSaveModelSucess()
//        }
//        self.navigationController?.popViewControllerAnimated(true)
//        })
//
//        return nil
//    }
//
//    func saveNewModel(newModel:ParseModelAbstract) -> BFTask{
//        return (newModel as! ParseModelQuery).pinInBackgroundWithNewRecord()
//    }


}
