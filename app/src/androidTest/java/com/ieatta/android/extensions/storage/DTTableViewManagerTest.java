package com.ieatta.android.extensions.storage;

import android.test.InstrumentationTestCase;

import junit.framework.TestCase;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by djzhang on 12/1/15.
 */
public class DTTableViewManagerTest extends InstrumentationTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testInstanceOfClass() throws Exception {
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
}