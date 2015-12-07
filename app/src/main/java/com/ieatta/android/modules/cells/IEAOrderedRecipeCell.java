package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.CellType;
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
    private TextView subtitleLabel;

    public IEAOrderedRecipeCell(View itemView) {
        super(itemView);
        self.avatarView = (AvatarView) itemView.findViewById(R.id.avatarView);
        self.displayNameLabel = (TextView) itemView.findViewById(R.id.titleTextView);
        self.subtitleLabel = (TextView) itemView.findViewById(R.id.addressTextView);
    }

    @Override
    public void render(Object value) {
        Recipe model  = (Recipe) value;
        self.displayNameLabel.setText(model.displayName);

        self.avatarView.loadNewPhotoByModel(model, R.drawable.blank_biz);
    }
}
