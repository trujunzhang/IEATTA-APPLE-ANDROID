package com.ieatta.android.modules;

import com.ieatta.android.extensions.UIDevice;
import com.ieatta.android.modules.common.MainSegueIdentifier;

/**
 * Created by djzhang on 12/1/15.
 */
public class IEASplitMasterViewController extends IEABaseTableViewController{
    private IEASplitMasterViewController self = this;

    protected void whenSelectedCellTaped(MainSegueIdentifier type){
        switch(UIDevice.currentDevice().userInterfaceIdiom){
            case Pad:
            IEASplitMasterViewController.whenSelectedCellTapedForiPad(type,rootViewController: self.splitViewController!,selectedModel: self.selectedModel)
            break;
            case Phone:
            self.performSegueWithIdentifier(type.rawValue, sender: self)
            break;
            default:
                break;
        }
    }

}
