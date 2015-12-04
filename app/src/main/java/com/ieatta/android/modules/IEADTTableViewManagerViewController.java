package com.ieatta.android.modules;

import android.os.Bundle;

import com.ieatta.android.extensions.storage.DTTableViewManager;
import com.ieatta.android.extensions.storage.MemoryStorage;
import com.ieatta.android.extensions.viewkit.NSIndexPath;
import com.ieatta.android.modules.cells.headerfooterview.IEAViewForHeaderInSectionCell;
import com.ieatta.android.modules.common.edit.EditBaseCellModel;
import com.ieatta.com.parse.ParseModelAbstract;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by djzhang on 12/1/15.
 */
public class IEADTTableViewManagerViewController extends IEAAppSegureTableViewController {
    private IEADTTableViewManagerViewController self = this;
    private DTTableViewManager manager = new DTTableViewManager(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        self.startManagingWithDelegate(manager);
        self.setRegisterHeaderClass(IEAViewForHeaderInSectionCell.class,IEAViewForHeaderInSectionCell.layoutResId);
    }

    public void whenSelectedEvent(Object model, NSIndexPath indexPath) {
//        fatalError("whenSelectedEvent() has not been implemented");
    }

    public void setRegisterHeaderClass(Class headerClass, int layoutResId) {
        getTableManager().registerHeaderClass(headerClass,layoutResId);
    }

    public void setRegisterFooterClass(Class footerClass, int layoutResId) {
        getTableManager().registerFooterClass(footerClass,layoutResId);
    }

    public void setRegisterCellClass(Class cellClass, int layoutResId, int forSectionIndex) {
        getTableManager().registerCellClass(cellClass, layoutResId, forSectionIndex);
    }

    public void setRegisterCellClassWhenSelected(Class cellClass, int layoutResId, int forSectionIndex) {
        getTableManager().registerCellClass(cellClass, layoutResId, forSectionIndex);
    }

    public void setSectionItems(List items, int forSectionIndex) {
        getMemoryStorage().setItems(items, forSectionIndex);
    }

    public void setFooterModelInSection(Object model, int forSectionIndex,Class cellClass, int layoutResId) {
        getMemoryStorage().setSectionFooterModel(model, forSectionIndex,cellClass,layoutResId);
    }

    public void removeSectionItemsAtIndexPaths(NSIndexPath[] indexPaths) {
        getMemoryStorage().removeItemsAtIndexPaths(indexPaths);
    }

    public void appendSectionTitleCell(EditBaseCellModel cell, int forSectionIndex) {
        self.appendSectionTitleCell(cell, forSectionIndex,IEAViewForHeaderInSectionCell.class, IEAViewForHeaderInSectionCell.layoutResId);
    }

    public void appendSectionTitleCell(EditBaseCellModel cell, int forSectionIndex,Class cellClass, int layoutResId) {
        getMemoryStorage().setSectionHeaderModel(cell, forSectionIndex,cellClass,layoutResId);
    }

    public MemoryStorage getMemoryStorage() {
        return getTableManager().memoryStorage;
    }

    public DTTableViewManager getTableManager() {
        return manager;
    }


}
