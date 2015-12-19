package com.ieatta.android.modules.view.edit;

import android.os.Bundle;
import android.view.View;

import com.ieatta.android.R;
import com.ieatta.android.apps.AppAlertView;
import com.ieatta.android.cache.IntentCache;
import com.ieatta.android.modules.tools.CollectionUtils;
import com.ieatta.android.modules.view.edit.model.IEAEditBaseManager;
import com.ieatta.android.modules.view.photogallery.IEAPhotoGalleryViewController;
import com.ieatta.android.observers.EditChangedObserver;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.ParseModelQuery;
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
        // **** Important ****
        self.editManager = self.getEditManager();
        self.rowModels = self.editManager.getRowsInSection(self.editedModel, self);

        super.onCreate(savedInstanceState);

//        assert(self.editedModel != nil, "Must setup editedModel's instance.")

        self.rightBarButtonItem.setText(self.rightButtonTitle());
        self.rightBarButtonItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                self.saveModelAction();
            }
        });

        EditChangedObserver.sharedInstance.resetObserver();

        // TODO djzhang(test)
//        self.prepareForEditTableView();
//        self.setItemsInSection(self.editManager.getRowsInSection(self.editedModel, self));

        self.getQueryPhotosTask().onSuccess(new Continuation<List<ParseModelAbstract>, Object>() {
            @Override
            public Object then(Task<List<ParseModelAbstract>> task) throws Exception {
                self.prepareForEditTableView();

                self.setItemsInSection(self.rowModels);
                self.configurePhotoGallerySection(task);

                return null;
            }
        }).continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
                // Finally, hide hud.
                if (self.newModel == false) {
                    self.hideHUD();
                }
                if (task.isFaulted()) {

                }
                return null;
            }
        });

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

    protected void postSaveModelSucess() {
//        fatalError("postSaveModelSucess() has not been implemented")
    }

    // MARK: NavigationBarItem Events
    private void saveModelAction() {
        /// **** important ****
        self.rightBarButtonItem.setEnabled(false);

        final ParseModelAbstract model = self.editManager.convertToEditModel(self.rowModels, self.editedModel);

        if (self.newModel == true) {
            self.saveNewModel(model).continueWith(new Continuation() {
                @Override
                public Object then(Task task) throws Exception {
                    if (task.isFaulted() == true) {
//                        AppAlertView.showError(L10n.SaveFailure.string)
                    }
                    return self.afterSaveNewModelTask(task);
                }
            });
        } else {
            /// The model already exist, delete it first.
            ((ParseModelQuery) model).unpinInBackgroundWithNewRecord().onSuccessTask(new Continuation<Void, Task>() {
                @Override
                public Task then(Task<Void> task) throws Exception {
                    return self.saveNewModel(model);
                }
            }).continueWith(new Continuation() {
                @Override
                public Object then(Task task) throws Exception {
                    if (task.isFaulted() == true) {
//                        AppAlertView.showError(L10n.UpdateFailure.string)
                    }
                    return self.afterSaveNewModelTask(task);
                }
            });
        }
    }

    private Task afterSaveNewModelTask(Task task) {
        if (task.isFaulted() == true) {
//                        AppAlertView.showError(L10n.SavedFailure.string)
        } else {
            self.postSaveModelSucess();
        }
        self.finish();
        return null;
    }

    private Task saveNewModel(ParseModelAbstract newModel) {
        return ((ParseModelQuery) newModel).pinInBackgroundWithNewRecord();
    }


}
