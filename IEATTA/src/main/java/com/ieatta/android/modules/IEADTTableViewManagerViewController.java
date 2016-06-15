package com.ieatta.android.modules;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.ieatta.android.extensions.storage.DTTableViewManager;
import com.ieatta.android.extensions.storage.MemoryStorage;
import com.ieatta.android.extensions.storage.TableViewConfiguration;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.modules.adapter.NSIndexPath;
import com.ieatta.android.modules.adapter.decoration.TableViewDividerDecoration;
import com.ieatta.android.modules.cells.headerfooterview.IEAViewForHeaderInSectionCell;
import com.tableview.model.EditBaseCellModel;

import java.util.List;

public class IEADTTableViewManagerViewController extends IEAAppSegureTableViewController {
    private DTTableViewManager manager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TableViewConfiguration config =
                new TableViewConfiguration.Builder(this.getBaseContext())
                        .setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
                        .setItemDecoration(new TableViewDividerDecoration(this))
                        .setOnItemClickListener(this)
                        .setDebugInfo("Activity_Table_View")
                        .build();
        this.manager = new DTTableViewManager(config);
        this.startManagingWithDelegate(this.manager);

//        this.setRegisterHeaderClass(IEAViewForHeaderInSectionCell.getType());
    }

    public void setRegisterHeaderClass(CellType type) {
        getTableManager().registerHeaderClass(type);
    }

    public void setRegisterFooterClass(CellType type) {
        getTableManager().registerFooterClass(type);
    }

    public void setRegisterCellClassInSpecialRow(CellType type, int forSectionIndex, int forRowIndex) {
        getTableManager().registerCellClassInSpecialRow(type, forSectionIndex, forRowIndex);
    }

    public void setRegisterCellClass(CellType type, int forSectionIndex) {
        getTableManager().registerCellClass(type, forSectionIndex);
    }

    public void setRegisterCellClassWhenSelected(CellType type, int forSectionIndex) {
        getTableManager().registerCellClass(type, forSectionIndex);
    }

    public void setRegisterCellClassWhenSelected(CellType type, int forSectionIndex, int forRowIndex) {
        getTableManager().registerCellClassInSpecialRow(type, forSectionIndex, forRowIndex);
    }


    public void setSectionItems(List items, int forSectionIndex) {
        getMemoryStorage().setItems(items, forSectionIndex);
    }

    public void setFooterModelInSection(Object model, int forSectionIndex, CellType type) {
        getMemoryStorage().setSectionFooterModel(model, forSectionIndex, type);
    }

    public void removeSectionItemsAtIndexPaths(NSIndexPath[] indexPaths) {
        getMemoryStorage().removeItemsAtIndexPaths(indexPaths);
    }

    public void appendSectionTitleCell(EditBaseCellModel cell, int forSectionIndex) {
//        this.appendSectionTitleCell(cell, forSectionIndex, IEAViewForHeaderInSectionCell.getType());
    }

    public void appendSectionTitleCell(EditBaseCellModel cell, int forSectionIndex, CellType type) {
        getMemoryStorage().setSectionHeaderModel(cell, forSectionIndex, type);
    }

    public MemoryStorage getMemoryStorage() {
        return getTableManager().memoryStorage;
    }

    public DTTableViewManager getTableManager() {
        return manager;
    }


}
