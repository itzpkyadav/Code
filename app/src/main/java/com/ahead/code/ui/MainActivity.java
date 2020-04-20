package com.ahead.code.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ahead.code.R;
import com.ahead.code.databinding.ActivityMainBinding;
import com.ahead.code.ui.adapter.TaskAdapter;
import com.ahead.code.ui.base.BaseActivity;
import com.ahead.code.ui.viewModel.MainViewModel;
import com.google.gson.Gson;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private TaskAdapter taskAdapter;
    private String helloWorld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainViewModel.class);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        Log.d(TAG, "onCreate: ViewModel: " + viewModel);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        initViews();

        Log.d(TAG, "onCreate: " + helloWorld);

        viewModel.getSchedule().observe(this, list -> {
            taskAdapter.setList(list);
            taskAdapter.notifyDataSetChanged();
        });

        viewModel.getDate().observe(this, date -> {
            binding.tvDate.setText(date);
        });
    }

    private void initViews() {
        setActionBar(binding.toolbar);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter();
        binding.recyclerView.setAdapter(taskAdapter);
        taskAdapter.notifyDataSetChanged();

        binding.btnSchedule.setOnClickListener(this);
        binding.llPrevious.setOnClickListener(this);
        binding.llNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llPrevious:
                viewModel.changeDate(-1);
                break;
            case R.id.llNext:
                viewModel.changeDate(1);
                break;

            case R.id.btnSchedule:
                Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
                intent.putExtra("date", binding.tvDate.getText().toString());
                intent.putExtra("taskList", new Gson().toJson(taskAdapter.getList()));
                startActivity(intent);
                break;
        }
    }
}
