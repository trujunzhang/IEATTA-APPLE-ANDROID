package com.ieatta.com.parse.models;

import com.ieatta.com.parse.ParseModelQuery;
import com.ieatta.com.parse.ParseModelSync;

import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;

import com.ieatta.com.parse.engine.realm.DBObject;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.ieatta.com.parse.engine.realm.DBQuery;
import com.ieatta.com.parse.ParseModelAbstract;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.List;

/**
 * Created by djzhang on 11/27/15.
 */
public class PeopleInEvent extends ParseModelSync {
    // Class key
    private static final String kPAPClassKey = "PeopleInEvent";

    // Field keys
    private static final String kPAPFieldUserKey = "userRef";
    private static final String kPAPFieldEventKey = "eventRef";

    public String userRef = "";
    public String eventRef = "";


    public PeopleInEvent() {
        super();
    }

    public PeopleInEvent(String userRef, String eventRef) {
        super();
        this.userRef = userRef;
        this.eventRef = eventRef;
    }

    public PeopleInEvent(String objectUUID, String userRef, String eventRef) {
        super(objectUUID);

        this.userRef = userRef;
        this.eventRef = eventRef;
    }

    // MARK: ParseModel
    DBQuery createQueryByEventRef(String eventRef) {
        DBQuery query = this.getDBQueryInstance();

        query.whereEqualTo(kPAPFieldEventKey, eventRef);
        query.orderByDescending(kPAPFieldObjectCreatedDateKey);

        return query;
    }

    @Override
    public String getParseTableName() {
        return kPAPClassKey;
    }

    @Override
    public PQueryModelType getModelType() {
        return PQueryModelType.PeopleInEvent;
    }

    @Override
    public void writeCommonObject(DBObject object) {
        object.put(kPAPFieldUserKey, this.userRef);
        object.put(kPAPFieldEventKey, this.eventRef);
    }

    @Override
    public void readCommonObject(DBObject object) {
        Object theUserRef = this.getValueFromObject(object, kPAPFieldUserKey);
        if (theUserRef != null) {
            this.userRef = (String) theUserRef;
        }
        Object theEventRef = this.getValueFromObject(object, kPAPFieldEventKey);
        if (theEventRef != null) {
            this.eventRef = (String) theEventRef;
        }
    }

    @Override
    public ParseModelAbstract getNewInstance() {
        return new PeopleInEvent();
    }

    public static Task<List<ParseModelAbstract>> queryOrderedPeople(String eventRef) {
        return Event.queryFromDatabase(PQueryModelType.PeopleInEvent, new PeopleInEvent().createQueryByEventRef(eventRef));
    }

    public static Team getPeople(PeopleInEvent peopleInEvent, List<ParseModelAbstract/*Team*/> fetchedPeople) {
        for (ParseModelAbstract model : fetchedPeople) {
            Team people = (Team) model;
            if (ParseModelAbstract.getPoint(people).equals(peopleInEvent.userRef)) {
                return people;
            }
        }

        return null;
    }

    public static Task<List<ParseModelAbstract>> sortOrderedPeople(Task<List<ParseModelAbstract>> previous, List<ParseModelAbstract/*PeopleInEvent*/> peopleInEvents) {
        List<ParseModelAbstract> fetchedPeople = previous.getResult();

        List<ParseModelAbstract> sortedPeople = new LinkedList<>();

        for (ParseModelAbstract peopleInEvent : peopleInEvents) {
            Team people = getPeople((PeopleInEvent) peopleInEvent, fetchedPeople);
            if (people != null) {
                sortedPeople.add(people);
            } else {
                // TODO djzhang(fixing)
//                return BFTask(error: NSError.getError(IEAErrorType.SortArray, description: "\(peopleInEvent.printDescription())"))
                return Task.forError(new Exception(""));
            }
        }

        return Task.forResult(sortedPeople);
    }

    public Task<Void> saveTeam() {
        return this.pinInBackgroundWithNewRecord();
    }

    // MARK: Description
    @Override
    public String printDescription() {
        return "PeopleInEvent{" +
                "objectUUID='" + objectUUID + '\'' +
                ", userRef='" + userRef + '\'' +
                ", eventRef='" + eventRef + '\'' +
                '}';
    }

}
