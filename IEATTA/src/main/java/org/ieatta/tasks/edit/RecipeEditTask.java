package org.ieatta.tasks.edit;

import android.app.Activity;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.ieatta.database.models.DBRecipe;

import org.ieatta.server.cache.ThumbnailImageUtil;
//import org.ieatta.tasks.DBConvert;
import org.ieatta.tasks.FragmentTask;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;
import io.realm.RealmResults;

public class RecipeEditTask extends FragmentTask {
    public RecipeEditTask(Activity activity, RecyclerView recyclerView) {
        super(activity, recyclerView);
    }
//
//    @VisibleForTesting
//    public RecipeEditTask(HistoryEntry entry) {
//        super(entry);
//    }
//
//    public RecipeEditTask(HistoryEntry entry, Activity activity, PageViewModel model) {
//        super(entry, activity, model);
//    }
//
//    enum EditRecipeSection {
//        sectionInformation,//= 0
//        section_gallery_thumbnail,//= 1
//    }
//
//    public DBRecipe mRecipe = new DBRecipe();
//
//    /**
//     * Execute Task for Restaurant edit.
//     *
//     * @return
//     */
//    @Override
//    public Task<Void> executeTask() {
//        final String _recipeUUID = this.entry.getHPara();
//        if (this.entry.isNewModel() == true)
//            return Task.forResult(null);
//
//        return new RealmModelReader<DBRecipe>(DBRecipe.class).getFirstObject(LocalDatabaseQuery.get(_recipeUUID), false, realmList).onSuccessTask(new Continuation<DBRecipe, Task<List<File>>>() {
//            @Override
//            public Task<List<File>> then(Task<DBRecipe> task) throws Exception {
//                mRecipe = task.getResult();
//                return ThumbnailImageUtil.sharedInstance.getImagesListTask(_recipeUUID);
//            }
//        }).onSuccessTask(new Continuation<List<File>, Task<Void>>() {
//            @Override
//            public Task<Void> then(Task<List<File>> task) throws Exception {
//                thumbnailGalleryCollection = DBConvert.toGalleryCollection(task.getResult());
//                return null;
//            }
//        });
//
//    }
//
//    @Override
//    public void prepareUI() {
//        super.prepareUI();
//
//        // Add rows for sections.
//        this.manager.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Recipe_Information), EditRecipeSection.sectionInformation.ordinal());
//        this.manager.setRegisterCellClass(IEAEditTextFieldCell.getType(), EditRecipeSection.sectionInformation.ordinal());
//    }
//
//    @Override
//    public void postUI() {
//        this.manager.setHeaderItem(new IEAHeaderViewModel(this.model.getActionbarHeight()), IEAHeaderView.getType());
//        this.manager.setFooterItem(new IEAFooterViewModel(), IEAFooterView.getType());
//
//        List<EditCellModel> infoSectionList = new LinkedList<EditCellModel>() {{
//            add(new EditCellModel(IEAEditKey.recipe_name, mRecipe.getDisplayName(), R.string.Recipe_Name_info));
//            add(new EditCellModel(IEAEditKey.recipe_price, mRecipe.getPrice(), R.string.recipe_price));
//        }};
//        this.manager.setSectionItems(infoSectionList, EditRecipeSection.sectionInformation.ordinal());
//
//        postPhotosGallery(EditRecipeSection.section_gallery_thumbnail.ordinal());
//
//        model.setPage(new Page());
//    }
//
//    @Override
//    public boolean haveLeadImage() {
//        return false;
//    }
}
