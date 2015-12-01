package com.ieatta.android.modules;

import android.os.Bundle;

import com.ieatta.android.extensions.storage.DTTableViewManager;
import com.ieatta.android.extensions.storage.MemoryStorage;
import com.ieatta.android.extensions.viewkit.ModelTransfer;
import com.ieatta.android.extensions.viewkit.NSIndexPath;
import com.ieatta.android.modules.common.edit.EditBaseCellModel;

import java.util.LinkedList;

/**
 * Created by djzhang on 12/1/15.
 */
public class IEADTTableViewManagerViewController extends IEAAppSegureTableViewController{
    private IEADTTableViewManagerViewController self = this;
    private DTTableViewManager manager = new DTTableViewManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getTableManager().startManagingWithDelegate(self)
//        setRegisterHeaderClass(IEAViewForHeaderInSectionCell);
    }

    public void whenSelectedEvent(Object model,NSIndexPath indexPath){
//        fatalError("whenSelectedEvent() has not been implemented");
    }

    public void setRegisterHeaderClass( ModelTransfer headerClass){
//        getTableManager().registerHeaderClass(headerClass)
    }

    public void setRegisterFooterClass(ModelTransfer footerClass){
//        getTableManager().registerFooterClass(footer)
    }

    public void setRegisterCellClass(ModelTransfer cellClass){
//        getTableManager().registerCellClass(cellClass)
    }

    public void setRegisterCellClassWhenSelected(ModelTransfer cellClass){
//        getTableManager().registerCellClass(cellClass) { (_, model, indexPath) -> Void in
//            self.whenSelectedEvent((model as! Object), indexPath: indexPath)
//        }
    }

    public void setSectionItems(LinkedList<Object> items,int forSectionIndex ){
//        getMemoryStorage().setItems(items, forSectionIndex);
    }

    public void setFooterModelInSection(Object model,int forSectionIndex ){
//        getMemoryStorage().setSectionFooterModel(model,forSectionIndex: sectionIndex)
    }

    public void removeSectionItemsAtIndexPaths(NSIndexPath[] indexPaths){
//        getMemoryStorage().removeItemsAtIndexPaths(indexPaths)
    }

    public void appendSectionTitleCell(EditBaseCellModel cell,int forSectionIndex ){
//        getMemoryStorage().setSectionHeaderModel(cell, forSectionIndex: index)
    }

    public MemoryStorage getMemoryStorage()  {
        return getTableManager().memoryStorage;
    }

    public DTTableViewManager getTableManager(){
        return manager;
    }


}
