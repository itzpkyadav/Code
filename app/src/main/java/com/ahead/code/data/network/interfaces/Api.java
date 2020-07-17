package com.ahead.code.data.network.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("schedule")
    Call<Object> getSchedule(@Query("date") String date);
}
