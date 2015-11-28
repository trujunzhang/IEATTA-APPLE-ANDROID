package com.ieatta.com.parse;
import bolts.Task;

import com.ieatta.com.parse.models.enums.PQeuryModelType;
import com.ieatta.com.parse.models.enums.ParseModelFlag;
import com.ieatta.com.parse.models.enums.PhotoUsedType;
import com.ieatta.com.parse.models.enums.ReviewType;
import com.parse.ParseObject;
import bolts.Task;
/**
 * Created by djzhang on 11/27/15.
 */
public abstract class ParseJsoner extends ParseModelAbstract{
    public ParseJsoner(String objectUUID) {
        super(objectUUID);
    }

    public ParseJsoner() {
        super();
    }
}
