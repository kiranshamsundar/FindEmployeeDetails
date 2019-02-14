package com.find.employee.app.di.module;


import com.find.employee.app.view.EmployeeFindAppHomeActivity;
import com.find.employee.app.view.EmployeeListFragmentBindingModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {EmployeeListFragmentBindingModule.class})
    abstract EmployeeFindAppHomeActivity bindHomeActivity();
}
