package com.ieatta.android.modules.cells.edit;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.common.edit.DatePickerCellModel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by djzhang on 12/2/15.
 */
public class IEADatePickerCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEADatePickerCell.class, R.layout.date_picker_cell);
    }

    private IEADatePickerCell self = this;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM dd yyyy hh:mm aa");
    private boolean isDialog = false;

    private Date editedDate;

    private TextView editText;
    private FrameLayout datePickerLayout;
    private Button clickedView;


    private DatePickerCellModel model;

    public IEADatePickerCell(View itemView) {
        super(itemView);

        this.clickedView = (Button) itemView.findViewById(R.id.clicked_view);
        this.datePickerLayout = (FrameLayout) itemView.findViewById(R.id.date_picker_layout);
        this.editText = (TextView) itemView.findViewById(R.id.editText);

        this.clickedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDialog == true) {
                    return;
                }

                new SlideDateTimePicker.Builder(EnvironmentUtils.sharedInstance.getCurrentFragmentManager())
                        .setListener(listener)
                        .setInitialDate(editedDate)
                                //.setMinDate(minDate)
                                //.setMaxDate(maxDate)
                        .setIs24HourTime(true)
//                                .setTheme(SlideDateTimePicker.HOLO_DARK)
//                                .setIndicatorColor(Color.parseColor("#990000"))
                        .build()
                        .show();
            }
        });
    }

    @Override
    public void render(Object value) {
        self.model = (DatePickerCellModel) value;
        self.reloadTableRow(self.model.date);
    }

    private SlideDateTimeListener listener = new SlideDateTimeListener() {

        @Override
        public void onDateTimeSet(Date date) {
            reloadTableRow(date);
        }

        // Optional cancel listener
        @Override
        public void onDateTimeCancel() {
        }
    };

    private void reloadTableRow(Date date) {
        self.editedDate = date;
        this.editText.setText(dateFormatter.format(editedDate));
        isDialog = false;
    }
}