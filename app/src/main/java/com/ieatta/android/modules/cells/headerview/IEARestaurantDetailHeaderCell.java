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

    private TextView restaurantNameLabel;
    private ImageView ratingImageView;

    private TextView editButton;
    private TextView firstButton;
    private TextView secondButton;
    private TextView thirdButton;

    public IEARestaurantDetailHeaderCell(View itemView) {
        super(itemView);

        self.restaurantNameLabel = (TextView) itemView.findViewById(R.id.displayNameTextView);
        self.ratingImageView = (ImageView) itemView.findViewById(R.id.business_review_star_rating);

        self.editButton = (TextView) itemView.findViewById(R.id.editNameTextView);
        self.firstButton = (TextView) itemView.findViewById(R.id.firstTextView);
        self.secondButton = (TextView) itemView.findViewById(R.id.secondTextView);
        self.thirdButton = (TextView) itemView.findViewById(R.id.thirdTextView);
    }

    @Override
    public void render(Object value) {
        IEARestaurantDetailHeader model = (IEARestaurantDetailHeader) value;


    }
}
