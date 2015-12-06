package com.ieatta.com.parse.models;

import com.ieatta.com.parse.ParseModelQuery;
import com.ieatta.com.parse.ParseModelSync;

import bolts.Task;

import com.parse.Parse;
import com.parse.ParseObject;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.enums.PhotoUsedType;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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

    public Team(String displayName, String email, String address, int sampleFileName) {
        this.displayName = displayName;
        this.email = email;
        this.address = address;
        this.sampleFileName = sampleFileName;
    }

    // MARK: Anonymous User
    public static Team getAnonymousUser() {
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
    public PQueryModelType getModelType() {
        return PQueryModelType.Team;
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

    private static boolean checkExist(ParseModelAbstract user, List<Team> inSource) {
        for (Team model : inSource) {
            if (model.equals(user)) {
                return true;
            }
        }
        return false;
    }

    // MARK: Support methods.
    public static Task<List<ParseModelAbstract>> filterFrom(Task<List<ParseModelAbstract>> previous, List<Team> source) {

        List<ParseModelAbstract> result = previous.getResult();

        List<ParseModelAbstract> filterUser = new LinkedList<>();
        for (ParseModelAbstract model : result) {
            if (checkExist(model, source) == false) {
                filterUser.add(model);
            }
        }

        return Task.forResult(filterUser);
    }

    public static Task<List<ParseModelAbstract>> queryTeam() {
        return ParseModelQuery.queryFromDatabase(PQueryModelType.Team, new Team().makeParseQuery());
    }

    public static Task<List<ParseModelAbstract>>  queryTeamByPoints(List<String> points){
        return new  Team().queryParseModels(PQueryModelType.Team, points);
    }

    public static Task<List<ParseModelAbstract>> queryTeamByPeopleInEvent(List<ParseModelAbstract> list) {
        // 1. Get ordered people reference.
        return new Team().queryParseModels(PQueryModelType.Team, Team.getPeoplePoints(list));
    }

    // MARK: Support methods.
    public static List<String> getPeoplePoints(List<ParseModelAbstract> peopleInEvent) {
        List<String> peoplePoints = new LinkedList<>();

        for (ParseModelAbstract model : peopleInEvent) {
            peoplePoints.add(((PeopleInEvent) model).userRef);
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


    static List<Team> convertToTeamArray(List<ParseObject> objectArray) {
        List<Team> array = new LinkedList<>();

        for (ParseObject object : objectArray) {
            array.add((Team) convertToModel(object, new Team()));
        }

        return array;
    }


}
