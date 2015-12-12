package com.ieatta.android.modules.view.search;

import android.os.Bundle;

import com.ieatta.android.modules.IEASplitDetailViewController;
import com.ieatta.android.modules.adapter.NSIndexPath;
import com.ieatta.android.modules.cells.IEAPeopleInfoCell;
import com.ieatta.com.parse.models.Team;

enum ManagerPeopleSection  {
         sectionTeam ;//= 0
        }
/**
 * Created by djzhang on 12/1/15.
 */
public class IEAManagerPeopleViewController extends IEASplitDetailViewController {
    private IEAManagerPeopleViewController self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        self.setRegisterCellClassWhenSelected(IEAPeopleInfoCell.getType(),ManagerPeopleSection.sectionTeam.ordinal());
    }

    @Override
    public void whenSelectedEvent(Object model, NSIndexPath indexPath) {
        self.showSelectedModel((Team)model);
    }

    public void showSelectedModel(Team model){
//        self.getManagerNavigationViewController().pushViewController(UIStoryboard.Storyboard.Controllers.editPeopleViewController().transfer(model), animated: true)
    }

    // MARK: Navigation item actions
    public void addPeopleAction() {
//        self.getManagerNavigationViewController().pushViewController(UIStoryboard.Storyboard.Controllers.editPeopleViewController().transfer(Team(), newModel: true), animated: true)
    }

}
