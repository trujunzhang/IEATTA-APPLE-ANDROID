package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.com.parse.models.Recipe;

public class IEAOrderedRecipeCell extends IEAViewHolder {
    @Override
    public CellType getType() {
        return new CellType(IEAOrderedRecipeCell.class, R.layout.ordered_recipe_cell);
    }

    private IEAOrderedRecipeCell self = this;

//    @IBOutlet weak var avatarView: AvatarView!

    private TextView displayNameLabel;
    private TextView subtitleLabel;

    public IEAOrderedRecipeCell(View itemView) {
        super(itemView);

        self.displayNameLabel = (TextView) itemView.findViewById(R.id.titleTextView);
        self.subtitleLabel = (TextView) itemView.findViewById(R.id.addressTextView);
    }

    @Override
    public void updateWithModel(Object model) {
        Recipe more  = (Recipe) model;
        self.displayNameLabel.setText(more.displayName);

    }
}
