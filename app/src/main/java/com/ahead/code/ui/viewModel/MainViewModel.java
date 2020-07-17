package com.ahead.code.ui.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahead.code.data.network.interfaces.Api;
import com.ahead.code.data.network.model.Task;
import com.ahead.code.data.repository.Repository;
import com.ahead.code.utils.AppConstants;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {

    private static final String TAG = "MainViewModel";

    private MutableLiveData<String> dateLiveData = new MutableLiveData<>();
    private Repository repository;
    private Calendar calendar;
    public String date;

    private Api api;

    @Inject
    public MainViewModel(Api api) {
        this.api = api;
        repository = Repository.getInstance();
        calendar = Calendar.getInstance();
        date = AppConstants.SIMPLE_DATE_FORMAT.format(calendar.getTime());
        setDate();

        if (this.api == null)
            Log.d(TAG, "MainViewModel: api is NULL.");
        else
            Log.d(TAG, "MainViewModel: api is NOT NULL.");
    }

    public void changeDate(int i) {
        calendar.add(Calendar.DATE, i);
        date = AppConstants.SIMPLE_DATE_FORMAT.format(calendar.getTime());
        setDate();
        Log.d(TAG, "changeDate: " + date);
        getSchedule();
    }

    public LiveData<List<Task>> getSchedule() {
        return repository.getSchedule(date);
    }

    private void setDate() {
        dateLiveData.setValue(date);
    }

    public LiveData<String> getDate() {
        return dateLiveData;
    }

    @Override
    protected void onCleared() {
        Log.d(TAG, "onCleared: ");
        super.onCleared();
    }
}
