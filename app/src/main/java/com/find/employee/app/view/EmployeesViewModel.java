package com.find.employee.app.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.find.employee.app.data.model.Employee;
import com.find.employee.app.data.rest.EmployeeRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class EmployeesViewModel extends ViewModel {

    private final EmployeeRepository employeeRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<List<Employee>> employeeList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private final MutableLiveData<String> countryName = new MutableLiveData<>();

    @Inject
    public EmployeesViewModel(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        disposable = new CompositeDisposable();
        fetchEmployeeList();
    }

    public LiveData<List<Employee>> getEmployeeList() {
        return employeeList;
    }

    LiveData<String> getCountryName(){return  countryName;}

    LiveData<Boolean> getError() {
        return loadError;
    }

    LiveData<Boolean> getLoading() {
        return loading;
    }

    public void fetchEmployeeList() {
        loading.setValue(true);
        disposable.add(employeeRepository.getEmployeeList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<Employee>>() {
                    @Override
                    public void onSuccess(List<Employee> value) {
                        loadError.setValue(false);
                        employeeList.setValue(value);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadError.setValue(true);
                        loading.setValue(false);
                    }
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
