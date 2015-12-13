package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.extensions.viewkit.AvatarView;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.cells.model.IEAOrderedPeople;

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
        IEAOrderedPeople model = (IEAOrderedPeople) value;
        self.nameLabel.setText(model.model.displayName);
        self.addressLabel.setText(model.model.address);
        self.avatarView.loadNewPhotoByModel(model.model, R.drawable.blank_user_small);

//        Recipe.queryOrderedRecipesCount(model.model!, event: model.event!).continueWithBlock { (task) -> AnyObject? in
//
//            dispatch_async(dispatch_get_main_queue(), { () -> Void in
//
//            if let _ = task.error{
//                self.setRecipeInformation(L10n.FetchRecipeCountFailure.string)
//            }else{
//                let recipesCount = task.result as! Int
//                model.model?.recipesCount = recipesCount
//                self.setRecipeInformation(tr(L10n.ReipesOrderedCount(recipesCount)))
//            }
//            })
//
//            return nil
//        }
    }
}
