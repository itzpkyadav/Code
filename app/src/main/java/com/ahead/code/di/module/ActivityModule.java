package com.ahead.code.di.module;

import com.ahead.code.di.module.subModule.main.MainViewModelModule;
import com.ahead.code.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(
            modules = {MainViewModelModule.class}
    )
    abstract MainActivity contributeMainActivity();
}
