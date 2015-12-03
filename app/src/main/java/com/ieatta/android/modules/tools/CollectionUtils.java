package com.ieatta.android.modules.tools;

import java.util.LinkedList;

/**
 * Created by djzhang on 12/3/15.
 */
public class CollectionUtils {

    public static LinkedList createList(Object item){
        LinkedList list = new LinkedList();
        list.add(item);
        return list;
    }
}
