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
import com.ieatta.com.parse.models.enums.PQeuryModelType;
import com.ieatta.com.parse.models.enums.PhotoUsedType;
import com.ieatta.com.parse.models.enums.ReviewType;

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

    static Task<Object> queryOrderedPeople(String eventRef) {
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

//    static Task<Object> sortOrderedPeople(previous:BFTask,peopleInEvents:[PeopleInEvent]){
//        let fetchedPeople = previous.result as! [ParseUser]
//    public String sortedPeople = [ParseUser]()
//
//        void   getPeople(peopleInEvent: PeopleInEvent) -> ParseUser?{
//        for people in fetchedPeople{
//            if(ParseModelAbstract.getPoint(people) == peopleInEvent.userRef){
//                return people
//            }
//        }
//        return nil
//        }
//
//        for peopleInEvent in peopleInEvents{
//            if let people = getPeople(peopleInEvent){
//                sortedPeople.append(people)
//            }else{
//                return BFTask(error: NSError.getError(IEAErrorType.SortArray, description: "\(peopleInEvent.printDescription())"))
//            }
//        }
//
//        return BFTask(result: sortedPeople)
//    }

//    public Task<Object>   saveParseUser()   {
//        return this.pinInBackgroundWithNewRecord();
//    }

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
