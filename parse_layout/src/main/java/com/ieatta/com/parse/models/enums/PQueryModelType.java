package com.ieatta.com.parse.models.enums;

/**
 * Created by djzhang on 7/15/15.
 */



public enum PQueryModelType {
    Recipe,    // 0
    Photo,
    Team,
    Review,
    Event,
    Restaurant,

    NewRecord,// 6

    PeopleInEvent,// 7

    Waiter, //8//(unused)
    ServedWaiter,//9//(unused)

    unkown;// 10

    public static int getInt(PQueryModelType type){
        return type.ordinal();
    }

    public static PQueryModelType fromInteger(int x) {
        switch(x) {
            case 0:
                return Recipe;
            case 1:
                return Photo;
            case 2:
                return Team;
            case 3:
                return Review;
            case 4:
                return Event;
            case 5:
                return Restaurant;
            case 6:
                return NewRecord;
            case 7:
                return PeopleInEvent;
            case 8:
                return Waiter;
            case 9:
                return ServedWaiter;
            case 10:
                return unkown;
        }
        return null;
    }

    public static String getString(PQueryModelType type){
        String[] PQeuryModelTypeNames = {
                "Recipe",
                "Photo",
                "Team",
                "Review",
                "Event",
                "Restaurant",

                "NewRecord",

                "PeopleInEvent",

                "Waiter",
                "ServedWaiter",

                "unkown",
        };
        return PQeuryModelTypeNames[PQueryModelType.getInt(type)];
    }

}

