package com.ieatta.com.parse.models;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.ParseModelLocalQuery;

import com.ieatta.com.parse.ParseModelSync;
import com.ieatta.com.parse.engine.realm.LocalQuery;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.ieatta.com.parse.models.enums.PhotoUsedType;
import com.ieatta.com.parse.models.enums.ReviewType;
import com.parse.ParseObject;

import java.util.List;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 11/27/15.
 */
public class Recipe extends ParseModelSync {
    final Recipe self = this;

    // Class key
    private static final String kPAPClassKey = "Recipe";

    // Field keys
    private static final String kPAPFieldPriceKey = "price";
    private static final String kPAPFieldLikeCountKey = "likeCount";

    private static final String kPAPFieldEventRefKey = "eventRef";
    private static final String kPAPFieldOrderedPeopleRefKey = "orderedPeopleRef";

    public String price = "";
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

    public Recipe(String displayName, String price) {
        super();
        this.displayName = displayName;
        this.price = price;
    }

    public Recipe(String displayName, String price, int sampleFileName) {
        super();
        this.displayName = displayName;
        this.price = price;
        this.sampleFileName = sampleFileName;
    }

    // MARK: ParseModel
    public LocalQuery createQuery(Team people, Event event) {
        LocalQuery query = this.getLocalQueryInstance();

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
        object.put(kPAPFieldPriceKey, this.price);
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

        Object thePrice = this.getValueFromObject(object, kPAPFieldPriceKey);
        if (thePrice != null) {
            this.price = (String) thePrice;
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
        return ParseModelLocalQuery.queryFromRealm(PQueryModelType.Recipe, new Recipe().makeLocalQuery());
    }

    @Override
    public Task<List<ParseModelAbstract>> queryParseModels(String keyword) {
        return Recipe.queryFromRealm(PQueryModelType.Recipe, new Recipe().createSearchDisplayNameForLocalQuery(keyword));
    }

    public static Task<List<ParseModelAbstract>> queryRecipes(Team people, Event event) {
        return ParseModelLocalQuery.queryFromRealm(PQueryModelType.Recipe, new Recipe().createQuery(people, event));
    }

    @Override
    public Task<ParseModelAbstract> queryBelongToTask(ParseModelAbstract belongTo) {
        final Recipe[] backModel = {self};

        return self.getFirstModelTaskFromRealm()
                .onSuccessTask(new Continuation<ParseModelAbstract, Task<ParseModelAbstract>>() {
                    @Override
                    public Task<ParseModelAbstract> then(Task<ParseModelAbstract> task) throws Exception {
                        Recipe newModel = (Recipe) task.getResult();
                        backModel[0] = newModel;
                        ParseModelAbstract event = ParseModelAbstract.getInstanceFromType(PQueryModelType.Event, backModel[0].eventRef);
                        return event.queryBelongToTask(self);
                    }
                }).onSuccessTask(new Continuation<ParseModelAbstract, Task<ParseModelAbstract>>() {
                    @Override
                    public Task<ParseModelAbstract> then(Task<ParseModelAbstract> task) throws Exception {
                        Event event = (Event) task.getResult();
                        backModel[0].belongToModel = new Team(event);
                        return Task.forResult((ParseModelAbstract) backModel[0]);
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
                ", price=" + price +
                ", likeCount=" + likeCount +
                ", eventRef='" + eventRef + '\'' +
                ", orderedPeopleRef='" + orderedPeopleRef + '\'' +
                '}';
    }
}
