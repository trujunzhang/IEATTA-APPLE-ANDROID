package com.ieatta.android.extensions.storage;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by djzhang on 12/2/15.
 */
public class TableViewUtils {
    private TableViewUtils self = this;

    private LinkedHashMap<Integer, Integer> sectionInfo;
    private int rowLength = 0;

    public LinkedHashMap<Integer, SectionModel> sections = new LinkedHashMap<>();

    public void generateItems(LinkedHashMap<Integer, SectionModel> sections) {
        self.sections = sections;

        Set<Integer> keySet = sections.keySet();
        LinkedList<Integer> indexs = new LinkedList<>(keySet);
        Collections.sort(indexs);

        self.sectionInfo = new LinkedHashMap<>();
        self.rowLength = 0;
        for (Integer integer : indexs) {
            Integer itemsCountInSection = self.getItemsCountInSection(integer);
            rowLength += itemsCountInSection;
            sectionInfo.put(integer, itemsCountInSection);
        }
    }

    private Integer getItemsCountInSection(Integer integer) {
        SectionModel sectionModel = self.sections.get(integer);
        return sectionModel.numberOfItems();
    }

    public int getItemCount() {
        return rowLength;
    }

    public RowModel getItem(int viewType) {
        int total = 0;

        for (Integer key : self.sectionInfo.keySet()) {
            Integer count = self.sectionInfo.get(key);

            int begin = total;
            int end = total + count;

            if ((viewType + 1) >= begin && (viewType + 1) <= end) {
                int row = viewType - total;
                return self.sections.get(key).getRowModel(row);
            }
            total = end;
        }
        return null;
    }

    public int getItemViewType(int position) {
        int total = 0;

        for (Integer key : self.sectionInfo.keySet()) {
            Integer count = self.sectionInfo.get(key);

            int begin = total;
            int end = total + count;

            if ((position + 1) >= begin && (position + 1) <= end) {
                return key.intValue();
            }
            total = end;
        }
        return 0;
    }
}
