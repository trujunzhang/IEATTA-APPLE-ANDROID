package com.ieatta.com.parse.models;

import com.ieatta.com.parse.ParseModelQuery;
import com.ieatta.com.parse.ParseModelSync;

import bolts.Continuation;
import bolts.Task;

import com.parse.ParseObject;
import com.ieatta.com.parse.models.enums.PQeuryModelType;
import com.parse.ParseQuery;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.enums.PQeuryModelType;
import com.ieatta.com.parse.models.enums.PhotoUsedType;
import com.ieatta.com.parse.models.enums.ReviewType;

import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;

/**
 * Created by djzhang on 11/27/15.
 */
public class Recipe extends ParseModelSync {

    // Class key
    private static final String kPAPClassKey = "Recipe";

    // Field keys
    private static final String kPAPFieldCostKey = "cost";
    private static final String kPAPFieldLikeCountKey = "likeCount";

    private static final String kPAPFieldEventRefKey = "eventRef";
    private static final String kPAPFieldOrderedPeopleRefKey = "orderedPeopleRef";

    private static final float DEFAULT_RECIPE_COST = 1.0f;

    public float cost = DEFAULT_RECIPE_COST;
    public int likeCount = 0;

    public String eventRef = "";
    public String orderedPeopleRef = "";

    // Variables invoked by viewController
    public ParseUser belongToModel;

    public Recipe() {
        super();
    }

    public Recipe(ParseUser belongToModel) {
        super();
        this.belongToModel = belongToModel;

        this.orderedPeopleRef = ParseModelAbstract.getPoint(belongToModel);
        this.eventRef = ParseModelAbstract.getPoint(belongToModel.belongToModel);
    }

    public Recipe(String displayName, float cost) {
        super();
        this.displayName = displayName;
        this.cost = cost;
    }

    // MARK: ParseModel
    ParseQuery createQuery(ParseUser people, Event event) {
        ParseQuery query = this.getParseQueryInstance();

        query.whereEqualTo(kPAPFieldOrderedPeopleRefKey, ParseModelAbstract.getPoint(people));
        query.whereEqualTo(kPAPFieldEventRefKey, ParseModelAbstract.getPoint(event));

        return query;
    }

    @Override
    public ReviewType getReviewType() {
        return ReviewType.Review_Recipe;
    }

    @Override
    public PhotoUsedType getPhotoUsedType() {
        return PhotoUsedType.Photo_Used_Recipe;
    }

    @Override
    public String getParseTableName() {
        return kPAPClassKey;
    }

    @Override
    public PQeuryModelType getModelType() {
        return PQeuryModelType.Recipe;
    }

    @Override
    public String getRestaurantRef() {
        return ParseModelAbstract.getPoint((this.belongToModel.belongToModel.belongToModel));
    }

    @Override
    public void writeCommonObject(ParseObject object) {
//        assert(this.eventRef.isEmpty == false, "Must setup eventRef.")
//        assert(this.orderedPeopleRef.isEmpty == false, "Must setup orderedPeopleRef.")

        object.put(kPAPFieldDisplayNameKey, this.displayName);
        object.put(kPAPFieldCostKey, this.cost);
        object.put(kPAPFieldLikeCountKey, this.likeCount);
        object.put(kPAPFieldEventRefKey, this.eventRef);
        object.put(kPAPFieldOrderedPeopleRefKey, this.orderedPeopleRef);
    }

    @Override
    public void readCommonObject(ParseObject object) {
        Object theName = this.getValueFromObject(object, kPAPFieldDisplayNameKey);
        if (theName != null) {
            this.displayName = (String) theName;
        }

        Object theCost = this.getValueFromObject(object, kPAPFieldCostKey);
        if (theCost != null) {
            if (theCost.getClass().equals(Integer.class)) {
                this.cost = (int) theCost;
            } else {
                this.cost = (float) theCost;
            }
        }

        Object theLikeCount = this.getValueFromObject(object, kPAPFieldLikeCountKey);
        if (theLikeCount != null) {
            this.likeCount = (int) theLikeCount;
        }

        Object theEventRef = this.getValueFromObject(object, kPAPFieldEventRefKey);
        if (theEventRef != null) {
            this.eventRef = (String) theEventRef;
        }

        Object theOrderedPeopleRef = this.getValueFromObject(object, kPAPFieldOrderedPeopleRefKey);
        if (theOrderedPeopleRef != null) {
            this.orderedPeopleRef = (String) theOrderedPeopleRef;
        }
    }

    @Override
    public ParseModelAbstract getNewInstance() {
        return new Recipe();
    }

    static Task<Object> queryRecipes() {
        return ParseModelQuery.queryFromDatabase(PQeuryModelType.Recipe, new Recipe().makeParseQuery());
    }

    @Override
    public Task<Object> queryParseModels(String keyword) {
        return Recipe.queryFromDatabase(PQeuryModelType.Recipe, new Recipe().createSearchDisplayNameQuery(keyword));
    }

    static Task<Object> queryRecipes(ParseUser people, Event event) {
        return ParseModelQuery.queryFromDatabase(PQeuryModelType.Recipe, new Recipe().createQuery(people, event));
    }

    @Override
    public Task<Object> queryBelongToTask(ParseModelAbstract belongTo) {
        final Recipe self = this;

        return this.getFirstLocalModelArrayTask().continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
                ParseModelAbstract model = ParseModelAbstract.getInstanceFromType(PQeuryModelType.Event, self.eventRef);
                return model.queryBelongToTask(self);
            }
        }).continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {

                final Event event = (Event) task.getResult();
                self.belongToModel = new ParseUser(event);

                TaskCompletionSource finalTask = new TaskCompletionSource();
                finalTask.setResult(self);
                return finalTask.getTask();
            }
        });
    }

    static Task<Integer> queryOrderedRecipesCount(ParseUser people, Event event) {
        Recipe recipe = new Recipe();

        return recipe.countLocalObjects(recipe.createQuery(people, event));
    }

    // MARK: Description
    @Override
    public String printDescription() {
        return "Recipe{" +
                "objectUUID='" + objectUUID + '\'' +
                ", cost=" + cost +
                ", likeCount=" + likeCount +
                ", eventRef='" + eventRef + '\'' +
                ", orderedPeopleRef='" + orderedPeopleRef + '\'' +
                '}';
    }
}
