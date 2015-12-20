package com.ieatta.com.parse.models;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.ParseModelQuery;
import com.ieatta.com.parse.ParseModelSync;
import com.ieatta.com.parse.engine.realm.DBQuery;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.ieatta.com.parse.models.enums.PhotoUsedType;
import com.ieatta.com.parse.models.enums.ReviewType;
import com.parse.ParseObject;
import com.twofortyfouram.assertion.Assertions;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

//import com.twofortyfouram.assertion;

/**
 * Created by djzhang on 11/27/15.
 */
public class Event extends ParseModelSync {
    private Event self = this;
    // Class key
    private static final String kPAPClassKey = "Event";

    // Field keys
    private static final String kPAPFieldStartDateKey = "startDate";
    private static final String kPAPFieldEndDateKey = "endDate";
    private static final String kPAPFieldWhatToEatKey = "whatToEat";
    private static final String kPAPFieldRemarksKey = "remarks";

    private static final String kPAPFieldWaiterKey = "waiter";
    private static final String kPAPFieldLocalRestaurantKey = "restaurantRef";

    public Date startDate = new Date();
    public Date endDate;
    public String whatToEat = "";
    public String remarks = "";
    public String waiter = "";
    public String restaurantRef = "";

    // Variables invoked by viewController
    public Restaurant belongToModel;

    public Event() {
        super();
        self.startDate = new Date();
        self.endDate = self.getNextHourDate();
    }

    public Event(Restaurant belongToModel) {
        this();
        this.belongToModel = belongToModel;
        this.restaurantRef = ParseModelAbstract.getPoint(belongToModel);
    }

    private Date getNextHourDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.HOUR, 1);
        Date d = c.getTime();
        return d;
    }

    public Event(String displayName, Date startDate, Date endDate, String content, String remarks) {
        this();
        self.displayName = displayName;
        self.startDate = startDate;
        self.endDate = endDate;
        self.whatToEat = content;
        self.remarks = remarks;
    }

    // MARK: ParseModel
    public DBQuery createQueryByRestaurantRef(Restaurant restaurant) {
        DBQuery query = this.makeDBQuery();

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
    public PQueryModelType getModelType() {
        return PQueryModelType.Event;
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
    public Task<Boolean> queryBelongToTask(ParseModelAbstract belongTo) {
        return this.getFirstLocalModelArrayTask().onSuccessTask(new Continuation<ParseModelAbstract, Task<Boolean>>() {
            @Override
            public Task<Boolean> then(Task<ParseModelAbstract> task) throws Exception {
                return ParseModelAbstract.getInstanceFromType(PQueryModelType.Restaurant, self.restaurantRef).queryBelongToTask(self);
            }
        });
    }

    public Task<List<ParseModelAbstract>> queryParseModels(String keyword) {
        return Event.queryFromDatabase(PQueryModelType.Event, new Event().createSearchDisplayNameQuery(keyword));
    }

    public static Task<List<ParseModelAbstract>> queryEventsRelatedRestaurant(Restaurant restaurant) {
        return ParseModelQuery.queryFromDatabase(PQueryModelType.Event, new Event().createQueryByRestaurantRef(restaurant));
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
