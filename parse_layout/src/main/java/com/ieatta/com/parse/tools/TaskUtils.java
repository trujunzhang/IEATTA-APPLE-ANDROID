package com.ieatta.com.parse.tools;

import java.util.LinkedList;

import bolts.Task;

/**
 * Created by djzhang on 12/2/15.
 */
public class TaskUtils {
    public static LinkedList getResultFromTask(Task task) {
        Object tmp = task.getResult();
        while (tmp.getClass().equals(Task.class)) {
            tmp = ((Task) tmp).getResult();
        }

        return (LinkedList) tmp;
    }
}
