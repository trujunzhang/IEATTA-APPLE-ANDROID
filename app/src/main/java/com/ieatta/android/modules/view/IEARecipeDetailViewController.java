package com.ieatta.android.modules.view;

import com.ieatta.android.modules.IEAReviewsInDetailTableViewController;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Recipe;

/**
 * Created by djzhang on 12/1/15.
 */

enum RecipeDetailSection {
    sectionHeader,//= 0
    sectionOrderedPeople,//= 1
    sectionPhotos,//= 2
    sectionReviews,//= 3
}

public class IEARecipeDetailViewController extends IEAReviewsInDetailTableViewController {
private IEARecipeDetailViewController self = this;

    @Override
    public ParseModelAbstract getPageModel(){
        return self.orderedRecipe;
    }
    @Override
    public boolean havePhotoGallery() {
        return true;
    }
    @Override
    public boolean shouldShowHUD() {
        return true;
    }

    // Transferd Model from previous page.
    private Recipe orderedRecipe;


}
