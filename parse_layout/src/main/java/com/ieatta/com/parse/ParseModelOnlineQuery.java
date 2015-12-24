package com.ieatta.com.parse;

import com.ieatta.com.parse.engine.realm.DBObject;
import com.ieatta.com.parse.engine.realm.LocalQuery;
import com.ieatta.com.parse.models.NewRecord;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 12/23/15.
 */
public abstract class ParseModelOnlineQuery extends ParseModelConvert {
    private ParseModelOnlineQuery self = this;

    public ParseModelOnlineQuery(String objectUUID) {
        super(objectUUID);
    }

    public ParseModelOnlineQuery() {
        super();
    }

    /**
     * Query the server table from the last fetched date. and sort from oldest to newest.
     * <p/>
     * - parameter lastAsyncDate: the last fetched date
     * <p/>
     * - returns: query's instance
     */
    public ParseQuery createQueryForPullObjectsFromServer(Date lastAsyncDate, int limit) {
        final String kPAPFieldModelOnlineCreatedAtKey = "createdAt";
        ParseQuery query = ParseQuery.getQuery(self.getParseTableName());
        query.setLimit(limit);

        // *** Important (used orderByAscending) ***
        query.orderByAscending(kPAPFieldModelOnlineCreatedAtKey);

        if (lastAsyncDate != null) {
            query.whereGreaterThan(kPAPFieldModelOnlineCreatedAtKey, lastAsyncDate);
        }

        return query;
    }

    ParseQuery createParseQueryByUUID() {
        ParseQuery query = self.makeParseQuery();

        query.whereEqualTo(kPAPFieldObjectUUIDKey, self.objectUUID);

        return query;
    }

    protected ParseQuery getParseQueryInstance() {
        return ParseQuery.getQuery(self.getParseTableName());
    }

    public ParseQuery makeParseQuery() {
        ParseQuery query = self.getParseQueryInstance();

        query.orderByDescending(kPAPFieldObjectCreatedDateKey);
        return query;
    }

    private ParseObject makeObject() {
        ParseObject object = self.createObject();

        ParseACL acl = self.getACL();
        object.setACL(acl);

        return object;
    }

    protected ParseQuery createSearchDisplayNameForParseQuery(String keyword) {
        ParseQuery query = self.makeParseQuery();

        query.whereMatches(kPAPFieldDisplayNameKey, keyword, "i");

        return query;
    }

    protected Task upInBackground() {
        ParseQuery query = self.getParseQueryInstance();
        query.whereEqualTo(kPAPFieldObjectUUIDKey, self.objectUUID);
        // *** Important ***
        query.fromLocalDatastore();

        return query.findInBackground().onSuccessTask(new Continuation() {
            @Override
            public Object then(Task task) throws Exception {
                Object result = task.getResult();
                LinkedList<ParseObject> objects = new LinkedList<ParseObject>((Collection<? extends ParseObject>) result);
                if (objects.isEmpty() == false) {
                    return objects.getFirst().unpinInBackground("Offline");
                }
                return Task.forResult(null);
            }
        });
    }

    protected Task pinInBackground() {
        ParseObject object = makeObject();
        self.writeLocalObject(object);

        return object.pinInBackground("Offline");
    }

    public static Task<List<ParseModelAbstract>> queryFromParse(final PQueryModelType type, ParseQuery query) {
        return ParseModelOnlineQuery.findInBackgroundFromParse(query).onSuccessTask(new Continuation() {
            @Override
            public Object then(Task task) throws Exception {
                return ((ParseModelConvert) ParseModelAbstract.getInstanceFromType(type)).convertToParseModelsTask(task, true);
            }
        });
    }

    public static Task findInBackgroundFromParse(ParseQuery query) /*ParseModelAbstract*/ {
        // *** Important ***
        query.fromLocalDatastore();

        return query.findInBackground();
    }

    public Task getFirstModelTaskFromParse() {
        ParseQuery query = self.createParseQueryByUUID();

        // *** Important ***
        query.fromLocalDatastore();

        return query.findInBackground().onSuccessTask(new Continuation() {
            @Override
            public Object then(Task task) throws Exception {
                LinkedList<ParseObject> list = new LinkedList<ParseObject>((Collection<? extends ParseObject>) task.getResult());
                if (list.getFirst() != null) {
                    ParseModelAbstract model = self.convertToLocalModel(list.getFirst());
                    return Task.forResult(model);
                }

                // **** Important ****
                // Here, return value is 'null' means that not found object.
                // For example, if all newrecord objects already pushed to server.
                // No NewRecord rows on the local table. So not found NewRecord here.
                return Task.forResult(null);
            }
        });

    }


}
