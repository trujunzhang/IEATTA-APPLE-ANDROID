package com.ieatta.android.modules.cells.headerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.cells.model.IEAEventHeader;

/**
 * Created by djzhang on 12/2/15.
 */
public class IEAEventHeaderCell  extends IEAViewHolder {
    public static CellType  getType() {
        return new CellType(IEAEventHeaderCell.class, R.layout.event_header_cell);
    }

    @Override
    protected boolean shouldClickItem() {
        return false;
    }

    private IEAEventHeaderCell self = this;


    private TextView restaurantNameLabel;
    private TextView displayNameLabel;
    private ImageView ratingImageView;

    private TextView editButton;
    private TextView firstButton;
    private TextView secondButton;
    private TextView thirdButton;

    public IEAEventHeaderCell(View itemView) {
        super(itemView);

        self.restaurantNameLabel = (TextView) itemView.findViewById(R.id.restaurantNameTextView);
        self.displayNameLabel = (TextView) itemView.findViewById(R.id.displayNameTextView);
        self.ratingImageView = (ImageView) itemView.findViewById(R.id.business_review_star_rating);

        self.editButton = (TextView) itemView.findViewById(R.id.editNameTextView);
        self.editButton.setText(R.string.Edit_Event);
        self.firstButton = (TextView) itemView.findViewById(R.id.firstTextView);
        self.firstButton.setText(R.string.Select_People);
        self.secondButton = (TextView) itemView.findViewById(R.id.secondTextView);
        self.secondButton.setText(R.string.Write_Review);
        self.thirdButton = (TextView) itemView.findViewById(R.id.thirdTextView);
        self.thirdButton.setText(R.string.See_Reviews);
    }

    @Override
    public void render(Object value) {
        IEAEventHeader model  = (IEAEventHeader) value;

        self.restaurantNameLabel.setText(model.model.belongToModel.displayName);
        self.displayNameLabel.setText(model.model.displayName);

    }
}
