package com.ieatta.android.modules.cells.headerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.cells.model.IEARestaurantDetailHeader;

/**
 * Created by djzhang on 12/2/15.
 */
public class IEARestaurantDetailHeaderCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEARestaurantDetailHeaderCell.class, R.layout.restaurant_detail_header_cell);
    }

    @Override
    protected boolean shouldClickItem() {
        return false;
    }

    private IEARestaurantDetailHeaderCell self = this;

    private TextView displayNameLabel;
    private ImageView ratingImageView;

    private TextView editButton;
    private TextView firstButton;
    private TextView secondButton;
    private TextView thirdButton;

    public IEARestaurantDetailHeaderCell(View itemView) {
        super(itemView);

        self.displayNameLabel = (TextView) itemView.findViewById(R.id.displayNameTextView);
        self.ratingImageView = (ImageView) itemView.findViewById(R.id.business_review_star_rating);

        self.editButton = (TextView) itemView.findViewById(R.id.editNameTextView);
        self.editButton.setText(R.string.Edit_Restaurant);
        self.firstButton = (TextView) itemView.findViewById(R.id.firstTextView);
        self.firstButton.setText(R.string.Add_Event);
        self.secondButton = (TextView) itemView.findViewById(R.id.secondTextView);
        self.secondButton.setText(R.string.Write_Review);
        self.thirdButton = (TextView) itemView.findViewById(R.id.thirdTextView);
        self.thirdButton.setText(R.string.See_Reviews);
    }

    @Override
    public void render(Object value) {
        IEARestaurantDetailHeader model = (IEARestaurantDetailHeader) value;

        self.displayNameLabel.setText(model.model.displayName);
        self.ratingImageView.setImageLevel(2);
    }
}
