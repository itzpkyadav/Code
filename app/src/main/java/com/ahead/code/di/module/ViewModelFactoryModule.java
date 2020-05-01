package com.ahead.code.di.module;

import androidx.lifecycle.ViewModelProvider;

import com.ahead.code.ui.viewModel.factory.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);
}
