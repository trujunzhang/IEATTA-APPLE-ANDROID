package com.ieatta.com.parse.models.enums;

/**
 * Created by djzhang on 7/16/15.
 */
public enum ReviewType {
    Review_Restaurant,
    Review_Recipe,
    Review_Event,
    Review_Unknow;// Default

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

}

