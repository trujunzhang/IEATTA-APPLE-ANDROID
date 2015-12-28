package com.ieatta.com.parse.models;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.ParseModelLocalQuery;

import com.ieatta.com.parse.ParseModelSync;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.ieatta.com.parse.models.enums.PhotoUsedType;
import com.ieatta.com.parse.models.enums.ReviewType;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 11/27/15.
 */
public class Restaurant extends ParseModelSync {
    private Restaurant self = this;
    public static String Default_FormattedAddress = "";

    public static final double PAWRestaurantMaximumSearchDistance = 1; // Value in kilometers

    // Class key
    private static final String kPAPClassKey = "Restaurant";

    // Field keys
    private static final String kPAPFieldLocationKey = "location";
    private static final String kPAPFieldAddressKey = "address";
    private static final String kPAPFieldCityStateKey = "cityState";

    public ParseGeoPoint location;

    // Variables invoked by viewController
    public String googleMapAddress = Default_FormattedAddress;

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
    private ParseQuery createNearlyRestaurantQuery(ParseGeoPoint nearPoint) {
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
    public Task<Void> writeCommonObject(ParseObject object) {
        object.put(kPAPFieldDisplayNameKey, this.displayName);
        object.put(kPAPFieldLocationKey, this.location);
        object.put(kPAPFieldAddressKey, this.googleMapAddress);

        return Task.forResult(null);
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
    public Task<ParseModelAbstract> queryBelongToTask(final ParseModelAbstract belongTo) {
        final ParseModelAbstract[] backModel = {self};

        // belongTo is self, return this.
        if (belongTo != null) {
            if (belongTo.equals(self)) {
                return Task.forResult(backModel[0]);
            }
        }

        return self.getFirstModelTaskFromParse().onSuccessTask(new Continuation() {
            @Override
            public Object then(Task task) throws Exception {
                ParseModelAbstract newModel = (ParseModelAbstract) task.getResult();
                backModel[0] = newModel;
                return Task.forResult(backModel[0]);
            }
        });
    }

    public static Task<List<ParseModelAbstract>> queryRestaurants() {
        return ParseModelLocalQuery.queryFromParse(PQueryModelType.Restaurant, new Restaurant().makeParseQuery());
    }

    public static Task<List<ParseModelAbstract>> queryNearRestaurants(ParseGeoPoint geoPoint) {
        return ParseModelLocalQuery.queryFromParse(PQueryModelType.Restaurant, new Restaurant().createNearlyRestaurantQuery(geoPoint));
    }

    @Override
    public Task<List<ParseModelAbstract>> queryParseModels(String keyword) {
        return Restaurant.queryFromParse(PQueryModelType.Restaurant, new Restaurant().createSearchDisplayNameForParseQuery(keyword));
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

    @Override
    public Task<Void> updateLocalInBackground() {
        return self.upInBackground().onSuccessTask(new Continuation() {
            @Override
            public Object then(Task task) throws Exception {
                return self.pinInBackground();
            }
        });
    }

    @Override
    protected Task getFirstModelTask()  {
        return getFirstModelTaskFromParse();
    }
}
