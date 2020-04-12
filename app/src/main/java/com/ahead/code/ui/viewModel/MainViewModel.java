package com.ahead.code.ui.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ahead.code.data.network.model.Task;
import com.ahead.code.data.repository.Repository;
import com.ahead.code.utils.AppConstants;

import java.util.Calendar;
import java.util.List;

public class MainViewModel extends ViewModel {

    private static final String TAG = "MainViewModel";

    private Repository repository;
    private Calendar calendar;
    public String date;

    public MainViewModel() {
        repository = Repository.getInstance();
        calendar = Calendar.getInstance();
        date = AppConstants.SIMPLE_DATE_FORMAT.format(calendar.getTime());
    }

    public void changeDate(int i) {
        calendar.add(Calendar.DATE, i);
        date = AppConstants.SIMPLE_DATE_FORMAT.format(calendar.getTime());
        Log.d(TAG, "changeDate: " + date);
        getSchedule();
    }

    public LiveData<List<Task>> getSchedule() {
        return repository.getSchedule(date);
    }
}
