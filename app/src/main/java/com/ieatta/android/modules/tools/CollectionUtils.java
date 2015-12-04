package com.ieatta.android.modules.tools;

import com.nostra13.universalimageloader.utils.L;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by djzhang on 12/3/15.
 */
public class CollectionUtils {

    public static List createList(Object item){
        LinkedList list = new LinkedList();
        list.add(item);
        return list;
    }

    public  static List createList(Object[] items){
        LinkedList list = new LinkedList(Arrays.asList(items));
        return list;
    }

}
