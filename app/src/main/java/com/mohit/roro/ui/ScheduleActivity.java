package com.mohit.roro.ui;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mohit.roro.R;
import com.mohit.roro.network.model.Task;
import com.mohit.roro.utils.AppConstants;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ScheduleActivity";

    private EditText meeting_date, start_time, end_time, description;
    private List<Task> taskList;
    private Button btnSubmit;
    private Intent intent;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        if (getIntent() != null) {
            intent = getIntent();
        }

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
        taskList = new Gson().fromJson(
                new Gson().toJson(intent.getStringExtra("taskList")),
                type);
        Log.d(TAG, "initViews: " + taskList);

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
                break;

            case R.id.start_time:
            case R.id.end_time:
                Log.d(TAG, "onClick: Start&End: ");
                try {
                    Date date = AppConstants.SIMPLE_DATE_FORMAT.parse(meeting_date.getText().toString());
                    TimePickerDialog picker = new TimePickerDialog(ScheduleActivity.this,
                            (tp, sHour, sMinute) ->
                                    ((EditText) findViewById(v.getId())).setText(
                                            String.format("%s:%s", sHour, sMinute)),
                            date.getHours(),
                            date.getMinutes(),
                            true);
                    picker.show();
                } catch (ParseException e) {
                    Log.e(TAG, "onClick: " + e.getMessage());
                    e.printStackTrace();
                }
                break;
        }
    }
}
