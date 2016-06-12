package com.ieatta.android.modules.view.edit.model;

import com.ieatta.android.modules.view.edit.IEAEditBaseViewController;
import com.ieatta.com.parse.ParseModelAbstract;

public interface IEAEditBaseManager {

    public Object[] getRowsInSection(ParseModelAbstract model, IEAEditBaseViewController viewController);

    public ParseModelAbstract convertToEditModel(Object[] rowModels, ParseModelAbstract model);
}


