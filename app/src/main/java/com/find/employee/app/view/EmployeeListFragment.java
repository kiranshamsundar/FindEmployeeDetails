package com.find.employee.app.view;

import android.app.SearchManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
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
    @BindView(R.id.mSearch)
    SearchView searchView;
    private EmployeeListAdapter adapter;

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
        adapter=new EmployeeListAdapter(viewModel, this);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                adapter.getFilter().filter(query);
                return false;
            }
        });




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
