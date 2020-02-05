package com.ahead.code.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.ahead.code.R;

public class ScheduleActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ScheduleActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        initViews();

        findViewById(R.id.llBack).setOnClickListener(v -> onBackPressed());
    }

    private void initViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);

        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSubmit) {
            Log.d(TAG, "onClick: Submit: ");
        }
    }
}
