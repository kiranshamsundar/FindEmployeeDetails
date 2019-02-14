package com.find.employee.app.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.find.employee.R;
import com.find.employee.app.base.BaseFragment;
import com.find.employee.app.util.ViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;


public class EmployeeListFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView listView;
    @BindView(R.id.tv_error)
    TextView errorTextView;
    @BindView(R.id.loading_view)
    View loadingView;

    @Inject
    ViewModelFactory viewModelFactory;
    private EmployeesViewModel viewModel;

    @Override
    protected int layoutRes() {
        return R.layout.employee_list_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(EmployeesViewModel.class);

        listView.addItemDecoration(new DividerItemDecoration(getBaseActivity(), DividerItemDecoration.VERTICAL));
        listView.setAdapter(new EmployeeListAdapter(viewModel, this));
        listView.setLayoutManager(new LinearLayoutManager(getContext()));

        observableViewModel();

    }


    private void observableViewModel() {
        viewModel.getEmployeeList().observe(this, employees -> {
            if(employees != null) {
                listView.setVisibility(View.VISIBLE);
            }
        });

        viewModel.getError().observe(this, isError -> {
            if (isError != null) if(isError) {
                errorTextView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                errorTextView.setText(getString(R.string.msg_error_while_loading));
            }else {
                errorTextView.setVisibility(View.GONE);
                errorTextView.setText(null);
            }
        });

        viewModel.getLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    errorTextView.setVisibility(View.GONE);
                    listView.setVisibility(View.GONE);
                }
            }
        });

        viewModel.getCountryName().observe(this, countryName -> {
            if (countryName != null) {
                    getActivity().setTitle(countryName);
                }

        });
    }
}
