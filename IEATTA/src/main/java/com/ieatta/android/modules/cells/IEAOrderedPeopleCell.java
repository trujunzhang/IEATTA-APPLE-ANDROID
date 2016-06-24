package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.android.IEAApplication;
import com.ieatta.android.R;
import com.tableview.model.IEAOrderedPeople;
import com.tableview.storage.models.CellType;
import com.ieatta.android.extensions.viewkit.AvatarView;
import com.tableview.adapter.IEAViewHolder;


import org.ieatta.database.query.RecipeQuery;
import org.wikipedia.views.GoneIfEmptyTextView;

import bolts.Continuation;
import bolts.Task;

public class IEAOrderedPeopleCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEAOrderedPeopleCell.class, R.layout.cell_ordered_people);
    }

    private IEAOrderedPeopleCell self = this;

    private AvatarView avatarView;

    private TextView nameLabel;
    private GoneIfEmptyTextView recipesCountLabel;

    public IEAOrderedPeopleCell(View itemView) {
        super(itemView);
        this.avatarView = (AvatarView) itemView.findViewById(R.id.page_list_item_image);
        this.nameLabel = (TextView) itemView.findViewById(R.id.page_list_item_title);
        this.recipesCountLabel = (GoneIfEmptyTextView) itemView.findViewById(R.id.page_list_item_description);
    }

    private void setOrderedPeople(IEAOrderedPeople model) {
        this.nameLabel.setText(model.getDisplayName());
        this.avatarView.loadNewPhotoByModel(model.getTeamUUID());

        new RecipeQuery().queryOrderedRecipesCount(model.getTeamUUID(), model.getEventUUID()).onSuccess(new Continuation<Long, Void>() {
            @Override
            public Void then(Task<Long> task) throws Exception {
                final String info = String.format("%d %s", task.getResult(), IEAApplication.getInstance().getResources().getString(R.string.Recipes_Ordered_Count));
                IEAOrderedPeopleCell.this.recipesCountLabel.setText(info);
                return null;
            }
        }, Task.UI_THREAD_EXECUTOR);

//        self.nameLabel.setText(model.model.displayName);
//        self.addressLabel.setText(model.model.address);
//        self.avatarView.loadNewPhotoByModel(model.model, R.drawable.blank_user_small);
//
//        self.addFoodButton.setVisibility(View.GONE);
//        if (model.hideButton == false) {
//            self.addFoodButton.setVisibility(View.VISIBLE);
//            self.addFoodButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    model.viewController.performSegueForAddingRecipe();
//                }
//            });
//        }
//
//        Recipe.queryOrderedRecipesCount(model.model, model.event)
//                .onSuccess(new Continuation<Integer, Object>() {
//                    @Override
//                    public Object then(Task<Integer> task) throws Exception {
//
//                        int recipesCount = task.getResult();
//                        model.model.recipesCount = recipesCount;
//                        String info = EnvironmentUtils.sharedInstance.getGlobalContext().getResources().getString(R.string.Recipes_Ordered_Count);
//                        info = recipesCount + " " + info;
//                        self.addressLabel.setText(info);
//
//                        return null;
//                    }
//                }, Task.UI_THREAD_EXECUTOR).continueWith(new Continuation<Object, Object>() {
//            @Override
//            public Object then(Task<Object> task) throws Exception {
//                if (task.isFaulted()) {
//
//                    String info = EnvironmentUtils.sharedInstance.getGlobalContext().getResources().getString(R.string.Fetch_recipe_count_failure);
//                    self.addressLabel.setText(info);
//
//                }
//                return null;
//            }
//        }, Task.UI_THREAD_EXECUTOR);
    }

    @Override
    public void render(Object value) {
        this.setOrderedPeople((IEAOrderedPeople) value);
    }

}
