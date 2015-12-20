package com.ieatta.com.parse.models;

import com.ieatta.com.parse.ParseModelQuery;
import com.ieatta.com.parse.ParseModelSync;

import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;

import com.ieatta.com.parse.engine.realm.DBObject;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.ieatta.com.parse.engine.realm.DBQuery;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.enums.ReviewType;

import java.util.LinkedList;
import java.util.List;
import java.util.List;

/**
 * Created by djzhang on 11/27/15.
 */
public class Review extends ParseModelSync {

    public static final int MAX_FETCHED_REVIEWS_IN_DetailPage = 3;
    public static final int NO_Limit_FETCHED_REVIEWS_IN_DetailPage = Integer.MAX_VALUE;

    public static final int Rating_Default_Value = 1;
    public static final int Rating_Max_Value = 5;

    // Class key
    private static final String kPAPClassKey = "Review";

    // Field keys
    private static final String kPAPFieldContentKey = "content";
    private static final String kPAPFieldRateKey = "rate";

    private static final String kPAPFieldUserRefKey = "userRef";

    private static final String kPAPFieldReviewRefKey = "reviewRef";
    private static final String kPAPFieldReviewTypeKey = "reviewType";

    public String content = "";

    /// Rate valuefrom 1 to 5
    public int rate = 1;
    public String userRef = "";// Point of Team
    public String reviewRef = "";
    public ReviewType reviewType = ReviewType.Review_Unknow;

    public Review() {
        super();
    }

    public Review(ParseModelAbstract refModel) {
        super();

        this.reviewRef = ParseModelAbstract.getPoint(refModel);
        this.reviewType = refModel.getReviewType();
    }

    public Review(Team refUser, ParseModelAbstract refModel, String content, int rate) {
        this(refModel);

        this.userRef = ParseModelAbstract.getPoint(refUser);
        this.content = content;
        this.rate = rate;
    }


    /// Constructor for JUnit Test.
    public Review(ReviewType reviewType, String content, int rate) {
        super();
        this.reviewType = reviewType;
        this.content = content;
        this.rate = rate;
    }

    // MARK: ParseModel
    public DBQuery createQueryForReviewRef() {
        DBQuery query = this.makeDBQuery();

        query.whereEqualTo(kPAPFieldReviewRefKey, this.reviewRef);
        query.whereEqualTo(kPAPFieldReviewTypeKey, ReviewType.getInt(this.reviewType));

        return query;
    }

    @Override
    public String getParseTableName() {
        return kPAPClassKey;
    }

    @Override
    public PQueryModelType getModelType() {
        return PQueryModelType.Review;
    }

    @Override
    public void writeCommonObject(DBObject object) {
        object.put(kPAPFieldContentKey, this.content);
        object.put(kPAPFieldRateKey, this.rate);
        object.put(kPAPFieldUserRefKey, this.userRef);
        object.put(kPAPFieldReviewRefKey, this.reviewRef);
        object.put(kPAPFieldReviewTypeKey, ReviewType.getInt(this.reviewType)); // *** Important ***
    }

    @Override
    public void readCommonObject(DBObject object) {
        Object theContent = this.getValueFromObject(object, kPAPFieldContentKey);
        if (theContent != null) {
            this.content = (String) theContent;
        }

        Object theRate = this.getValueFromObject(object, kPAPFieldRateKey);
        if (theRate != null) {
            this.rate = (int) theRate;
        }
        Object theUserRef = this.getValueFromObject(object, kPAPFieldUserRefKey);
        if (theUserRef != null) {
            this.userRef = (String) theUserRef;
        }

        Object theReviewRef = this.getValueFromObject(object, kPAPFieldReviewRefKey);
        if (theReviewRef != null) {
            this.reviewRef = (String) theReviewRef;
        }

        Object theReviewType = this.getValueFromObject(object, kPAPFieldReviewTypeKey);
        if (theReviewType != null) {
            this.reviewType = ReviewType.fromInteger(((int) theReviewType));
        }
    }

    @Override
    public ParseModelAbstract getNewInstance() {
        return new Review();
    }

    public Task<Integer> queryReviewsCount() {
        return this.countLocalObjects(this.createQueryForReviewRef()).onSuccessTask(new Continuation<Integer, Task<Integer>>() {
            @Override
            public Task<Integer> then(Task<Integer> task) throws Exception {
                int value = task.getResult().intValue() - Review.MAX_FETCHED_REVIEWS_IN_DetailPage;
                return Task.forResult(value);
            }
        });
    }

    public Task<Integer> queryRatingInReviews() {
        // First of all, query all reviews.
        return ParseModelQuery.findLocalObjectsInBackground(this.createQueryForReviewRef()).onSuccess(new Continuation<List<DBObject>, Integer>() {
            @Override
            public Integer then(Task<List<DBObject>> task) throws Exception {
                List<ParseModelAbstract> array = new Review().convertToParseModelArray(task.getResult(), true);
                int rating = Review.getRatingInReview(array);
                return rating;
            }
        });
    }

    public static Task<List<ParseModelAbstract>> queryReviews(ParseModelAbstract model, int limit) {
        DBQuery query = new Review(model).createQueryForReviewRef();
        //        print("\(model.printDescription())")
        //        print("\(Review(refModel: model).printDescription())")
        if (limit != Review.NO_Limit_FETCHED_REVIEWS_IN_DetailPage) {
            query.setLimit(limit);
        }
        return ParseModelQuery.queryFromDatabase(PQueryModelType.Review, query);
    }


    public static int getRatingInReview(List<ParseModelAbstract> list) {
        if (list.size() == 0) {
            return 0;
        }

        int sum = 0;
        for (ParseModelAbstract model : list) {
            sum += (((Review) model).rate);
        }
        int rating = sum / list.size();

        return rating;
    }


    public static List<String> getUserPoints(List<ParseModelAbstract> reviews) {
        LinkedList<String> userPoints = new LinkedList<>();
        for (ParseModelAbstract model : reviews) {
            userPoints.add(((Review) model).userRef);
        }
        return userPoints;
    }

    private static Team getPeople(Review review, List<ParseModelAbstract/*Team*/> people) {
        for (ParseModelAbstract user : people) {
            if (review.userRef.equals(ParseModelAbstract.getPoint(user))) {
                return (Team) user;
            }
        }
        return Team.getAnonymousUser();
    }

    public static List<Object> getReviewItems(List<ParseModelAbstract/*Review*/> reviews, List<ParseModelAbstract/*Team*/> people) {
        LinkedList<Object> array = new LinkedList<>();
        for (ParseModelAbstract model : reviews) {
            Team user = getPeople((Review) model, people);
            user.writedReviewTimeAgo = model.getTimeAgoString();

            array.add(user);
            array.add(model);
        }

        return array;
    }

    // MARK: Description
    @Override
    public String printDescription() {
        return "Review{" +
                "objectUUID='" + objectUUID + '\'' +
                ", content='" + content + '\'' +
                ", rate=" + rate +
                ", userRef='" + userRef + '\'' +
                ", reviewRef='" + reviewRef + '\'' +
                ", reviewType=" + reviewType +
                '}';
    }

}
