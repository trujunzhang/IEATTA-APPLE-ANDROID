package com.ieatta.android.extensions.storage;

import android.test.InstrumentationTestCase;
import android.test.InstrumentationTestSuite;

import junit.framework.Assert;
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

        TableItem[] array5 = {new TableItem(50),new TableItem(51)};// 2
        sections.put(new Integer(5),new SectionModel(5).setItems(array5));

        TableItem[] array4 = {new TableItem(40),new TableItem(41),new TableItem(42)};// 3
        sections.put(new Integer(4),new SectionModel(4).setItems(array4));

        TableItem[] array1 = {new TableItem(10)};// 1
        sections.put(new Integer(1),new SectionModel(1).setItems(array1));

        TableItem[] array0 = {new TableItem(0),new TableItem(1)}; // 2
        sections.put(new Integer(0),new SectionModel(0).setItems(array0));

        tableViewUtils.generateItems(sections);

        // Verify the total rows count.
        int itemCount = tableViewUtils.getItemCount();
        int expectItemCount = array0.length + array1.length+array4.length+array5.length;
        Assert.assertEquals("row count.", itemCount, expectItemCount);

        // Verify that getRowModel.
        verifyRowModel(tableViewUtils, 3, array4[0].position);
        verifyRowModel(tableViewUtils, 0, array0[0].position);
        verifyRowModel(tableViewUtils, 1, array0[1].position);
        verifyRowModel(tableViewUtils, 2, array1[0].position);
    }

    private void verifyRowModel(TableViewUtils tableViewUtils, int viewType, int expectPosition) {
        RowModel item = tableViewUtils.getItem(viewType);
        TableItem model = (TableItem) item.model;
        int position = model.position;
        Assert.assertEquals("The same row Model.", position, expectPosition);
    }
}