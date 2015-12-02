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

    private Context context;
    public  MemoryStorage memoryStorage ;

    public DTTableViewManager(Context context) {
        self.context = context;
        self.memoryStorage = new MemoryStorage(new IEATableViewControllerAdapter(self, self.context));
    }

    public IEATableViewControllerAdapter getAdapter(){
        return self.memoryStorage.adapter;
    }

    public int getItemCount() {
        return self.memoryStorage.getItemCount();
    }



    public void registerHeaderClass(Class headerClass) {

    }
    public void registerFooterClass(Class footerClass) {

    }

    public IEAViewHolder createViewHolder(ViewGroup parent, int viewType) {
        RowModel rowModel = self.memoryStorage.getRowModelFromPosition(viewType);
        Class cellClass = rowModel.cellClass;
        int layoutResId = rowModel.layoutResId;

        Constructor[] ctors = cellClass.getDeclaredConstructors();
        Constructor viewConstructor = TableViewFactory.getConstructorForView(ctors);
        View view = LayoutInflater.from(self.context).inflate(layoutResId, parent, false);

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

    public void registerCellClass(Class cellClass, int forSectionIndex,int layoutResId) {
        self.memoryStorage.registerCellClass(cellClass,forSectionIndex,layoutResId);
    }


}
