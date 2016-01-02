package com.ieatta.android.modules.common.edit.enums;

/**
 * Created by djzhang on 12/1/15.
 */
public enum IEAEditKey {

    Unknow {
        public String toString() {
            return "Unknow";
        }
    },

    //******************************************
    // the following Sections
    Section_Title {
        public String toString() {
            return "section_title";
        }
    },
    //******************************************
    // the following Rows
    // Photo Gallery
    photo_gallery {
        public String toString() {
            return "photo_gallery";
        }
    },
    // Restaurant
    rest_name {
        public String toString() {
            return "rest_name";
        }
    },
    rest_address {
        public String toString() {
            return "rest_address";
        }
    },

    rest_citystate {
        public String toString() {
            return "rest_citystate";
        }
    },
    // Event
    event_name {
        public String toString() {
            return "event_name";
        }
    },

    event_nameofserver {
        public String toString() {
            return "event_nameofserver";
        }
    },

    event_writeareview {
        public String toString() {
            return "event_writeareview";
        }
    },

    event_starttime {
        public String toString() {
            return "event_starttime";
        }
    },

    event_endtime {
        public String toString() {
            return "event_endtime";
        }
    },

    event_GPS_Location {
        public String toString() {
            return "event_GPS_Location";
        }
    },
    // People
    person_name {
        public String toString() {
            return "person_name";
        }
    },

    person_address {
        public String toString() {
            return "person_address";
        }
    },

    person_email {
        public String toString() {
            return "person_email";
        }
    },
    // Recipe
    recipe_name {
        public String toString() {
            return "recipe_name";
        }
    },

    recipe_price {
        public String toString() {
            return "recipe_price";
        }
    },
    add_Photo {
        public String toString() {
            return "add_photo";
        }
    },
}
