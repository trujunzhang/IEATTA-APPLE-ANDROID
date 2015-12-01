package com.ieatta.android.extensions.storage;

import android.content.Context;
import android.view.ViewGroup;

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

    public DTTableViewManager(Context context) {
        self.context = context;
    }

    public int getItemCount() {
        return self.memoryStorage.getItemCount();
    }

    public Object getModel(int position) {
        return null;
    }

    public void registerHeaderClass(Class<IEAViewHolder> headerClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?> constructor = headerClass.getConstructor(IEAViewHolder.class);
        Object object = constructor.newInstance(new Object[] { "wanghao" });
    }

    public IEAViewHolder createViewHolder(ViewGroup parent, int viewType) {
//        Class clazz = self.memoryStorage.getItem(viewType);
        return null;
    }

    public void registerCellClass(Class cellClass, int forSectionIndex,int layoutResId) {
        self.memoryStorage.registerCellClass(cellClass,forSectionIndex,layoutResId);
    }
}
