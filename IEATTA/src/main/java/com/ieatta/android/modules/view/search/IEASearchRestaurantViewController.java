package com.ieatta.android.modules.view.search;

import com.ieatta.android.modules.IEASplitDetailViewController;

public class IEASearchRestaurantViewController extends IEASplitDetailViewController {


    enum SearchRestaurantSection {
        sectionRestaurants //;= 0
    }

//    private IEASearchRestaurantViewController self = this;
//    private EditText searchTextView;
//    private ImageView search_clear_Button;
//    private List<ParseModelAbstract/*Restaurant*/> fetchedRestaurants;
//    private Restaurant selectedModel;
//
//    private String keyword = "";
//
//    protected int getContentView() {
//        return R.layout.table_controller_serch_view;
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        self.searchTextView = (EditText) self.findViewById(R.id.searchTextView);
//        self.search_clear_Button = (ImageView) self.findViewById(R.id.search_clear);
//
//        self.searchTextView.setHint(R.string.Search_Hint_Restaurant);
//        self.searchTextView.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                self.keyword = s.toString();
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                self.queryNearRestaurant(self.keyword);
//            }
//        });
//
//        self.search_clear_Button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                self.searchTextView.setText("");
//            }
//        });
//
//        self.setRegisterCellClassWhenSelected(IEANearRestaurantsCell.getType(), SearchRestaurantSection.sectionRestaurants.ordinal());
//    }
//
//    private void queryNearRestaurant(String keyword) {
//        self.setSectionItems(new LinkedList (), SearchRestaurantSection.sectionRestaurants.ordinal());
//        if (keyword == null || keyword.isEmpty() == true) {
//            return;
//        }
//        new Restaurant().queryParseModels(keyword).onSuccessTask(new Continuation<List , Task<Boolean>>() {
//            @Override
//            public Task<Boolean> then(Task<List > task) throws Exception {
//                self.fetchedRestaurants = task.getResult();
//                self.fetchedRestaurants = RestaurantSortUtils.sort(self.fetchedRestaurants);
//
//                // Next, fetch related photos
//                return self.getPhotosForModelsTask(task);
//            }
//        }).onSuccess(new Continuation<Boolean, Object>() {
//            @Override
//            public Object then(Task<Boolean> task) throws Exception {
//                self.setSectionItems(self.fetchedRestaurants, SearchRestaurantSection.sectionRestaurants.ordinal());
//                return null;
//            }
//        }, Task.UI_THREAD_EXECUTOR);
//    }
//
//    @Override
//    public void whenSelectedEvent(Object model, NSIndexPath indexPath) {
//        self.selectedModel = (Restaurant) model;
//        self.performSegueWithIdentifier(MainSegueIdentifier.detailRestaurantSegueIdentifier, self);
//    }
//
//    @Override
//    protected void segueForRestaurantDetailViewController(IEARestaurantDetailActivity destination, Intent sender) {
//        /// Show detailed restaurant
//        self.setTransferedModel(sender, self.selectedModel);
//    }
//
//    // MARK: NSNotificationCenter notification handlers
//    @Override
//    protected void RestaurantWasCreated(NSNotification note) {
//        // **** Important(Just for Android) ****
//        self.queryNearRestaurant(self.keyword);
//    }
//
//    @Override
//    protected void TakenPhotoWasChanged(NSNotification note) {
//        // **** Important(Just for Android) ****
//        self.queryNearRestaurant(self.keyword);
//    }


}
