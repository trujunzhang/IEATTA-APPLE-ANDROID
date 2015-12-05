package com.ieatta.com.parse;

import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.com.parse.models.NewRecord;
import com.ieatta.com.parse.models.Team;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.ieatta.com.parse.models.enums.ParseModelFlag;
import com.ieatta.com.parse.models.enums.PhotoUsedType;
import com.ieatta.com.parse.models.enums.ReviewType;
import com.lukazakrajsek.timeago.TimeAgo;
import com.parse.ParseACL;
import com.parse.ParseObject;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import bolts.Task;
import bolts.TaskCompletionSource;

/**
 * Created by djzhang on 12/6/15.
 */
public abstract class ParseModelConvert extends ParseJsoner {
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
            return Task.forResult(self);
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
     * @param getFirstObjectTask
     * @return
     */
    public Task<Boolean> convertToOnlineModelTask(Task<Object> getFirstObjectTask) {
        Object result = getFirstObjectTask.getResult();
        if (result != null) {
            ParseObject firstObject = (ParseObject) result;
            this.readObject(firstObject);

            return Task.forResult(true);
        }

//        return BFTask(error: NSError.getError(IEAErrorType.FirstObject, description: "\(this.printDescription())"))
        return Task.forError(new Exception(""));
    }


    //
    static public ParseModelAbstract convertToFirstLocalModel(Object result, ParseModelAbstract instance) {
//        List<Object> array =  result;
//        if (array.size() == 1) {
//            return instance.convertToLocalModel(array.get(0));
//        }

        return null;
    }

}
