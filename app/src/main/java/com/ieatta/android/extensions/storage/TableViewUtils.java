package com.ieatta.android.extensions.storage;

import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Set;

/**
 * Created by djzhang on 12/2/15.
 */
public class TableViewUtils {
    private TableViewUtils self = this;

    private Object[] items ;
    private int rowLength = 0;

    public LinkedHashMap<Integer, SectionModel> sections = new LinkedHashMap<>();

    public void generateItems(LinkedHashMap<Integer, SectionModel> sections){
        self.sections = sections;
        Set<Integer> keySet = sections.keySet();

    }

    public int getItemCount() {
        return rowLength;
    }

    public RowModel getItem(int viewType) {
        return null;
    }
}
