package com.ieatta.android.modules.cells.edit;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.com.parse.models.Restaurant;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by djzhang on 12/2/15.
 */
public class IEADatePickerCell  extends IEAViewHolder {
    @Override
    public CellType getType() {
        return new CellType(IEADatePickerCell.class,R.layout.date_picker_cell);
    }

    private IEADatePickerCell self = this;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM dd yyyy hh:mm aa");
    private boolean isDialog = false;

    private Date editedDate;

    private TextView editText;
    private FrameLayout datePickerLayout;
    private Button clickedView;


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

//                new SlideDateTimePicker.Builder(IntentUtils.sharedInstance.supportFragmentManager)
//                        .setListener(listener)
//                        .setInitialDate(editedDate)
//                                //.setMinDate(minDate)
//                                //.setMaxDate(maxDate)
//                        .setIs24HourTime(true)
////                                .setTheme(SlideDateTimePicker.HOLO_DARK)
////                                .setIndicatorColor(Color.parseColor("#990000"))
//                        .build()
//                        .show();
            }
        });
    }

    @Override
    public void updateWithModel(Object model) {
        Restaurant more  = (Restaurant) model;
    }



    private SlideDateTimeListener listener = new SlideDateTimeListener() {

        @Override
        public void onDateTimeSet(Date date) {
            updateTableRow(date);
        }

        // Optional cancel listener
        @Override
        public void onDateTimeCancel() {
        }
    };

    private void updateTableRow(Date date) {
        editedDate = date;
        this.editText.setText(dateFormatter.format(editedDate));
        isDialog = false;
    }
}