package com.ieatta.android.extensions.storage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ieatta.android.modules.adapter.IEATableViewControllerAdapter;
import com.ieatta.android.modules.adapter.IEAViewHolder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by djzhang on 12/1/15.
 */
public class DTTableViewManager {
    private DTTableViewManager self = this;

    private TableViewConfiguration configuration;
    public  MemoryStorage memoryStorage ;

    public DTTableViewManager(TableViewConfiguration configuration) {
        self.memoryStorage = new MemoryStorage(new IEATableViewControllerAdapter(self, configuration.builder.context));
    }

    public IEATableViewControllerAdapter getAdapter(){
        return self.memoryStorage.adapter;
    }

    public int getItemCount() {
        return self.memoryStorage.getItemCount();
    }

    public void registerHeaderClass(CellType type) {
        self.memoryStorage.cellTypeUtils.registerType(type);
    }

    public void registerFooterClass(CellType type) {
        self.memoryStorage.cellTypeUtils.registerType(type);
    }

    public IEAViewHolder createViewHolder(ViewGroup parent, int viewType) {
        CellType cellType = self.memoryStorage.cellTypeUtils.getModelType(viewType);
        Class cellClass = cellType.cellClass;
        int layoutResId = cellType.layoutResId;

        Constructor[] ctors = cellClass.getDeclaredConstructors();
        Constructor viewConstructor = TableViewFactory.getConstructorForView(ctors);
        View view = LayoutInflater.from(self.configuration.builder.context).inflate(layoutResId, parent, false);

        IEAViewHolder viewHolder = null;
        try {
            viewHolder = (IEAViewHolder) viewConstructor.newInstance(new Object[]{view});
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return viewHolder;
    }

    public void registerCellClass(CellType type, int forSectionIndex) {
        self.memoryStorage.registerCellClass(type, forSectionIndex);
    }
    public void registerCellClassInSpecialRow(CellType type, int forSectionIndex, int forRowIndex) {
        self.memoryStorage.registerCellClassInSpecialRow(type, forSectionIndex,forRowIndex);
    }
}
