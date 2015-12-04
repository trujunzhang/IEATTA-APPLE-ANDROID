package com.ieatta.com.parse.models;

import com.ieatta.com.parse.ParseModelQuery;
import com.ieatta.com.parse.ParseModelSync;

import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;

import com.parse.ParseObject;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.parse.ParseQuery;
import com.ieatta.com.parse.ParseModelAbstract;

import java.util.Collection;
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
    ParseQuery createQueryByEventRef(String eventRef) {
        ParseQuery query = this.getParseQueryInstance();

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
    public void writeCommonObject(ParseObject object) {
        object.put(kPAPFieldUserKey, this.userRef);
        object.put(kPAPFieldEventKey, this.eventRef);
    }


    @Override
    public void readCommonObject(ParseObject object) {
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

    public static Team getPeople(PeopleInEvent peopleInEvent, List<Team> fetchedPeople) {
        for (Team people : fetchedPeople) {
            if (ParseModelAbstract.getPoint(people).equals(peopleInEvent.userRef)) {
                return people;
            }
        }

        return null;
    }

    public static Task<Object> sortOrderedPeople(Task<Object> previous, List<PeopleInEvent> peopleInEvents) {
        List<Team> fetchedPeople = (List<Team>) previous.getResult();

//        List<Team> sortedPeople = new List<>();
//
//        for (PeopleInEvent peopleInEvent : peopleInEvents) {
//            Team people = getPeople(peopleInEvent, fetchedPeople);
//            if (people != null) {
//                sortedPeople.add(people);
//            } else {
//                // TODO djzhang(fixing)
////                return BFTask(error: NSError.getError(IEAErrorType.SortArray, description: "\(peopleInEvent.printDescription())"))
//            }
//        }
//
//        TaskCompletionSource finishTask = new TaskCompletionSource();
//        finishTask.setResult(sortedPeople);
//        return finishTask.getTask();

        return null;
    }

    public Task<Object> saveTeam() {
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
