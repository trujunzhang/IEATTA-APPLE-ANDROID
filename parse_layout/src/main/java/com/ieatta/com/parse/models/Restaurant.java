package com.ieatta.com.parse.models;

import com.ieatta.com.parse.ParseModelQuery;
import com.ieatta.com.parse.ParseModelSync;

import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;

import com.ieatta.com.parse.R;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.parse.ParseQuery;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.enums.PhotoUsedType;
import com.ieatta.com.parse.models.enums.ReviewType;

import java.util.List;

/**
 * Created by djzhang on 11/27/15.
 */
public class Restaurant extends ParseModelSync {
    private  Restaurant self = this;
    public static String Default_FormattedAddress ="";

    public static final double PAWRestaurantMaximumSearchDistance = 1; // Value in kilometers

    // Class key
    private static final String kPAPClassKey = "Restaurant";

    // Field keys
    private static final String kPAPFieldLocationKey = "location";
    private static final String kPAPFieldAddressKey = "address";
    private static final String kPAPFieldCityStateKey = "cityState";

    public ParseGeoPoint location;

    // Variables invoked by viewController
    private String googleMapAddress = Default_FormattedAddress;

    // For testing.
    public Restaurant(String displayName, ParseGeoPoint location, String address, String city, int sampleFileName) {
        self.displayName = displayName;
        self.location = location;
        self.sampleFileName = sampleFileName;
    }

    public String getGoogleMapAddress() {
        if (this.googleMapAddress == null || this.googleMapAddress.equals("")) {
            return Default_FormattedAddress;
        }

        return this.googleMapAddress;
    }

    public Restaurant() {
        super();
    }

    // MARK: ParseModel
    ParseQuery createNearlyRestaurantQuery(ParseGeoPoint nearPoint) {
        ParseQuery query = this.getParseQueryInstance();

        // Restaurant list sorted by display name. From A..Z.
        query.orderByAscending(kPAPFieldDisplayNameKey);

        query.whereWithinKilometers(kPAPFieldLocationKey, nearPoint, PAWRestaurantMaximumSearchDistance);

        return query;
    }

    @Override
    public ReviewType getReviewType() {
        return ReviewType.Review_Restaurant;
    }

    @Override
    public PhotoUsedType getPhotoUsedType() {
        return PhotoUsedType.Photo_Used_Restaurant;
    }

    @Override
    public PQueryModelType getModelType() {
        return PQueryModelType.Restaurant;
    }

    @Override
    public String getParseTableName() {
        return kPAPClassKey;
    }

    @Override
    public String getRestaurantRef() {
        return ParseModelAbstract.getPoint(this);
    }

    @Override
    public void writeCommonObject(ParseObject object) {
        object.put(kPAPFieldDisplayNameKey, this.displayName);
        object.put(kPAPFieldLocationKey, this.location);
        object.put(kPAPFieldAddressKey, this.googleMapAddress);
    }

    @Override
    public void readCommonObject(ParseObject object) {
        Object theDisplayName = this.getValueFromObject(object, kPAPFieldDisplayNameKey);
        if (theDisplayName != null) {
            this.displayName = (String) theDisplayName;
        }

        Object theLocation = this.getValueFromObject(object, kPAPFieldLocationKey);
        if (theLocation != null) {
            this.location = (ParseGeoPoint) theLocation;
        }

        Object theAddress = this.getValueFromObject(object, kPAPFieldAddressKey);
        if (theAddress != null) {
            this.googleMapAddress = (String) theAddress;
        }
    }


    @Override
    public ParseModelAbstract getNewInstance() {
        return new Restaurant();
    }

    @Override
    public Task<Object> queryBelongToTask(final ParseModelAbstract belongTo) {
        final Restaurant self = this;
        // belongTo is self, return this.
        if (belongTo != null) {
            if (belongTo.equals(self)) {
                TaskCompletionSource finalTask = new TaskCompletionSource();
                finalTask.setResult(self);
                return finalTask.getTask();
            }
        }

        return this.getFirstLocalModelArrayTask().continueWith(new Continuation<ParseModelAbstract, Object>() {
            @Override
            public Object then(Task<ParseModelAbstract> task) throws Exception {
                if (belongTo != null) {
                    final Event event = (Event) belongTo;
                    event.belongToModel = self;
                }
                return Task.forResult(belongTo);
            }
        });
    }

    public static Task<List<ParseModelAbstract>> queryRestaurants() {
        return ParseModelQuery.queryFromDatabase(PQueryModelType.Restaurant, new Restaurant().makeParseQuery());
    }

    public static Task<List<ParseModelAbstract>> queryNearRestaurants(ParseGeoPoint geoPoint) {
        return ParseModelQuery.queryFromDatabase(PQueryModelType.Restaurant, new Restaurant().createNearlyRestaurantQuery(geoPoint));
    }

    @Override
    public Task<List<ParseModelAbstract>> queryParseModels(String keyword) {
        return Restaurant.queryFromDatabase(PQueryModelType.Restaurant, new Restaurant().createSearchDisplayNameQuery(keyword));
    }

    // MARK: Description
    @Override
    public String printDescription() {
        return "Restaurant{" +
                "objectUUID='" + objectUUID + '\'' +
                ", displayName='" + displayName + '\'' +
                ", location=" + location +
                ", googleMapAddress='" + googleMapAddress + '\'' +
                '}';
    }


}
