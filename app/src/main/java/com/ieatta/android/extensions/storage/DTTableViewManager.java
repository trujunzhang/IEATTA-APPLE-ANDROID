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
    private Context context;
    private DTTableViewManager self = this;
    public  MemoryStorage memoryStorage = new MemoryStorage();

    public IEATableViewControllerAdapter adapter;

    public DTTableViewManager(Context context) {
        self.context = context;
        self.adapter = new IEATableViewControllerAdapter(self,self.context);
    }

    public IEATableViewControllerAdapter getAdapter(){
        return self.adapter;
    }

    public int getItemCount() {
        return self.memoryStorage.getItemCount();
    }

    public Object getModel(int position) {
        SectionModel sectionModel = self.memoryStorage.getSectionFromPosition(0);
        return sectionModel.items.get(position);
    }

    public void registerHeaderClass(Class headerClass) {

    }
    public void registerFooterClass(Class footerClass) {

    }

    public IEAViewHolder createViewHolder(ViewGroup parent, int viewType) {
        SectionModel sectionModel = self.memoryStorage.getSectionFromPosition(viewType);
        Class cellClass = sectionModel.cellClass;
        int layoutResId = sectionModel.layoutResId;

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
