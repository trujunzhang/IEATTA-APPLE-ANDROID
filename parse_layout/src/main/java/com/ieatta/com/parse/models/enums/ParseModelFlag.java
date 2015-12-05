package com.ieatta.com.parse.models.enums;

/**
 * Created by djzhang on 11/27/15.
 */

public enum ParseModelFlag {
    Normal,
    deleted;

    public static int getInt(ParseModelFlag type){
        return type.ordinal();
    }

    public static ParseModelFlag fromInteger(int x) {
        switch (x) {
            case 0:
                return Normal;
            case 1:
                return deleted;
        }
        return null;
    }

}
