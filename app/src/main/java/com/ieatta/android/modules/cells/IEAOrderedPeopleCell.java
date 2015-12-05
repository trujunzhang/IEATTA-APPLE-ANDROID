package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.cells.model.IEAOrderedPeople;

public class IEAOrderedPeopleCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEAOrderedPeopleCell.class, R.layout.ordered_people_cell);
    }

    private IEAOrderedPeopleCell self = this;

//    @IBOutlet weak var avatarView: AvatarView!

    private TextView nameLabel;
    private TextView addressLabel;

    public IEAOrderedPeopleCell(View itemView) {
        super(itemView);
        self.nameLabel = (TextView) itemView.findViewById(R.id.titleTextView);
        self.addressLabel = (TextView) itemView.findViewById(R.id.addressTextView);
    }

    @Override
    public void render(Object model) {
        IEAOrderedPeople more  = (IEAOrderedPeople) model;
        self.nameLabel.setText(more.model.displayName);
    }
}
