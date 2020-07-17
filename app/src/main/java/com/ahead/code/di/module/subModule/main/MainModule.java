package com.ahead.code.di.module.subModule.main;

import com.ahead.code.data.network.interfaces.Api;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @Provides
    static Api provideApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }
}
