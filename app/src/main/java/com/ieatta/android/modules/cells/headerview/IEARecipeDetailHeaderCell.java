package com.ieatta.android.modules.cells.headerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.cells.model.IEARecipeHeader;

/**
 * Created by djzhang on 12/2/15.
 */
public class IEARecipeDetailHeaderCell  extends IEAViewHolder {
    public static CellType  getType() {
        return new CellType(IEARecipeDetailHeaderCell.class, R.layout.recipe_detail_header_cell);
    }

    @Override
    protected boolean shouldClickItem() {
        return false;
    }

    private IEARecipeDetailHeaderCell self = this;

    private TextView displayNameLabel;
    private TextView priceNameLabel;
    private ImageView ratingImageView;

    private TextView editButton;
    private TextView secondButton;
    private TextView thirdButton;

    public IEARecipeDetailHeaderCell(View itemView) {
        super(itemView);

        self.displayNameLabel = (TextView) itemView.findViewById(R.id.displayNameTextView);
        self.priceNameLabel = (TextView) itemView.findViewById(R.id.priceNameTextView);
        self.ratingImageView = (ImageView) itemView.findViewById(R.id.business_review_star_rating);

        self.editButton = (TextView) itemView.findViewById(R.id.editNameTextView);
        self.editButton.setText(R.string.Edit_Recipe);
        self.secondButton = (TextView) itemView.findViewById(R.id.secondTextView);
        self.secondButton.setText(R.string.Write_Review);
        self.thirdButton = (TextView) itemView.findViewById(R.id.thirdTextView);
        self.thirdButton.setText(R.string.See_Reviews);
    }

    @Override
    public void render(Object value) {
        IEARecipeHeader model  = (IEARecipeHeader) value;

        self.displayNameLabel.setText(model.model.displayName);
        self.priceNameLabel.setText(""+model.model.cost);
    }
}