package com.find.employee.app.data.model;

import java.util.ArrayList;

public class EmployeeList {

    private ArrayList<Employee> employees;

    public EmployeeList(String title, ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
}
