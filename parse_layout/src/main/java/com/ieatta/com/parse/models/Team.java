package com.ieatta.com.parse.models;

import com.ieatta.com.parse.ParseModelQuery;
import com.ieatta.com.parse.ParseModelSync;

import bolts.Task;

import com.parse.ParseObject;
import com.ieatta.com.parse.models.enums.PQeuryModelType;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.enums.PhotoUsedType;

import java.util.Collection;
import java.util.LinkedList;

import bolts.TaskCompletionSource;

/**
 * Created by djzhang on 11/27/15.
 */
public class Team extends ParseModelSync {

    public static final String DEFAULT_USER_NAME = "Anonymous";
    public static final int DEFAULT_RECIPES_COUNT = Integer.MIN_VALUE;

    // Class key
    private static final String kPAPClassKey = "Team";

    // Field keys
    private static final String kPAPFieldEmailKey = "email";
    private static final String kPAPFieldAddressKey = "address";


    public String email = "";
    public String address = "";

    // The following is used in tableview
    public int recipesCount = DEFAULT_RECIPES_COUNT;

    // models belong to
    public Event belongToModel;
    public String writedReviewTimeAgo = "";

    public Team() {
        super();
    }

    public Team(Event belongToModel) {
        super();
        this.belongToModel = belongToModel;
    }

    // MARK: Anonymous User
    static Team getAnonymousUser() {
        Team people = new Team();
        people.displayName = DEFAULT_USER_NAME;

        return people;
    }

    // MARK: ParseModel
    @Override
    public String getParseTableName() {
        return kPAPClassKey;
    }

    @Override
    public PQeuryModelType getModelType() {
        return PQeuryModelType.ParseUser;
    }

    @Override
    public PhotoUsedType getPhotoUsedType() {
        return PhotoUsedType.Photo_Used_People;
    }

    @Override
    public String getRestaurantRef() {
        return ParseModelAbstract.getPoint((this.belongToModel.belongToModel));
    }

    @Override
    public void writeCommonObject(ParseObject object) {
        object.put(kPAPFieldDisplayNameKey, this.displayName);
        object.put(kPAPFieldEmailKey, this.email);
        object.put(kPAPFieldAddressKey, this.address);
    }

    @Override
    public void readCommonObject(ParseObject object) {
        Object theDisplayName = this.getValueFromObject(object, kPAPFieldDisplayNameKey);
        if (theDisplayName != null) {
            this.displayName = (String) theDisplayName;
        }

        Object theEmail = this.getValueFromObject(object, kPAPFieldEmailKey);
        if (theEmail != null) {
            this.email = (String) theEmail;
        }

        Object theAddress = this.getValueFromObject(object, kPAPFieldAddressKey);
        if (theAddress != null) {
            this.address = (String) theAddress;
        }
    }

    @Override
    public ParseModelAbstract getNewInstance() {
        return new Team();
    }

    private static boolean checkExist(Team user, LinkedList<Team> inSource) {
        for (Team model : inSource) {
            if (model.equals(user)) {
                return true;
            }
        }
        return false;
    }

    // MARK: Support methods.
    static Task<Object> filterFrom(Task<Object> previous, LinkedList<Team> source) {

        LinkedList<Team> result = new LinkedList<>((Collection<? extends Team>) previous.getResult());

        LinkedList<Team> filterUser = new LinkedList<>();
        for (Team model : result) {
            if (checkExist(model, source)) {
                filterUser.add(model);
            }
        }
        TaskCompletionSource finalTask = new TaskCompletionSource();
        finalTask.setResult(filterUser);
        return finalTask.getTask();
    }

    static Task<Object> queryParseUser() {
        return ParseModelQuery.queryFromDatabase(PQeuryModelType.ParseUser, new Team().makeParseQuery());
    }

    static Task<Object> queryParseUserByPoints(LinkedList<String> points) {
        return new Team().queryParseModels(PQeuryModelType.ParseUser, points);
    }

    static Task<Object> queryParseUser(LinkedList<PeopleInEvent> list) {
        // 1. Get ordered people reference.
        LinkedList<String> points = Team.getPeoplePoints(list);

        return new Team().queryParseModels(PQeuryModelType.ParseUser, points);
    }

    // MARK: Support methods.
    static LinkedList<String> getPeoplePoints(LinkedList<PeopleInEvent> peopleInEvent) {
        LinkedList<String> peoplePoints = new LinkedList<>();

        for (PeopleInEvent model : peopleInEvent) {
            peoplePoints.add(model.userRef);
        }

        return peoplePoints;
    }


    // MARK: Description
    @Override
    public String printDescription() {
        return "Team{" +
                "objectUUID='" + objectUUID + '\'' +
                ", displayName='" + displayName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }


    static LinkedList<Team> convertToParseUserArray(LinkedList<ParseObject> objectArray) {
        LinkedList<Team> array = new LinkedList<>();

        for (ParseObject object : objectArray) {
            array.add((Team) convertToModel(object, new Team()));
        }

        return array;
    }


}
