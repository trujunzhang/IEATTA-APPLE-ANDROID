package com.ieatta.android.modules;

import android.os.Bundle;

import com.ieatta.android.extensions.storage.DTTableViewManager;
import com.ieatta.android.extensions.storage.MemoryStorage;
import com.ieatta.android.extensions.viewkit.ModelTransfer;
import com.ieatta.android.extensions.viewkit.NSIndexPath;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.common.edit.EditBaseCellModel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

/**
 * Created by djzhang on 12/1/15.
 */
public class IEADTTableViewManagerViewController extends IEAAppSegureTableViewController{
    private IEADTTableViewManagerViewController self = this;
    private DTTableViewManager manager = new DTTableViewManager(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        self.startManagingWithDelegate(manager);
//        getTableManager().startManagingWithDelegate(self)
//        setRegisterHeaderClass(IEAViewForHeaderInSectionCell);
    }

    public void whenSelectedEvent(Object model,NSIndexPath indexPath){
//        fatalError("whenSelectedEvent() has not been implemented");
    }

    public void setRegisterHeaderClass( Class<IEAViewHolder> headerClass){
        try {
//            getTableManager().registerHeaderClass(headerClass);

            Constructor<?> constructor = headerClass.getConstructor(IEAViewHolder.class);
            Object object = constructor.newInstance(new Object[] { "wanghao" });
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void setRegisterFooterClass(ModelTransfer footerClass){
//        getTableManager().registerFooterClass(footer)
    }

    public void setRegisterCellClass(ModelTransfer cellClass){
//        getTableManager().registerCellClass(cellClass)
    }

    public void setRegisterCellClassWhenSelected(Class cellClass, int forSectionIndex,int layoutResId){
        getTableManager().registerCellClass(cellClass, forSectionIndex, layoutResId);
//        getTableManager().registerCellClass(cellClass) { (_, model, indexPath) -> Void in
//            self.whenSelectedEvent((model as! Object), indexPath: indexPath)
//        }
    }

    public void setSectionItems(LinkedList<Object> items,int forSectionIndex ){
        getMemoryStorage().setItems(items, forSectionIndex);
    }

    public void setFooterModelInSection(Object model,int forSectionIndex ){
//        getMemoryStorage().setSectionFooterModel(model,forSectionIndex: sectionIndex)
    }

    public void removeSectionItemsAtIndexPaths(NSIndexPath[] indexPaths){
//        getMemoryStorage().removeItemsAtIndexPaths(indexPaths)
    }

    public void appendSectionTitleCell(EditBaseCellModel cell,int forSectionIndex ){
        getMemoryStorage().setSectionHeaderModel(cell, forSectionIndex);
    }

    public MemoryStorage getMemoryStorage()  {
        return getTableManager().memoryStorage;
    }

    public DTTableViewManager getTableManager(){
        return manager;
    }


}
