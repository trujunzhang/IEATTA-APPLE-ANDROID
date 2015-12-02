package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.com.parse.models.Recipe;

public class IEAOrderedRecipeCell extends IEAViewHolder {
    public static int layoutResId = R.layout.near_restaurant_cell;

    private IEAOrderedRecipeCell self = this;

//    @IBOutlet weak var avatarView: AvatarView!

    private TextView displayNameLabel;
    private TextView subtitleLabel;

    public IEAOrderedRecipeCell(View itemView) {
        super(itemView);
    }

    @Override
    public void updateWithModel(Object model) {
        Recipe more  = (Recipe) model;
        self.displayNameLabel.setText(more.displayName);

    }
}
