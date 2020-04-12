package com.ahead.code.data.repository;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ahead.code.data.network.APIClient;
import com.ahead.code.data.network.APITransaction;
import com.ahead.code.data.network.interfaces.APIResponse;
import com.ahead.code.data.network.interfaces.ApiInterface;
import com.ahead.code.data.network.model.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Repository {

    private static final String TAG = "Repository";

    private MutableLiveData<List<Task>> data = new MutableLiveData<>();

    private Handler handler = new Handler();
    private static Repository repository;
    private ApiInterface apiInterface;

    private Repository() {
        apiInterface = APIClient.create();
    }

    public static Repository getInstance() {
        if (repository == null)
            repository = new Repository();
        return repository;
    }

    public LiveData<List<Task>> getSchedule(String date) {

        handler.post(() -> {
            Log.d(TAG, "getTask: " + date);
            // binding.tvDate.setText(date);

            retrofit2.Call request = apiInterface.getSchedule(date);

            APITransaction.startNetworkService(request, new APIResponse() {
                @Override
                public void OnResponseAPI(Object object) {
                    try {
                        Log.d(TAG, "OnResponseAPI: " + date + " : " + new Gson().toJson(object));
                        Type type = new TypeToken<List<Task>>() {
                        }.getType();
                        data.setValue(new Gson().fromJson(new Gson().toJson(object), type));
                        // taskAdapter.setList(new Gson().fromJson(new Gson().toJson(object), type));
                        // taskAdapter.notifyDataSetChanged();
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

        return data;
    }
}
