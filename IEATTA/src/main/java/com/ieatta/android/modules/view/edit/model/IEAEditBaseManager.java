package com.ieatta.android.modules.view.edit.model;

import com.ieatta.android.modules.view.edit.IEAEditBaseViewController;

import io.realm.Realm;

public interface IEAEditBaseManager {

    public Object[] getRowsInSection(Realm model, IEAEditBaseViewController viewController);

    public Realm convertToEditModel(Object[] rowModels, Realm model);
}


