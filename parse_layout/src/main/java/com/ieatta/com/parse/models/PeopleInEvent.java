package com.ieatta.com.parse.models;

import com.ieatta.com.parse.ParseModelQuery;
import com.ieatta.com.parse.ParseModelSync;

import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;

import com.parse.ParseObject;
import com.ieatta.com.parse.models.enums.PQeuryModelType;
import com.parse.ParseQuery;
import com.ieatta.com.parse.ParseModelAbstract;

import java.util.Collection;
import java.util.LinkedList;
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
    public PQeuryModelType getModelType() {
        return PQeuryModelType.PeopleInEvent;
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

    public static Task<Object> queryOrderedPeople(String eventRef) {
        return ParseModelQuery.findLocalObjectsInBackground(new PeopleInEvent().createQueryByEventRef(eventRef)).continueWith(new Continuation<List<ParseObject>, Object>() {
            @Override
            public Object then(Task<List<ParseObject>> task) throws Exception {
                LinkedList<ParseModelAbstract> array = new PeopleInEvent().convertToParseModelArray(task.getResult(), true);

                TaskCompletionSource nextTask = new TaskCompletionSource();
                nextTask.setResult(array);
                return nextTask;
            }
        });
    }

    public static Team getPeople(PeopleInEvent peopleInEvent, LinkedList<Team> fetchedPeople) {
        for (Team people : fetchedPeople) {
            if (ParseModelAbstract.getPoint(people).equals(peopleInEvent.userRef)) {
                return people;
            }
        }

        return null;
    }

    public static Task<Object> sortOrderedPeople(Task<Object> previous, LinkedList<PeopleInEvent> peopleInEvents) {
        LinkedList<Team> fetchedPeople = new LinkedList<>((Collection<? extends Team>) previous.getResult());

        LinkedList<Team> sortedPeople = new LinkedList<>();

        for (PeopleInEvent peopleInEvent : peopleInEvents) {
            Team people = getPeople(peopleInEvent, fetchedPeople);
            if (people != null) {
                sortedPeople.add(people);
            } else {
                // TODO djzhang(fixing)
//                return BFTask(error: NSError.getError(IEAErrorType.SortArray, description: "\(peopleInEvent.printDescription())"))
            }
        }

        TaskCompletionSource finishTask = new TaskCompletionSource();
        finishTask.setResult(sortedPeople);
        return finishTask.getTask();
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
