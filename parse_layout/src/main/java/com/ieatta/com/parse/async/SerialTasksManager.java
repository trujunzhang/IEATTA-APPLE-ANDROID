package com.ieatta.com.parse.async;

import com.ieatta.com.parse.engine.realm.DBObject;

import java.util.LinkedList;
import java.util.List;

import bolts.Task;

/**
 * Created by djzhang on 12/16/15.
 */
public class SerialTasksManager {
    private SerialTasksManager self = this;
    private List<DBObject> array = new LinkedList<>();

    public SerialTasksManager(List<DBObject> array) {
        this.array = array;
    }

    // MARK: Fetch object step by step.
    int taskStep = 0;

//    public Task hasItemTask(){
//        if(taskStep < self.array.size()){
//            return Task.forResult(true);
//        }
//
//        return BFTask(error: NSError.getError(IEAErrorType.EmptyArray, description: ""))
//    }

    public boolean hasNext() {
        if (taskStep < self.array.size()) {
            return true;
        }

        return false;
    }

//    func nextTask() -> BFTask{
//        if(self.hasNext() == false){
//            return BFTask(error: NSError.getError(IEAErrorType.EmptyArray, description: ""))
//        }
//
//        let model: PFObject = self.array[self.taskStep]
//        self.taskStep += 1
//
//        return BFTask(result: model)
//    }

    public DBObject next() {
        DBObject model = self.array.get(self.taskStep);
        self.taskStep += 1;

        return model;
    }

    public int index() {
        return self.taskStep;
    }

    public int getFetchedCount() {
        return self.array.size();
    }


}
