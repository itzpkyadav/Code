package com.ahead.code.di.module;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    static String stringProvides() {
        return "Hello World!!";
    }
}
