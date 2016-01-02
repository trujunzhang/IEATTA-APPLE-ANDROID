package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.com.parse.models.Event;

public class IEARestaurantEventsCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEARestaurantEventsCell.class, R.layout.restaurant_events_cell);
    }

    private IEARestaurantEventsCell self = this;

    private TextView infoLabel;
    private TextView timeInfoLabel;
    private TextView timeAgoLabelLabel;

    public IEARestaurantEventsCell(View itemView) {
        super(itemView);

        self.infoLabel = (TextView) itemView.findViewById(R.id.titleTextView);
        self.timeInfoLabel = (TextView) itemView.findViewById(R.id.infoTextView);
        self.timeAgoLabelLabel = (TextView) itemView.findViewById(R.id.timeAgoTextView);
    }

    @Override
    public void render(Object value) {
        Event more = (Event) value;

        self.infoLabel.setText(more.displayName);
        if (more.waiter == null || more.waiter.equals("")) {
            self.timeInfoLabel.setText(R.string.No_waiters_servered_for_you);
        } else {
            self.timeInfoLabel.setText(more.waiter);
        }

        self.timeAgoLabelLabel.setText(more.getTimeAgoString());

    }
}
