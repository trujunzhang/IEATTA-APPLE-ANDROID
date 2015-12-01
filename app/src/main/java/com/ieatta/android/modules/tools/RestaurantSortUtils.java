package com.ieatta.android.modules.tools;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Restaurant;
import com.restaurant.tracking.parseasync.local.models.ParseModelProtocol;
import com.restaurant.tracking.parseasync.local.models.parse.Restaurant;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Created by djzhang on 8/9/15.
 */
public class RestaurantSortUtils {
    static class SortByName implements Comparator {
        public int compare(Object o1, Object o2) {
            Restaurant s1 = (Restaurant) o1;
            Restaurant s2 = (Restaurant) o2;
            return s1.displayName.toLowerCase().compareTo(s2.displayName.toLowerCase());
        }
    }

    public static LinkedList<Object> sort(LinkedList<Object> modelProtocols) {
        Collections.sort(modelProtocols, new SortByName());
        return modelProtocols;
    }
}
