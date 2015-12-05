package com.ieatta.com.parse.models.enums;

/**
 * Created by djzhang on 7/16/15.
 */
public enum PhotoUsedType {
    Photo_Used_Restaurant, // 0
    Photo_Used_Waiter,
    Photo_Used_Recipe, // 2
    Photo_Used_People,
    Photo_Used_Unknow;// Default

    public static int getInt(PhotoUsedType type){
        return type.ordinal();
    }

    public static PhotoUsedType fromInteger(int x) {
        switch (x) {
            case 0:
                return Photo_Used_Restaurant;
            case 1:
                return Photo_Used_Waiter;
            case 2:
                return Photo_Used_Recipe;
            case 3:
                return Photo_Used_People;
            case 4:
                return Photo_Used_Unknow;
        }
        return null;
    }

}
