package com.ieatta.android.extensions.storage;

import android.test.InstrumentationTestCase;
import android.test.InstrumentationTestSuite;

import junit.framework.TestCase;

import java.util.LinkedHashMap;

/**
 * Created by djzhang on 12/2/15.
 */
class TableItem{
    int position;

    public TableItem(int position) {
        this.position = position;
    }
}
public class TableViewUtilsTest extends InstrumentationTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testSort() throws Exception {
        TableViewUtils tableViewUtils = new TableViewUtils();

        LinkedHashMap<Integer, SectionModel> sections = new LinkedHashMap<>();
        Object[] array5 = {new TableItem(0),new TableItem(1)};// 2
        sections.put(new Integer(5),new SectionModel(5).setItems(array5));
        Object[] array4 = {new TableItem(0),new TableItem(1),new TableItem(2)};// 3
        sections.put(new Integer(4),new SectionModel(4).setItems(array4));
        Object[] array1 = {new TableItem(0)};// 1
        sections.put(new Integer(1),new SectionModel(1).setItems(array1));
        Object[] array0 = {new TableItem(0),new TableItem(1)}; // 2
        sections.put(new Integer(0),new SectionModel(0).setItems(array0));

        tableViewUtils.generateItems(sections);

    }
}