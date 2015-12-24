package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.extensions.viewkit.AvatarView;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.cells.model.IEAOrderedPeople;
import com.ieatta.com.parse.models.Recipe;

import bolts.Continuation;
import bolts.Task;

public class IEAOrderedPeopleCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEAOrderedPeopleCell.class, R.layout.ordered_people_cell);
    }

    private IEAOrderedPeopleCell self = this;

    private AvatarView avatarView;

    private TextView nameLabel;
    private TextView addressLabel;

    public IEAOrderedPeopleCell(View itemView) {
        super(itemView);
        self.avatarView = (AvatarView) itemView.findViewById(R.id.avatarView);
        self.nameLabel = (TextView) itemView.findViewById(R.id.titleTextView);
        self.addressLabel = (TextView) itemView.findViewById(R.id.addressTextView);
    }

    @Override
    public void render(Object value) {
        final IEAOrderedPeople model = (IEAOrderedPeople) value;
        self.nameLabel.setText(model.model.displayName);
        self.addressLabel.setText(model.model.address);
        self.avatarView.loadNewPhotoByModel(model.model, R.drawable.blank_user_small);

        // TODO: djzhang: fixing
        Recipe.queryOrderedRecipesCount(model.model, model.event).onSuccess(new Continuation<Integer, Object>() {
            @Override
            public Object then(Task<Integer> task) throws Exception {

                int recipesCount = task.getResult();
                model.model.recipesCount = recipesCount;
                String info = EnvironmentUtils.sharedInstance.getGlobalContext().getResources().getString(R.string.Recipes_Ordered_Count);
                info = info + ": " + recipesCount;
                self.setRecipeInformation(info);
                return null;
            }
        }).continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
                if (task.isFaulted()) {
                    String info = EnvironmentUtils.sharedInstance.getGlobalContext().getResources().getString(R.string.Fetch_recipe_count_failure);
                    self.setRecipeInformation(info);
                }
                return null;
            }
        });
    }

    private void setRecipeInformation(String info){
        self.addressLabel.setText(info);
    }
}
