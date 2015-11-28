package com.ieatta.com.parse.models;

import com.ieatta.com.parse.ParseModelQuery;
import com.ieatta.com.parse.ParseModelSync;

import bolts.Task;

import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.ieatta.com.parse.models.enums.PQeuryModelType;
import com.parse.ParseQuery;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.enums.PQeuryModelType;
import com.ieatta.com.parse.models.enums.PhotoUsedType;
import com.ieatta.com.parse.models.enums.ReviewType;

/**
 * Created by djzhang on 11/27/15.
 */
public class Restaurant extends ParseModelSync {
    public static final String Default_FormattedAddress = "";

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

    String getGoogleMapAddress() {
//        if(this.googleMapAddress.isEmpty == true){
//            return Default_FormattedAddress;
//        }

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
    public PQeuryModelType getModelType() {
        return PQeuryModelType.Restaurant;
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

//     @Override     public Task<Object> queryBelongToTask(belongTo:ParseModelAbstract?)   Task<Object>{
//        if let _beloingTo = belongTo{// belongTo is self, return this.
//            if(_beloingTo.isEqual(self)){
//                return BFTask(result: self)
//            }
//        }
//
//        return this.getFirstLocalModelArrayTask().continueWithBlock({ (task) -> AnyObject? in
//        if let _beloingTo = belongTo{
//            (_beloingTo as! Event).belongToModel = self
//        }
//        return BFTask(result: belongTo)
//        })
//    }

    static Task<Object> queryRestaurants()   {
        return ParseModelQuery.queryFromDatabase(PQeuryModelType.Restaurant, new  Restaurant().makeParseQuery());
    }

    static Task<Object> queryNearRestaurants(ParseGeoPoint geoPoint)   {
        return ParseModelQuery.queryFromDatabase(PQeuryModelType.Restaurant,new  Restaurant().createNearlyRestaurantQuery(geoPoint));
    }

     @Override
     public Task<Object> queryParseModels(String keyword)  {
        return Restaurant.queryFromDatabase(PQeuryModelType.Restaurant,new  Restaurant().createSearchDisplayNameQuery(keyword));
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
