package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.extensions.viewkit.AvatarView;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.com.parse.models.Recipe;

public class IEAOrderedRecipeCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEAOrderedRecipeCell.class, R.layout.ordered_recipe_cell);
    }

    private IEAOrderedRecipeCell self = this;

    private AvatarView avatarView;

    private TextView displayNameLabel;
    private TextView priceLabel;
    private ImageView ratingImageView;

    public IEAOrderedRecipeCell(View itemView) {
        super(itemView);
        self.avatarView = (AvatarView) itemView.findViewById(R.id.avatarView);
        self.displayNameLabel = (TextView) itemView.findViewById(R.id.titleTextView);
        self.priceLabel = (TextView) itemView.findViewById(R.id.priceTextView);
        self.ratingImageView = (ImageView) itemView.findViewById(R.id.business_review_star_rating);
    }

    @Override
    public void render(Object value) {
        Recipe model  = (Recipe) value;
        self.displayNameLabel.setText(model.displayName);
        self.priceLabel.setText(model.cost+"");
//        self.ratingImageView.setImageLevel(model.);
        self.avatarView.loadNewPhotoByModel(model, R.drawable.placeholder_photo);
    }
}
