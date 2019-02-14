package com.find.employee.app.data.rest;

import com.find.employee.app.data.model.Employee;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class EmployeeRepository {

    private final EmployeeService employeeService;

    @Inject
    public EmployeeRepository(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Single<List<Employee>> getEmployeeList() {
        return employeeService.getEmployeeList();
    }

}
