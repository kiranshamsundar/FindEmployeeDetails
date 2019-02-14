package com.find.employee.app.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;


import com.find.employee.app.di.util.ViewModelKey;
import com.find.employee.app.util.ViewModelFactory;
import com.find.employee.app.view.EmployeesViewModel;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Singleton
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EmployeesViewModel.class)
    abstract ViewModel bindEmployeeFragListViewModel(EmployeesViewModel countryFactsViewModel);


    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
