package com.ieatta.android.modules.view.edit.model;

import com.ieatta.android.modules.view.edit.IEAEditBaseViewController;
import com.ieatta.com.parse.ParseModelAbstract;

/**
 * Created by djzhang on 12/4/15.
 */
public interface IEAEditBaseManager {
    
    public Object[] getRowsInSection(ParseModelAbstract model,IEAEditBaseViewController viewController ) ;
public     ParseModelAbstract convertToEditModel( Object[] rowModels,  ParseModelAbstract model);
}


