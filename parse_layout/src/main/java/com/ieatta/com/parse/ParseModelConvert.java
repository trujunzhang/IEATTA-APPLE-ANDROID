package com.ieatta.com.parse;

import com.parse.ParseObject;

import java.util.LinkedList;
import java.util.List;

import bolts.Task;

/**
 * Created by djzhang on 12/6/15.
 */
public abstract class ParseModelConvert extends ParseJsoner {
    private ParseModelConvert self = this;

    public ParseModelConvert(String objectUUID) {
        super(objectUUID);
    }

    public ParseModelConvert() {
        super();
    }


    public Task<ParseModelAbstract> convertToLocalModelTask(Task<ParseObject> firstObjectTask) {
        ParseObject firstObject = firstObjectTask.getResult();
        if (firstObject != null) {
            this.readObjectLocal(firstObject);
            return Task.forResult((ParseModelAbstract) self);
        }

//        return BFTask(error: NSError.getError(IEAErrorType.FirstObject, description: "\(this.printDescription())"))
        return Task.forError(new NullPointerException("not found the first object"));
    }

    /**
     * Convert fetched result's value as ParseObject array to Model array.
     * <p/>
     * - parameter value: result's value
     * - parameter offline: offline is true,online is false
     * <p/>
     * - returns: ParseModelAbstract's array
     */
    public List<ParseModelAbstract> convertToParseModelArray(List<ParseObject> value, boolean offline) {
        LinkedList<ParseModelAbstract> array = new LinkedList<>();

        for (ParseObject object : value) {
            ParseModelAbstract instance = this.getNewInstance();
            if (offline == true) {
                instance.readObjectLocal(object);
            } else {
                instance.readObject(object);
            }
            array.add(instance);
        }

        return array;
    }

    public Task<List<ParseModelAbstract>> convertToParseModelsTask(Task<List<ParseObject>> previous, boolean offline) {
        List<ParseModelAbstract> array = new LinkedList<>();

        for (ParseObject object : previous.getResult()) {
            ParseModelAbstract instance = this.getNewInstance();
            if (offline == true) {
                instance.readObjectLocal(object);
            } else {
                instance.readObject(object);
            }
            array.add(instance);
        }

        return Task.forResult(array);
    }

    public ParseModelAbstract convertToLocalModel(Object object) {
        ParseModelAbstract instance = this.getNewInstance(); // Alloc a new instance.
        instance.readObjectLocal((ParseObject) object);
        return instance;
    }

    public static ParseModelAbstract convertToLocalModel(Object object, ParseModelAbstract instance) {
        instance.readObjectLocal((ParseObject) object);
        return instance;
    }

    public static ParseModelAbstract convertToOnlineModel(Object object, ParseModelAbstract instance) {
        instance.readObject((ParseObject) object);
        return instance;
    }

    /**
     * Convert from the online PFObject to the Model instance.
     *
     * @param previous
     * @return
     */
    public Task<Boolean> convertToOnlineModelTask(Task<ParseObject> previous) {
        ParseObject firstObject = previous.getResult();

        if (firstObject != null) {
            this.readObject(firstObject);
            return Task.forResult(true);
        }

//        return BFTask(error: NSError.getError(IEAErrorType.FirstObject, description: "\(this.printDescription())"))
        return Task.forError(new Exception(""));
    }


//    //
//    static public ParseModelAbstract convertToFirstLocalModel(Object result, ParseModelAbstract instance) {
////        List<Object> array =  result;
////        if (array.size() == 1) {
////            return instance.convertToLocalModel(array.get(0));
////        }
//
//        return null;
//    }

}
