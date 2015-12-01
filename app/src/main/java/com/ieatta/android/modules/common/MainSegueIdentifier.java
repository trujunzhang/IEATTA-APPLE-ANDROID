package com.ieatta.android.modules.common;

/**
 * Created by djzhang on 12/1/15.
 */


public enum MainSegueIdentifier {
    Unspecified {
        public String toString() {
            return "";
        }
    },

    // Four menus in the near restaurant page.
    editRestaurantSegueIdentifier {
        public String toString() {
            return "addEditRestaurant";
        }
    },
    searchRestaurantSegueIdentifier {
        public String toString() {
            return "searchRestaurant";
        }
    },
    managerPeopleSegueIdentifier {
        public String toString() {
            return "managerPeople";
        }
    },
    readReviewsSegueIdentifier {
        public String toString() {
            return "addEditRestaurant";
        }
    },

    // Four detail pages.
    detailRestaurantSegueIdentifier {
        public String toString() {
            return "detailRestaurant";
        }
    },
    detailEventSegueIdentifier {
        public String toString() {
            return "detailEvent";
        }
    },
    detailOrderedRecipesSegueIdentifier {
        public String toString() {
            return "detailOrderedRecipes";
        }
    },
    detailRecipeSegueIdentifier {
        public String toString() {
            return "detailRecipe";
        }
    },

    // Show all posted reviews for restaurant,recipe.
    detailSeeReviewSegueIdentifier {
        public String toString() {
            return "seeReviewsInDetail";
        }
    },

    // Show detail review from review list.
    detailReviewSegueIdentifier {
        public String toString() {
            return "detailReview";
        }
    },

    // Four new/edit model pages.(the following three, and restaurant)
    editEventSegueIdentifier {
        public String toString() {
            return "addEditEvent";
        }
    },
    editPeopleSegueIdentifier {
        public String toString() {
            return "addEditPeople";
        }
    },
    editRecipeSegueIdentifier {
        public String toString() {
            return "addEditRecipe";
        }
    },
    // Choice Person in the event page.
    choicePeopleSegueIdentifier {
        public String toString() {
            return "choicePeople";
        }
    },
}
