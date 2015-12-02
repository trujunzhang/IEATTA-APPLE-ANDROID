package com.ieatta.com.parse.models;

import com.ieatta.com.parse.ParseModelQuery;
import com.ieatta.com.parse.ParseModelSync;

import bolts.Continuation;
import bolts.Task;

import com.ieatta.com.parse.ParseModelSync;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.ieatta.com.parse.models.enums.PQeuryModelType;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.enums.PQeuryModelType;
import com.ieatta.com.parse.models.enums.PhotoUsedType;
import com.ieatta.com.parse.models.enums.ReviewType;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.enums.PQeuryModelType;
import com.ieatta.com.parse.models.enums.PhotoUsedType;
import com.ieatta.com.parse.models.enums.ReviewType;
import com.twofortyfouram.assertion.Assertions;

import java.util.Date;

//import com.twofortyfouram.assertion;

/**
 * Created by djzhang on 11/27/15.
 */
public class Event extends ParseModelSync {

    // Class key
    private static final String kPAPClassKey = "Event";

    // Field keys
    private static final String kPAPFieldStartDateKey = "startDate";
    private static final String kPAPFieldEndDateKey = "endDate";
    private static final String kPAPFieldWhatToEatKey = "whatToEat";
    private static final String kPAPFieldRemarksKey = "remarks";

    private static final String kPAPFieldWaiterKey = "waiter";
    private static final String kPAPFieldLocalRestaurantKey = "restaurantRef";

    public Date startDate;
    public Date endDate;
    public String whatToEat = "";
    public String remarks = "";
    public String waiter = "";
    public String restaurantRef = "";

    // Variables invoked by viewController
    public Restaurant belongToModel;

    public Event() {
        super();
    }

    public Event(Restaurant belongToModel) {
        super();
        this.belongToModel = belongToModel;
        this.restaurantRef = ParseModelAbstract.getPoint(belongToModel);
    }

    public Event(String s, Date date, Date date1, String s1, String remarks1) {




    }


    // MARK: ParseModel
    public ParseQuery createQueryByRestaurantRef(Restaurant restaurant) {
        ParseQuery query = this.makeParseQuery();

        query.whereEqualTo(kPAPFieldLocalRestaurantKey, ParseModelAbstract.getPoint(restaurant));

        return query;
    }

    @Override
    public ReviewType getReviewType() {
        return ReviewType.Review_Event;
    }

    @Override
    public PhotoUsedType getPhotoUsedType() {
        return PhotoUsedType.Photo_Used_Waiter;
    }

    @Override
    public String getParseTableName() {
        return kPAPClassKey;
    }

    @Override
    public PQeuryModelType getModelType() {
        return PQeuryModelType.Event;
    }

    @Override
    public String getRestaurantRef() {
        return ParseModelAbstract.getPoint((this.belongToModel));
    }

    @Override
    public void writeCommonObject(ParseObject object) {
        Assertions.assertNotEmpty(this.restaurantRef, "Must setup restaurantRef.");

        object.put(kPAPFieldDisplayNameKey, this.displayName);
        object.put(kPAPFieldStartDateKey, this.startDate);
        object.put(kPAPFieldEndDateKey, this.endDate);

        object.put(kPAPFieldWaiterKey, this.waiter);

        object.put(kPAPFieldWhatToEatKey, this.whatToEat);
        object.put(kPAPFieldRemarksKey, this.remarks);

        object.put(kPAPFieldLocalRestaurantKey, this.restaurantRef);
    }

    @Override
    public void readCommonObject(ParseObject object) {
        Object theRestaurantRef = this.getValueFromObject(object, kPAPFieldLocalRestaurantKey);
        if (theRestaurantRef != null) {
            this.restaurantRef = (String) theRestaurantRef;
        }

        Object theDisplayName = this.getValueFromObject(object, kPAPFieldDisplayNameKey);
        if (theDisplayName != null) {
            this.displayName = (String) theDisplayName;
        }

        Object theStartDate = this.getValueFromObject(object, kPAPFieldStartDateKey);
        if (theStartDate != null) {
            this.startDate = (Date) theStartDate;
        }

        Object theEndDate = this.getValueFromObject(object, kPAPFieldEndDateKey);
        if (theEndDate != null) {
            this.endDate = (Date) theEndDate;
        }

        Object theWaiter = this.getValueFromObject(object, kPAPFieldWaiterKey);
        if (theWaiter != null) {
            this.waiter = (String) theWaiter;
        }

        Object theWhatToEat = this.getValueFromObject(object, kPAPFieldWhatToEatKey);
        if (theWhatToEat != null) {
            this.whatToEat = (String) theWhatToEat;
        }

        Object theRemarks = this.getValueFromObject(object, kPAPFieldRemarksKey);
        if (theRemarks != null) {
            this.remarks = (String) theRemarks;
        }
    }

    @Override
    public ParseModelAbstract getNewInstance() {
        return new Event();
    }

    @Override
    public Task<Object> queryBelongToTask(ParseModelAbstract belongTo) {
        final Event self = this;
        return this.getFirstLocalModelArrayTask().continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
                return ParseModelAbstract.getInstanceFromType(PQeuryModelType.Restaurant, self.restaurantRef).queryBelongToTask(self);
            }
        });
    }

    public Task<Object> queryParseModels(String keyword) {
        return Event.queryFromDatabase(PQeuryModelType.Event, new Event().createSearchDisplayNameQuery(keyword));
    }

    public static Task<Object> queryEventsRelatedRestaurant(Restaurant restaurant) {
        return ParseModelQuery.queryFromDatabase(PQeuryModelType.Event, new Event().createQueryByRestaurantRef(restaurant));
    }

    // MARK: Description
    @Override
    public String printDescription() {
        return "Event{" +
                "objectUUID='" + objectUUID + '\'' +
                ", restaurantRef='" + restaurantRef + '\'' +
                ", displayName='" + displayName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", whatToEat='" + whatToEat + '\'' +
                ", remarks='" + remarks + '\'' +
                ", waiter='" + waiter + '\'' +
                '}';
    }

}
