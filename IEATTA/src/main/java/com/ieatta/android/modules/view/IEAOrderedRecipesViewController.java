package com.ieatta.android.modules.view;

import com.ieatta.android.modules.IEASplitDetailViewController;


public class IEAOrderedRecipesViewController extends IEASplitDetailViewController {
    enum OrderedRecipesSection {
        sectionOrderedPeople, //= 0
        sectionRecipes,       //= 1
    }

    private IEAOrderedRecipesViewController self = this;

    @Override
    public boolean shouldShowHUD() {
        return true;
    }
//
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
//        self.orderedPeople = (Team) self.getTransferedModel();
//
//        // Do any additional setup after loading the view.
////        assert(self.orderedPeople?.belongToModel != nil, "Must setup Event's instance.")
////        assert(self.orderedPeople != nil, "Must setup OrderedPeople instance.")
//
////        NSNotificationCenter.defaultCenter().addObserver(self, selector: "TakenPhotoWasChanged:", name: PAModelTakenPhotoNotification, object: nil)
////        NSNotificationCenter.defaultCenter().addObserver(self, selector: "RecipeWasCreated:", name: PARecipeCreatedNotification, object: nil)
//
//        self.queryOrderedRecipesList();
//    }
//
//    private void queryOrderedRecipesList() {
//        Recipe.queryRecipes(self.orderedPeople, self.orderedPeople.belongToModel)
//                .onSuccessTask(new Continuation<List , Task<Boolean>>() {
//                    @Override
//                    public Task<Boolean> then(Task<List > task) throws Exception {
//                        fetchedOrderedRecipes = task.getResult();
//
//                        // Next, fetch related photos
//                        return self.getPhotosForModelsTask(task);
//                    }
//                }).onSuccess(new Continuation<Boolean, Void>() {
//            @Override
//            public Void then(Task<Boolean> task) throws Exception {
//
//                self.setRegisterCellClass(IEAOrderedPeopleCell.getType(), OrderedRecipesSection.sectionOrderedPeople.ordinal());
//                self.setRegisterCellClassWhenSelected(IEAOrderedRecipeCell.getType(), OrderedRecipesSection.sectionRecipes.ordinal());
//
//                self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Ordered_People), OrderedRecipesSection.sectionOrderedPeople.ordinal());
//                self.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Ordered_Recipes), OrderedRecipesSection.sectionRecipes.ordinal());
//
//                self.setSectionItems(IEAOrderedPeople.convertToOrderedPeople(self.orderedPeople, (self.orderedPeople.belongToModel), self), OrderedRecipesSection.sectionOrderedPeople.ordinal());
//
////                self.setSectionItems(fetchedOrderedRecipes, OrderedRecipesSection.sectionRecipes.ordinal());
//                self.configureDetailSection(self.fetchedOrderedRecipes, R.string.Empty_for_Ordered_Recipe, IEAOrderedRecipeCell.getType(), OrderedRecipesSection.sectionRecipes.ordinal());
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
//        recipe.belongToModel = self.orderedPeople;
//        self.selectedModel = recipe;
//        self.performSegueWithIdentifier(MainSegueIdentifier.detailRecipeSegueIdentifier, self);
//    }
//
//    @Override
//    public void segueForRecipeDetailViewController(IEARecipeDetailViewController destination, Intent sender) {
//        /// Show detailed recipe
//        self.setTransferedModel(sender, self.selectedModel);
//    }
//
//    @Override
//    public void segueForEditRecipeViewController(IEAEditRecipeViewController destination, Intent sender) {
//        // Add recipe
//        self.setTransferedModelForEdit(sender, new Recipe(self.orderedPeople), true);
//    }
//
//    // MARK: Cell's Events
//    public void performSegueForAddingRecipe() {
//        self.performSegueWithIdentifier(MainSegueIdentifier.editRecipeSegueIdentifier, self);
//    }
//
//    // MARK: NSNotificationCenter notification handlers
//    @Override
//    protected void TakenPhotoWasChanged(NSNotification note) {
//        self.queryOrderedRecipesList();
//    }
//
//    @Override
//    protected void RecipeWasCreated(NSNotification note) {
//        self.queryOrderedRecipesList();
//    }
//
//    @Override
//    protected void ReviewWasCreated(NSNotification note) {
//        self.queryOrderedRecipesList();
//    }
}
