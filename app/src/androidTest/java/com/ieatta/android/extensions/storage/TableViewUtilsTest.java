package com.ieatta.android.extensions.storage;

import android.test.InstrumentationTestCase;
import android.test.InstrumentationTestSuite;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.LinkedHashMap;

/**
 * Created by djzhang on 12/2/15.
 */

class HeaderTableItem {
    String title;

    public HeaderTableItem(String title) {
        this.title = title;
    }
}

class FooterTableItem {
    String subTitle;

    public FooterTableItem(String subTitle) {
        this.subTitle = subTitle;
    }
}

class TableItem {
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

        // 5(2+2)
        TableItem[] array5 = {new TableItem(50), new TableItem(51)};// 2
        HeaderModel headerModel5 = new HeaderModel(new HeaderTableItem("header_555"));
        FooterModel footerModel5 = new FooterModel(new FooterTableItem("footer_555"));
        sections.put(new Integer(5), new SectionModel(5).setItems(array5).setHeaderModel(headerModel5).setFooterModel(footerModel5));

        // 4
        HeaderModel headerModel4 = new HeaderModel(new HeaderTableItem("header_444"));
        TableItem[] array4 = {new TableItem(40), new TableItem(41), new TableItem(42)};// 3
        sections.put(new Integer(4), new SectionModel(4).setItems(array4).setHeaderModel(headerModel4));

        // 1
        TableItem[] array1 = {new TableItem(10)};// 1
        FooterModel footerModel1 = new FooterModel(new FooterTableItem("footer_111"));
        sections.put(new Integer(1), new SectionModel(1).setItems(array1).setFooterModel(footerModel1));

        // 0
        TableItem[] array0 = {new TableItem(0), new TableItem(1)}; // 2
        sections.put(new Integer(0), new SectionModel(0).setItems(array0));

        tableViewUtils.generateItems(sections);

        // Verify the total rows count.
        int itemCount = tableViewUtils.getItemCount();
        int expectItemCount = (0 + array0.length + 0) + (0 + array1.length + 1) + (1 + array4.length + 0) + (1 + array5.length + 1);
        Assert.assertEquals("row count.", itemCount, expectItemCount);

        // Verify that getRowModel.
        verifyRowModel(tableViewUtils, 0, array0[0].position);
        verifyRowModel(tableViewUtils, 1, array0[1].position);
        verifyRowModel(tableViewUtils, 2, array1[0].position);
        verifyRowModel(tableViewUtils, 3, array4[0].position);
        verifyRowModel(tableViewUtils, 4, array4[1].position);
        verifyRowModel(tableViewUtils, 5, array4[2].position);
        verifyRowModel(tableViewUtils, 6, array5[0].position);
    }

    private void verifyRowModel(TableViewUtils tableViewUtils, int viewType, int expectPosition) {
        RowModel item = tableViewUtils.getItem(viewType);
        TableItem model = (TableItem) item.model;
        int position = model.position;
        Assert.assertEquals("The same row Model.", position, expectPosition);
    }
}