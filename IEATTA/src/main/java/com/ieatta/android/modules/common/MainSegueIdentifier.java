package com.ieatta.android.modules.common;

import com.ieatta.android.modules.view.IEAChoicePeopleViewController;
import org.ieatta.activity.event.IEAEventActivity;
import com.ieatta.android.modules.view.IEAOrderedRecipesViewController;
import com.ieatta.android.modules.view.IEARecipeDetailViewController;

import org.ieatta.activity.restaurant.IEARestaurantDetailActivity;

import com.ieatta.android.modules.view.IEAReviewDetailViewController;
import com.ieatta.android.modules.view.IEASeeReviewsInDetailViewController;
import com.ieatta.android.modules.view.edit.IEAEditEventViewController;
import com.ieatta.android.modules.view.edit.IEAEditPeopleViewController;
import com.ieatta.android.modules.view.edit.IEAEditRecipeViewController;
import com.ieatta.android.modules.view.edit.IEAEditRestaurantViewController;
import com.ieatta.android.modules.view.photogallery.PhotoGalleryPagerActivity;
import com.ieatta.android.modules.view.posts.IEAWriteReviewViewController;
import com.ieatta.android.modules.view.search.IEAManagerPeopleViewController;
import com.ieatta.android.modules.view.search.IEAReadReviewsViewController;
import com.ieatta.android.modules.view.search.IEASearchRestaurantViewController;

public enum MainSegueIdentifier {

    Unspecified("", null),

    // Four menus in the near restaurant page.
    editRestaurantSegueIdentifier("addEditRestaurant", IEAEditRestaurantViewController.class),

    searchRestaurantSegueIdentifier("searchRestaurant", IEASearchRestaurantViewController.class),
    managerPeopleSegueIdentifier("managerPeople", IEAManagerPeopleViewController.class),
    readReviewsSegueIdentifier("addEditRestaurant", IEAReadReviewsViewController.class),
    // Four detail pages.
    detailRestaurantSegueIdentifier("detailRestaurant", IEARestaurantDetailActivity.class),
    detailEventSegueIdentifier("detailEvent", IEAEventActivity.class),
    detailOrderedRecipesSegueIdentifier("detailOrderedRecipes", IEAOrderedRecipesViewController.class),
    detailRecipeSegueIdentifier("detailRecipe", IEARecipeDetailViewController.class),

    // Show all posted reviews for restaurant,recipe.
    detailSeeReviewSegueIdentifier("seeReviewsInDetail", IEASeeReviewsInDetailViewController.class),

    // Show detail review from review list.
    detailReviewSegueIdentifier("detailReview", IEAReviewDetailViewController.class),

    // Four new/edit model pages.(the following three, and restaurant)
    editEventSegueIdentifier("addEditEvent", IEAEditEventViewController.class),
    editPeopleSegueIdentifier("addEditPeople", IEAEditPeopleViewController.class),
    editRecipeSegueIdentifier("addEditRecipe", IEAEditRecipeViewController.class),
    // Choice Person in the event page.
    choicePeopleSegueIdentifier("choicePeople", IEAChoicePeopleViewController.class),

    // Show detail review from review list.
    postReviewSegueIdentifier("postReview", IEAWriteReviewViewController.class),

    photoPagesControllerSegueIdentifier("photoPagesController", PhotoGalleryPagerActivity.class);


    private String name;
    private Class<?> activity;

    MainSegueIdentifier(String name, Class<?> activity) {
        this.name = name;
        this.activity = activity;
    }

    public Class<?> getActivity() {
        return this.activity;
    }

}
