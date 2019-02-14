package com.find.employee.app.view;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class EmployeeListFragmentBindingModule {

    @ContributesAndroidInjector
    abstract EmployeeListFragment provideListFragment();


}
