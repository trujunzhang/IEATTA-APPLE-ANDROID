package com.ieatta.android.extensions.storage;

import android.content.Context;

import com.ieatta.android.modules.IEADTTableViewManagerViewController;
import com.ieatta.android.modules.adopter.IEAViewHolder;

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
        return 0;
    }

    public Object getModel(int position) {
        return null;
    }

    public void registerHeaderClass(Class<IEAViewHolder> headerClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?> constructor = headerClass.getConstructor(IEAViewHolder.class);
        Object object = constructor.newInstance(new Object[] { "wanghao" });
    }
}
