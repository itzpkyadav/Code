package com.ahead.code.di.module.subModule.main;

import androidx.lifecycle.ViewModel;

import com.ahead.code.di.annotation.ViewModelKey;
import com.ahead.code.ui.viewModel.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    public abstract ViewModel bindMainViewModel(MainViewModel viewModel);

}
