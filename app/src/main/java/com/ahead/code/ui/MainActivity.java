package com.ahead.code.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahead.code.R;
import com.ahead.code.network.APIClient;
import com.ahead.code.network.APITransaction;
import com.ahead.code.network.interfaces.APIResponse;
import com.ahead.code.network.interfaces.ApiInterface;
import com.ahead.code.network.model.Task;
import com.ahead.code.ui.adapter.TaskAdapter;
import com.ahead.code.utils.AppConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private Handler handler = new Handler();
    private ApiInterface apiInterface;
    private TaskAdapter taskAdapter;
    private Calendar calendar;
    private TextView tvDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        getTask();
    }

    private void initViews() {
        apiInterface = APIClient.create();
        calendar = Calendar.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);

        LinearLayout llPrevious = findViewById(R.id.llPrevious);
        LinearLayout llNext = findViewById(R.id.llNext);
        Button btnSchedule = findViewById(R.id.btnSchedule);
        tvDate = findViewById(R.id.tvDate);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter();
        recyclerView.setAdapter(taskAdapter);
        taskAdapter.notifyDataSetChanged();

        btnSchedule.setOnClickListener(this);
        llPrevious.setOnClickListener(this);
        llNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llPrevious:
                calendar.add(Calendar.DATE, -1);
                getTask();
                break;
            case R.id.llNext:
                calendar.add(Calendar.DATE, 1);
                getTask();
                break;

            case R.id.btnSchedule:
                Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
                intent.putExtra("date", AppConstants.SIMPLE_DATE_FORMAT.format(calendar.getTime()));
                intent.putExtra("taskList", new Gson().toJson(taskAdapter.getList()));
                startActivity(intent);
                break;
        }
    }

    private void getTask() {
        handler.post(() -> {
            String date = AppConstants.SIMPLE_DATE_FORMAT.format(calendar.getTime());
            Log.d(TAG, "getTask: " + date);
            tvDate.setText(date);

            retrofit2.Call request = apiInterface.getSchedule(date);

            APITransaction.startNetworkService(request, new APIResponse() {
                @Override
                public void OnResponseAPI(Object object) {
                    try {
                        Log.d(TAG, "OnResponseAPI: " + date + " : " + new Gson().toJson(object));
                        Type type = new TypeToken<List<Task>>() {
                        }.getType();
                        taskAdapter.setList(new Gson().fromJson(new Gson().toJson(object), type));
                        taskAdapter.notifyDataSetChanged();
                    } catch (Exception e) {
                        Log.d(TAG, "OnResponseAPI: Exception: " + e.getMessage());
                        e.printStackTrace();
                    }
                }

                @Override
                public void OnErrorAPI(String error) {
                    Log.e(TAG, "OnErrorAPI: " + error);
                }
            });
        });
    }
}
