package org.ieatta.activity.recipe;

import android.os.Bundle;

import com.ieatta.android.IEAPageActivity;


public class IEAOrderedRecipesActivity extends IEAPageActivity {

    private OrderedRecipesTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        task = new OrderedRecipesTask(this, this.recyclerView);
        task.makeActivePage();
    }

//    // Transferd Model from previous page.
//    private Team orderedPeople;
//    // Selected model from tableview.
//    private Recipe selectedModel;
//
//    private List<ParseModelAbstract/*Recipe*/> fetchedOrderedRecipes = new LinkedList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        this.orderedPeople = (Team) this.getTransferedModel();
//
//        // Do any additional setup after loading the view.
////        assert(this.orderedPeople?.belongToModel != nil, "Must setup Event's instance.")
////        assert(this.orderedPeople != nil, "Must setup OrderedPeople instance.")
//
////        NSNotificationCenter.defaultCenter().addObserver(this, selector: "TakenPhotoWasChanged:", name: PAModelTakenPhotoNotification, object: nil)
////        NSNotificationCenter.defaultCenter().addObserver(this, selector: "RecipeWasCreated:", name: PARecipeCreatedNotification, object: nil)
//
//        this.queryOrderedRecipesList();
//    }
//
//    private void queryOrderedRecipesList() {
//        Recipe.queryRecipes(this.orderedPeople, this.orderedPeople.belongToModel)
//                .onSuccessTask(new Continuation<List , Task<Boolean>>() {
//                    @Override
//                    public Task<Boolean> then(Task<List > task) throws Exception {
//                        fetchedOrderedRecipes = task.getResult();
//
//                        // Next, fetch related photos
//                        return this.getPhotosForModelsTask(task);
//                    }
//                }).onSuccess(new Continuation<Boolean, Void>() {
//            @Override
//            public Void then(Task<Boolean> task) throws Exception {
//
//                this.setRegisterCellClass(IEAOrderedPeopleCell.getType(), OrderedRecipesSection.sectionOrderedPeople.ordinal());
//                this.setRegisterCellClassWhenSelected(IEAOrderedRecipeCell.getType(), OrderedRecipesSection.sectionRecipes.ordinal());
//
//                this.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Ordered_People), OrderedRecipesSection.sectionOrderedPeople.ordinal());
//                this.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Ordered_Recipes), OrderedRecipesSection.sectionRecipes.ordinal());
//
//                this.setSectionItems(IEAOrderedPeople.convertToOrderedPeople(this.orderedPeople, (this.orderedPeople.belongToModel), this), OrderedRecipesSection.sectionOrderedPeople.ordinal());
//
////                this.setSectionItems(fetchedOrderedRecipes, OrderedRecipesSection.sectionRecipes.ordinal());
//                this.configureDetailSection(this.fetchedOrderedRecipes, R.string.Empty_for_Ordered_Recipe, IEAOrderedRecipeCell.getType(), OrderedRecipesSection.sectionRecipes.ordinal());
//
//                return null;
//            }
//        }, Task.UI_THREAD_EXECUTOR);
//    }
//
//    @Override
//    public void whenSelectedEvent(Object model, NSIndexPath indexPath) {
//        // **** Important ****
//        if (indexPath.section == OrderedRecipesSection.sectionOrderedPeople.ordinal()) {
//            return;
//        }
//        Recipe recipe = (Recipe) model;
//
//        recipe.belongToModel = this.orderedPeople;
//        this.selectedModel = recipe;
//        this.performSegueWithIdentifier(MainSegueIdentifier.detailRecipeSegueIdentifier, this);
//    }
//
//    @Override
//    public void segueForRecipeDetailViewController(IEARecipeDetailViewController destination, Intent sender) {
//        /// Show detailed recipe
//        this.setTransferedModel(sender, this.selectedModel);
//    }
//
//    @Override
//    public void segueForEditRecipeViewController(IEAEditRecipeViewController destination, Intent sender) {
//        // Add recipe
//        this.setTransferedModelForEdit(sender, new Recipe(this.orderedPeople), true);
//    }
//
//    // MARK: Cell's Events
//    public void performSegueForAddingRecipe() {
//        this.performSegueWithIdentifier(MainSegueIdentifier.editRecipeSegueIdentifier, this);
//    }
//
//    // MARK: NSNotificationCenter notification handlers
//    @Override
//    protected void TakenPhotoWasChanged(NSNotification note) {
//        this.queryOrderedRecipesList();
//    }
//
//    @Override
//    protected void RecipeWasCreated(NSNotification note) {
//        this.queryOrderedRecipesList();
//    }
//
//    @Override
//    protected void ReviewWasCreated(NSNotification note) {
//        this.queryOrderedRecipesList();
//    }
}
