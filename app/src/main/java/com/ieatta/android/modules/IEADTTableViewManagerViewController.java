package com.ieatta.android.modules;

import android.os.Bundle;

import com.ieatta.android.extensions.storage.CellType;
import com.ieatta.android.extensions.storage.DTTableViewManager;
import com.ieatta.android.extensions.storage.MemoryStorage;
import com.ieatta.android.extensions.viewkit.NSIndexPath;
import com.ieatta.android.modules.cells.headerfooterview.IEAViewForHeaderInSectionCell;
import com.ieatta.android.modules.common.edit.EditBaseCellModel;
import com.ieatta.com.parse.ParseModelAbstract;

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
        getTableManager().registerCellClass(new CellType(cellClass, layoutResId), forSectionIndex);
    }

    public void setRegisterCellClassWhenSelected(Class cellClass, int layoutResId, int forSectionIndex) {
        getTableManager().registerCellClass(new CellType(cellClass, layoutResId), forSectionIndex);
    }

    public void setSectionItems(List items, int forSectionIndex) {
        getMemoryStorage().setItems(items, forSectionIndex);
    }

    public void setFooterModelInSection(Object model, int forSectionIndex,CellType type) {
        getMemoryStorage().setSectionFooterModel(model, forSectionIndex,type);
    }

    public void removeSectionItemsAtIndexPaths(NSIndexPath[] indexPaths) {
        getMemoryStorage().removeItemsAtIndexPaths(indexPaths);
    }

    public void appendSectionTitleCell(EditBaseCellModel cell, int forSectionIndex) {
        self.appendSectionTitleCell(cell, forSectionIndex,new CellType(IEAViewForHeaderInSectionCell.class, IEAViewForHeaderInSectionCell.layoutResId));
    }

    public void appendSectionTitleCell(EditBaseCellModel cell, int forSectionIndex,CellType type) {
        getMemoryStorage().setSectionHeaderModel(cell, forSectionIndex,type);
    }

    public MemoryStorage getMemoryStorage() {
        return getTableManager().memoryStorage;
    }

    public DTTableViewManager getTableManager() {
        return manager;
    }


}
