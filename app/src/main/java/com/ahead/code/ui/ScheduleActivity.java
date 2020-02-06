package com.ahead.code.ui;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.ahead.code.R;
import com.ahead.code.network.model.Task;
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

    private TextView meeting_date, start_time, end_time;
    private EditText description;
    private List<Task> taskList;
    private Button btnSubmit;
    private Intent intent;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        if (getIntent() != null)
            intent = getIntent();

        initViews();

        findViewById(R.id.llBack).setOnClickListener(v -> onBackPressed());
    }

    private void initViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);

        meeting_date = findViewById(R.id.meeting_date);
        description = findViewById(R.id.description);
        start_time = findViewById(R.id.start_time);
        btnSubmit = findViewById(R.id.btnSubmit);
        end_time = findViewById(R.id.end_time);

        meeting_date.setText(intent.getStringExtra("date"));
        Type type = new TypeToken<List<Task>>() {
        }.getType();
        taskList = (new Gson().fromJson(intent.getStringExtra("taskList"), type));
        Log.d(TAG, "initViews: " + intent.getStringExtra("taskList"));

        Button btnSubmit = findViewById(R.id.btnSubmit);

        start_time.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        end_time.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                Log.d(TAG, "onClick: Submit: ");
                try {
                    boolean isAvailable = true;
                    Date startDate = AppConstants.SIMPLE_DATETIME_FORMAT.parse(meeting_date.getText().toString() + " " + start_time.getText().toString());
                    Date endDate = AppConstants.SIMPLE_DATETIME_FORMAT.parse(meeting_date.getText().toString() + " " + end_time.getText().toString());

                    if (startDate.before(endDate)) {
                        for (Task task : taskList) {
                            Date listStartDate = AppConstants.SIMPLE_DATETIME_FORMAT.parse(meeting_date.getText().toString() + " " + task.getStart_time());
                            Date listEndDate = AppConstants.SIMPLE_DATETIME_FORMAT.parse(meeting_date.getText().toString() + " " + task.getEnd_time());

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
                    Date date = AppConstants.SIMPLE_DATE_FORMAT.parse(meeting_date.getText().toString());

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
