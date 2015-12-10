package com.ieatta.android.modules.adapter;

/**
 * Created by djzhang on 12/1/15.
 */
public class NSIndexPath {
    public int row;
    public int section;

    public NSIndexPath(int section, int row) {
        this.section = section;
        this.row = row;
    }

    @Override
    public String toString() {
        return "NSIndexPath, section: " + section + " row: " + row;
    }
}
