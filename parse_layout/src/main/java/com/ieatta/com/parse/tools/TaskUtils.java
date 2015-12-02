package com.ieatta.com.parse.tools;

import java.util.LinkedList;

import bolts.Task;

/**
 * Created by djzhang on 12/2/15.
 */
public class TaskUtils {
    public static LinkedList<Object> getResultFromTask(Task task){
        return  (LinkedList<Object>) ((Task)(task.getResult())).getResult();
    }
}
