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

}
