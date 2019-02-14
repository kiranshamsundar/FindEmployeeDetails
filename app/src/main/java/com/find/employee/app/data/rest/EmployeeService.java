package com.find.employee.app.data.rest;

import com.find.employee.app.data.model.Employee;
import com.find.employee.app.data.model.EmployeeList;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface EmployeeService {

   // @GET("s/2iodh4vg0eortkl/facts.js")
    //Single<EmployeeList> getEmployeeList();

    @GET("/getEmployees")
    Single<List<Employee>> getEmployeeList();



}
