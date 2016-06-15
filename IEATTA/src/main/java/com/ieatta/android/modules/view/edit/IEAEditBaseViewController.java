package com.ieatta.android.modules.view.edit;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.ieatta.android.R;
import com.ieatta.android.apps.AppAlertView;
import com.ieatta.android.cache.IntentCache;
import com.ieatta.android.modules.tools.CollectionUtils;
import com.ieatta.android.modules.view.edit.model.IEAEditBaseManager;
import com.ieatta.android.modules.view.photogallery.IEAPhotoGalleryViewController;
import com.ieatta.android.observers.EditChangedObserver;

import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;
import io.realm.Realm;

public abstract class IEAEditBaseViewController extends IEAPhotoGalleryViewController {

    @Override
    public boolean havePhotoGallery() {
        return true;
    }

    @Override
    public boolean shouldShowHUD() {
        if (this.newModel == true) {
            return false;
        }
        return true;
    }

    @Override
    public boolean navigationShouldPopOnBackButton() {
        if (this.newModel == false) {
            return true;
        }
        if (EditChangedObserver.sharedInstance.hasTakenPhoto == true) {

            new AlertDialogWrapper.Builder(this)
                    .setTitle(R.string.Alert)
                    .setMessage(R.string.Alert_Take_Photo_Lost)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setNegativeButton(R.string.OK, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            IEAEditBaseViewController.this.popViewControllerAnimated(true);
                        }
                    })
                    .setPositiveButton(R.string.Cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();

            return false;
        }
        return true;
    }


    protected boolean newModel = false;
    protected Realm editedModel;

    protected Object[] rowModels = new Object[0];
    private IEAEditBaseManager editManager;


    public IEAEditBaseViewController setEditModel(Realm editedModel) {
        this.editedModel = editedModel;
        this.newModel = false;

        return this;
    }

    public IEAEditBaseViewController setEditModel(Realm editedModel, boolean newModel) {
        this.editedModel = editedModel;
        this.newModel = newModel;

        return this;
    }

    private int rightButtonTitle() {
        if (this.newModel == true) {
            return R.string.Save;
        }
        return R.string.Update;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        this.setEditModel(this.getTransferedModel(), this.getIntent().getExtras().getBoolean(IntentCache.newModel));

        // **** Important ****
        this.editManager = this.getEditManager();
        this.rowModels = this.editManager.getRowsInSection(this.editedModel, this);

        super.onCreate(savedInstanceState);

//        assert(this.editedModel != nil, "Must setup editedModel's instance.")

        this.setRightBarButtonItem(this.rightButtonTitle(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IEAEditBaseViewController.this.saveModelAction();
            }
        });

        EditChangedObserver.sharedInstance.resetObserver();

//        this.getQueryPhotosTask().onSuccess(new Continuation<List<Realm>, Object>() {
//            @Override
//            public Object then(Task<List<Realm>> task) throws Exception {
//                this.prepareForEditTableView();
//
//                this.setItemsInSection(this.rowModels);
//                this.configurePhotoGallerySection(task);
//
//                return null;
//            }
//        }, Task.UI_THREAD_EXECUTOR);
    }

//    protected Task<List<Realm>> getQueryPhotosTask() {
//        if (this.newModel == true) {
//            List<Realm> empty = new LinkedList<>();
//            return Task.forResult(empty);
//        }
//        return Photo.queryPhotosByModel(this.getPageModel());
//    }

    protected abstract void prepareForEditTableView();

    protected abstract IEAEditBaseManager getEditManager();

    // MARK: Override IEABaseTableViewController methods
    @Override
    public Realm getPageModel() {
        return this.editedModel;
    }

    private void setItemsInSection(Object[] rows) {
        this.rowModels = rows;
        for (int i = 0; i < rows.length; i++) {
            Object[] items = (Object[]) rows[i];
            if (items == null) {
                continue;
            }
            setSectionItems(CollectionUtils.createList(items), i);
        }
    }

    // MARK: Override IEAPhotoGalleryViewController methods

//    @Override
//    protected int getPhotoGallerySectionIndex() {
//        return this.rowModels.length;
//    }

    protected void postSaveModelSuccess() {

    }

    // MARK: NavigationBarItem Events
    private void saveModelAction() {
        /// **** important ****
//        this.rightBarButtonItem.setEnabled(false);

        final Realm model = this.editManager.convertToEditModel(this.rowModels, this.editedModel);

//        model.updateLocalInBackground()
//                .onSuccessTask(new Continuation<Void, Task<Void>>() {
//                    @Override
//                    public Task<Void> then(Task<Void> task) throws Exception {
//                        return NewRecord.addNewRecordForModel(model);
//                    }
//                }).onSuccessTask(new Continuation<Void, Task<Void>>() {
//            @Override
//            public Task<Void> then(Task<Void> task) throws Exception {
//
//                this.postSaveModelSuccess();
//
//                return null;
//            }
//        }).continueWith(new Continuation<Void, Object>() {
//            @Override
//            public Object then(Task<Void> task) throws Exception {
//                if (task.isFaulted()) {
//                    if (this.newModel) {
//                        AppAlertView.showError(R.string.Saved_Failure);
//                    } else {
//                        AppAlertView.showError(R.string.Update_Failure);
//                    }
//                }
//                this.navigationController.popViewControllerAnimated(true);
//                return null;
//            }
//        });
    }

}
