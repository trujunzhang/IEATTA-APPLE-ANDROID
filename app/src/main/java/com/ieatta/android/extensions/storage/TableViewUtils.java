package com.ieatta.android.extensions.storage;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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
        LinkedList<Integer> indexs = new LinkedList<>(keySet);
        Collections.sort(indexs);


        Integer integer = indexs.get(0);
        int x = 0;
    }

    public int getItemCount() {
        return rowLength;
    }

    public RowModel getItem(int viewType) {
        return null;
    }
}
