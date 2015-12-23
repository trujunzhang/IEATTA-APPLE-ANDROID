package com.ieatta.com.parse;
import com.ieatta.com.parse.engine.realm.DBObject;
import com.ieatta.com.parse.engine.realm.LocalQuery;
import com.ieatta.com.parse.models.NewRecord;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 12/23/15.
 */
public abstract class ParseModelOnlineQuery extends ParseModelConvert{
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
}
