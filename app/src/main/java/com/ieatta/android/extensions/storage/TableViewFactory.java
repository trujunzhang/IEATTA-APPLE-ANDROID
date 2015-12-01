package com.ieatta.android.extensions.storage;

import android.view.View;

import java.lang.reflect.Constructor;

/**
 * Created by djzhang on 12/1/15.
 */
public class TableViewFactory {
    
    private Constructor getConstructorForView(Constructor[] ctors) {
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 1) {
                Class[] types = ctor.getParameterTypes();
                boolean isView = this.verifyParaWithView(types);
                if(isView == true){
                    return ctor;
                }
            }
        }
        return ctor;
    }

    private boolean verifyParaWithView(Class[] types) {
        for (int i = 0; i < types.length; i++) {
            Class type = types[i];
            if(type.equals(View.class)){
                return true;
            }
        }
        return false;
    }
}
