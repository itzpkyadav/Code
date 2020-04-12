package com.ahead.code.ui;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.ahead.code.R;
import com.ahead.code.data.network.model.Task;
import com.ahead.code.databinding.ActivityScheduleBinding;
import com.ahead.code.utils.AppConstants;
import com.ahead.code.utils.AppHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ScheduleActivity";

    private ActivityScheduleBinding binding;

    private List<Task> taskList;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_schedule);

        if (getIntent() != null)
            intent = getIntent();

        initViews();

        findViewById(R.id.llBack).setOnClickListener(v -> onBackPressed());
    }

    private void initViews() {
        setActionBar(binding.toolbar);

        binding.meetingDate.setText(intent.getStringExtra("date"));
        Type type = new TypeToken<List<Task>>() {
        }.getType();
        taskList = (new Gson().fromJson(intent.getStringExtra("taskList"), type));
        Log.d(TAG, "initViews: " + intent.getStringExtra("taskList"));

        binding.startTime.setOnClickListener(this);
        binding.btnSubmit.setOnClickListener(this);
        binding.endTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                Log.d(TAG, "onClick: Submit: ");
                try {
                    boolean isAvailable = true;
                    Date startDate = AppConstants.SIMPLE_DATETIME_FORMAT.parse(binding.meetingDate.getText().toString() + " " + binding.startTime.getText().toString());
                    Date endDate = AppConstants.SIMPLE_DATETIME_FORMAT.parse(binding.meetingDate.getText().toString() + " " + binding.endTime.getText().toString());

                    if (startDate.before(endDate)) {
                        for (Task task : taskList) {
                            Date listStartDate = AppConstants.SIMPLE_DATETIME_FORMAT.parse(binding.meetingDate.getText().toString() + " " + task.getStart_time());
                            Date listEndDate = AppConstants.SIMPLE_DATETIME_FORMAT.parse(binding.meetingDate.getText().toString() + " " + task.getEnd_time());

                            if ((listStartDate.after(startDate) && listStartDate.before(endDate)) || (listEndDate.after(startDate) && listEndDate.before(endDate))) {
                                isAvailable = false;
                                break;
                            }
                        }

                        if (isAvailable)
                            Toast.makeText(this, "Slot available", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(this, "Slot not available", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(this, "Invalid Time Selection...", Toast.LENGTH_SHORT).show();

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.start_time:
            case R.id.end_time:
                try {
                    Date date = AppConstants.SIMPLE_DATE_FORMAT.parse(binding.meetingDate.getText().toString());

                    // Launch Time Picker Dialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                            (view, hourOfDay, minute) ->
                                    ((TextView) findViewById(v.getId())).setText(
                                            String.format("%s:%s", AppHelper.twoDigitString(hourOfDay), AppHelper.twoDigitString(minute))),
                            date.getHours(),
                            date.getMinutes(),
                            false);
                    timePickerDialog.show();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
