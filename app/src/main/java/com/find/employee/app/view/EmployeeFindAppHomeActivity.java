package com.find.employee.app.view;

import android.os.Bundle;

import com.find.employee.R;
import com.find.employee.app.base.BaseActivity;


public class EmployeeFindAppHomeActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.find_employee_activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.screenContainer, new EmployeeListFragment()).commit();
    }
}
