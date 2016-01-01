package com.ieatta.android.modules.view.edit;

import android.os.Bundle;
import android.view.View;

import com.ieatta.android.R;
import com.ieatta.android.cache.IntentCache;
import com.ieatta.android.modules.tools.CollectionUtils;
import com.ieatta.android.modules.view.edit.model.IEAEditBaseManager;
import com.ieatta.android.modules.view.photogallery.IEAPhotoGalleryViewController;
import com.ieatta.android.observers.EditChangedObserver;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.NewRecord;
import com.ieatta.com.parse.models.Photo;

import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
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

    private int rightButtonTitle() {
        if (self.newModel == true) {
            return R.string.Save;
        }
        return R.string.Update;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        self.setEditModel(self.getTransferedModel(), self.getIntent().getExtras().getBoolean(IntentCache.newModel));

        // **** Important ****
        self.editManager = self.getEditManager();
        self.rowModels = self.editManager.getRowsInSection(self.editedModel, self);

        super.onCreate(savedInstanceState);

//        assert(self.editedModel != nil, "Must setup editedModel's instance.")

        self.setRightBarButtonItem(self.rightButtonTitle(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                self.saveModelAction();
            }
        });

        EditChangedObserver.sharedInstance.resetObserver();

        self.getQueryPhotosTask().onSuccess(new Continuation<List<ParseModelAbstract>, Object>() {
            @Override
            public Object then(Task<List<ParseModelAbstract>> task) throws Exception {
                self.prepareForEditTableView();

                self.setItemsInSection(self.rowModels);
                self.configurePhotoGallerySection(task);

                return null;
            }
        }, Task.UI_THREAD_EXECUTOR);
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
            if (items == null) {
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

    protected void postSaveModelSuccess() {

    }

    // MARK: NavigationBarItem Events
    private void saveModelAction() {
        /// **** important ****
        self.rightBarButtonItem.setEnabled(false);

        final ParseModelAbstract model = self.editManager.convertToEditModel(self.rowModels, self.editedModel);

        model.updateLocalInBackground()
                .onSuccessTask(new Continuation<Void, Task<Void>>() {
                    @Override
                    public Task<Void> then(Task<Void> task) throws Exception {
                        return NewRecord.addNewRecordForModel(model);
                    }
                }).onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {

                self.postSaveModelSuccess();

                return null;
            }
        }).continueWith(new Continuation<Void, Object>() {
            @Override
            public Object then(Task<Void> task) throws Exception {
                if (task.isFaulted()) {
                    Exception error = task.getError();
//                    AppAlertView.showError(L10n.UpdateFailure.string)
                }
                self.navigationController.popViewControllerAnimated(true);
                return null;
            }
        });
    }

}
