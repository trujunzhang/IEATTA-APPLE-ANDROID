package com.ieatta.com.parse.models;

import com.ieatta.com.parse.ParseModelQuery;
import com.ieatta.com.parse.ParseModelSync;

import bolts.Continuation;
import bolts.Task;

import com.parse.ParseObject;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.parse.ParseQuery;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.enums.PhotoUsedType;
import com.ieatta.com.parse.models.enums.ReviewType;

import java.util.List;

import bolts.TaskCompletionSource;

/**
 * Created by djzhang on 11/27/15.
 */
public class Recipe extends ParseModelSync {
    final Recipe self = this;

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
    public Team belongToModel;

    public Recipe() {
        super();
    }

    public Recipe(Team belongToModel) {
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

    public Recipe(String displayName, float cost, int sampleFileName) {
        super();
        this.displayName = displayName;
        this.cost = cost;
        this.sampleFileName = sampleFileName;
    }

    // MARK: ParseModel
    public ParseQuery createQuery(Team people, Event event) {
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
    public PQueryModelType getModelType() {
        return PQueryModelType.Recipe;
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

    public static Task<List<ParseModelAbstract>> queryRecipes() {
        return ParseModelQuery.queryFromDatabase(PQueryModelType.Recipe, new Recipe().makeParseQuery());
    }

    @Override
    public Task<List<ParseModelAbstract>> queryParseModels(String keyword) {
        return Recipe.queryFromDatabase(PQueryModelType.Recipe, new Recipe().createSearchDisplayNameQuery(keyword));
    }

    public static Task<List<ParseModelAbstract>> queryRecipes(Team people, Event event) {
        return ParseModelQuery.queryFromDatabase(PQueryModelType.Recipe, new Recipe().createQuery(people, event));
    }

    @Override
    public Task<Boolean> queryBelongToTask(ParseModelAbstract belongTo) {
        final ParseModelAbstract[] event = {null};
        return this.getFirstLocalModelArrayTask().onSuccessTask(new Continuation<ParseModelAbstract, Task<Boolean>>() {
            @Override
            public Task<Boolean> then(Task<ParseModelAbstract> task) throws Exception {
                event[0] = ParseModelAbstract.getInstanceFromType(PQueryModelType.Event, self.eventRef);
                return event[0].queryBelongToTask(self);
            }
        }).onSuccessTask(new Continuation<Boolean, Task<Boolean>>() {
            @Override
            public Task<Boolean> then(Task<Boolean> task) throws Exception {
                final Event instance = (Event) event[0];
                self.belongToModel = new Team(instance);

                return Task.forResult(true);
            }
        });
    }

    public static Task<Integer> queryOrderedRecipesCount(Team people, Event event) {
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
