package com.ieatta.android.extensions.storage;

import android.test.InstrumentationTestCase;
import android.test.InstrumentationTestSuite;

import junit.framework.TestCase;

import java.util.LinkedHashMap;

/**
 * Created by djzhang on 12/2/15.
 */
public class TableViewUtilsTest extends InstrumentationTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testSort() throws Exception {
        TableViewUtils tableViewUtils = new TableViewUtils();

        LinkedHashMap<Integer, SectionModel> sections = new LinkedHashMap<>();
        sections.put(new Integer(5),new SectionModel(5));
        sections.put(new Integer(4),new SectionModel(4));
        sections.put(new Integer(1),new SectionModel(1));
        sections.put(new Integer(0),new SectionModel(0));

        tableViewUtils.generateItems(sections);

    }
}