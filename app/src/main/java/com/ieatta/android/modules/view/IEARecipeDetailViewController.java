package com.ieatta.android.modules.view;

import android.os.Bundle;
import android.virtualbreak.com.manualdatabase.ActivityModelDebug;

import com.ieatta.android.R;
import com.ieatta.android.modules.IEAReviewsInDetailTableViewController;
import com.ieatta.android.modules.cells.IEAReviewUserCell;
import com.ieatta.android.modules.cells.headerview.IEARecipeDetailHeaderCell;
import com.ieatta.android.modules.cells.model.IEARecipeHeader;
import com.ieatta.android.modules.common.MainSegueIdentifier;
import com.ieatta.android.modules.common.edit.SectionTitleCellModel;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.tools.CollectionUtils;
import com.ieatta.android.notification.NSNotification;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Photo;
import com.ieatta.com.parse.models.Recipe;

import java.util.List;

import bolts.Continuation;
import bolts.Task;

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
    public ParseModelAbstract getPageModel() {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        self.orderedRecipe = (Recipe) self.getTransferedModel();
        // TODO djzhang(test)
//        self.orderedRecipe = ActivityModelDebug.getOrderedRecipe();

        // Do any additional setup after loading the view.
//        assert(self.orderedRecipe?.belongToModel != nil, "Must setup OrderedPeople's instance.")
//        assert(self.orderedRecipe != nil, "Must setup orderedRecipe's instance.")

//        NSNotificationCenter.defaultCenter().addObserver(self, selector: "RecipeWasCreated:", name: PARecipeCreatedNotification, object: nil)

        Photo.queryPhotosByModel(self.getPageModel()).onSuccessTask(new Continuation<List<ParseModelAbstract>, Task<Boolean>>() {
            @Override
            public Task<Boolean> then(Task<List<ParseModelAbstract>> task) throws Exception {
                fetchedPhotosTask = task;

                // Next, Load Reviews.
                return self.getReviewsRelatedModelQueryTask();
            }
        }).onSuccess(new Continuation<Boolean, Object>() {
            @Override
            public Object then(Task<Boolean> task) throws Exception {

                // Finally, hide hud.
                self.hideHUD();

                self.setRegisterCellClass(IEARecipeDetailHeaderCell.getType(), RecipeDetailSection.sectionHeader.ordinal());
                self.setRegisterCellClass(IEAReviewUserCell.getType(), RecipeDetailSection.sectionOrderedPeople.ordinal());

                self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Ordered_People), RecipeDetailSection.sectionOrderedPeople.ordinal());

                self.setSectionItems(CollectionUtils.createList(new IEARecipeHeader(self, self.orderedRecipe)), RecipeDetailSection.sectionHeader.ordinal());
                self.setSectionItems(CollectionUtils.createList(self.orderedRecipe.belongToModel), RecipeDetailSection.sectionOrderedPeople.ordinal());

                self.configureReviewsSection();
                self.configurePhotoGallerySection(fetchedPhotosTask);

                return null;
            }
        }).continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
                if (task.isFaulted()) {
                    Object object = task;
                    return null;
                }
                return null;
            }
        });
    }


    // MARK: Override IEAReviewsTableViewController methods
    @Override
    public int getReviewsSectionIndex() {
        return RecipeDetailSection.sectionReviews.ordinal();
    }

    // MARK: Override IEAPhotoGalleryViewController methods
//    @Override
    public int getPhotoGallerySectionIndex() {
        return RecipeDetailSection.sectionPhotos.ordinal();
    }

    // MARK: TableView header events
    public void performSegueForEditingModel() {
        self.performSegueWithIdentifier(MainSegueIdentifier.editRecipeSegueIdentifier, self);
    }

    // MARK: NSNotificationCenter notification handlers
    @Override
    protected void RecipeWasCreated(NSNotification note) {
        setSectionItems(CollectionUtils.createList(new IEARecipeHeader(self, self.orderedRecipe)), RecipeDetailSection.sectionHeader.ordinal());
    }
}
