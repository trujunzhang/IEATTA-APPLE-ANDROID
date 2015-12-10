package com.ieatta.android.modules.common;

import com.ieatta.android.modules.view.IEAChoicePeopleViewController;
import com.ieatta.android.modules.view.IEAEventDetailViewController;
import com.ieatta.android.modules.view.IEAOrderedRecipesViewController;
import com.ieatta.android.modules.view.IEARecipeDetailViewController;
import com.ieatta.android.modules.view.IEARestaurantDetailViewController;
import com.ieatta.android.modules.view.IEASeeReviewsInDetailViewController;
import com.ieatta.android.modules.view.edit.IEAEditEventViewController;
import com.ieatta.android.modules.view.edit.IEAEditPeopleViewController;
import com.ieatta.android.modules.view.edit.IEAEditRestaurantViewController;
import com.ieatta.android.modules.view.search.IEAManagerPeopleViewController;
import com.ieatta.android.modules.view.search.IEAReadReviewsViewController;
import com.ieatta.android.modules.view.search.IEASearchRestaurantViewController;

/**
 * Created by djzhang on 12/1/15.
 */


public enum MainSegueIdentifier {

    Unspecified("",null),

    // Four menus in the near restaurant page.
    editRestaurantSegueIdentifier("addEditRestaurant", IEAEditRestaurantViewController.class),
    
    searchRestaurantSegueIdentifier("searchRestaurant", IEASearchRestaurantViewController.class),
    managerPeopleSegueIdentifier("managerPeople", IEAManagerPeopleViewController.class),
    readReviewsSegueIdentifier("addEditRestaurant", IEAReadReviewsViewController.class),
    // Four detail pages.
    detailRestaurantSegueIdentifier("detailRestaurant", IEARestaurantDetailViewController.class),
    detailEventSegueIdentifier("detailEvent", IEAEventDetailViewController.class),
    detailOrderedRecipesSegueIdentifier("detailOrderedRecipes", IEAOrderedRecipesViewController.class),
    detailRecipeSegueIdentifier("detailRecipe", IEARecipeDetailViewController.class),

    // Show all posted reviews for restaurant,recipe.
    detailSeeReviewSegueIdentifier("seeReviewsInDetail", IEASeeReviewsInDetailViewController.class),

    // Show detail review from review list.
    detailReviewSegueIdentifier("detailReview",IEASeeReviewsInDetailViewController.class),

    // Four new/edit model pages.(the following three, and restaurant)
    editEventSegueIdentifier("addEditEvent", IEAEditEventViewController.class),
    editPeopleSegueIdentifier("addEditPeople", IEAEditPeopleViewController.class),
    editRecipeSegueIdentifier("addEditRecipe",IEARecipeDetailViewController.class),
    // Choice Person in the event page.
    choicePeopleSegueIdentifier("choicePeople", IEAChoicePeopleViewController.class),


    private String name;
    private Class<?> activity;

    MainSegueIdentifier(String name, Class<?> activity) {
        this.name = name;
        this.activity = activity;
    }

    public static Class<?> getActivity(MainSegueIdentifier type){
        return type.activity;
    }

}
