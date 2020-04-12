package com.ahead.code.data.network.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("schedule")
    Call<Object> getSchedule(@Query("date") String date);
}
