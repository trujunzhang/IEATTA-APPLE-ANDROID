package com.ieatta.android.modules.cells;

import android.view.View;
import android.widget.TextView;

import com.ieatta.android.R;
import com.tableview.adapter.IEAViewHolder;
import com.tableview.storage.models.CellType;

import org.ieatta.database.models.DBEvent;
import org.ieatta.database.utils.DBUtil;


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

    private void setEvent(DBEvent event) {
        self.infoLabel.setText(event.getDisplayName());
        String waiter = event.getWaiter();
        if (waiter == null || waiter.equals("")) {
            self.timeInfoLabel.setText(R.string.No_waiters_servered_for_you);
        } else {
            self.timeInfoLabel.setText(waiter);
        }

        this.timeAgoLabelLabel.setText(DBUtil.getTimeAgoString(event.getObjectCreatedDate()));
    }


    @Override
    public void render(Object value) {
        this.setEvent((DBEvent) value);
    }
}
