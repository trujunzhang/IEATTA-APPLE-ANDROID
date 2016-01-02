package com.ieatta.android.extensions.storage;

import android.content.Context;
import android.test.InstrumentationTestCase;
import android.util.AttributeSet;
import android.view.View;

import junit.framework.TestCase;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by djzhang on 12/1/15.
 */

class MyClass {
    private String displayName = "";
    private int price;
    private View view;

    public MyClass(View view) {
        this.view = view;
    }

    public MyClass(String displayName) {
        this.displayName = displayName;
    }

    public MyClass(int price) {
        this.price = price;
    }

    public MyClass(String displayName, int price) {
        this.displayName = displayName;
        this.price = price;
    }
}

public class DTTableViewManagerTest extends InstrumentationTestCase {

    private Context context;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        this.context = this.getInstrumentation().getContext();
    }

    public void testInstanceOfClass() throws Exception {
        try {
            Constructor[] ctors = MyClass.class.getDeclaredConstructors();
            Constructor viewConstructor = this.getConstructorForView(ctors);

            MyClass myClass = (MyClass) viewConstructor.newInstance(new Object[]{new View(this.context)});

//            MyClass.class.getConstructor(Class.class).newInstance(Some.class);
            Constructor<?> constructor = MyClass.class.getConstructor(MyClass.class);
            Object object = constructor.newInstance(new Object[]{"wanghao"});

            int y = 0;
            Object z = object;
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