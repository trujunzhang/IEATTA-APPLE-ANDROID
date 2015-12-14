package com.ieatta.com.parse.models.enums;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Event;
import com.ieatta.com.parse.models.Recipe;
import com.ieatta.com.parse.models.Restaurant;

/**
 * Created by djzhang on 7/16/15.
 */
public enum ReviewType {
    Review_Restaurant,
    Review_Recipe,
    Review_Event,
    Review_Unknow;// Default

    public static int getInt(ReviewType type) {
        return type.ordinal();
    }

    public static ReviewType fromInteger(int x) {
        switch (x) {
            case 0:
                return Review_Restaurant;
            case 1:
                return Review_Recipe;
            case 2:
                return Review_Event;
            case 3:
                return Review_Unknow;
        }
        return null;
    }

    public static ParseModelAbstract getParseModelInstance(int type) {
        int object = type;
        if (type == ReviewType.Review_Restaurant.ordinal()) {
            return new Restaurant();
        } else if (type == ReviewType.Review_Recipe.ordinal()) {
            return new Recipe();
        } else if (type == ReviewType.Review_Event.ordinal()) {
            return new Event();
        }
        return null;
    }

}

