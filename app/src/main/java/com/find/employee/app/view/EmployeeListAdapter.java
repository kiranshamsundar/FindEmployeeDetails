package com.find.employee.app.view;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.find.employee.R;
import com.find.employee.app.data.model.Employee;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.EmployeeViewHolder>  implements Filterable {

    private List<Employee> employeeList = new ArrayList<>();
    private List<Employee> filteredEmployeeList = new ArrayList<>();

    EmployeeListAdapter(EmployeesViewModel viewModel, LifecycleOwner lifecycleOwner) {
        viewModel.getEmployeeList().observe(lifecycleOwner, employeeList -> {
            this.employeeList.clear();
            if (employeeList != null ) {
                this.employeeList.addAll(employeeList);
                notifyDataSetChanged();
            }
        });

    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_list_item, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        holder.bind(employeeList.get(position));
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredEmployeeList = employeeList;
                } else {
                    List<Employee> filteredList = new ArrayList<>();
                    for (Employee row : employeeList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getFirstName().toLowerCase().contains(charString.toLowerCase()) || row.getLastName().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    filteredEmployeeList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredEmployeeList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredEmployeeList = (ArrayList<Employee>) filterResults.values;

                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }

    static final class EmployeeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        public TextView name;
        @BindView(R.id.employee_num)
        public TextView employeeNum;
        @BindView(R.id.location)
        public TextView location;
        @BindView(R.id.role)
        public TextView role;

        private Employee employee;

        EmployeeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        void bind(Employee countryFact) {
            this.employee = countryFact;

            if (this.employee.getFirstName() != null) {
                name.setText(this.employee.getFirstName());
            } else {
                /**
                 * Handle if fact's title is unavailable
                 */
                name.setText(R.string.msg_no_title);
            }
            if (this.employee.getEmployeeNum() != 0) {
                employeeNum.setText("" + this.employee.getEmployeeNum());
            } else {
                /**
                 * Handle if fact description is unavailable
                 */
                employeeNum.setText(R.string.msg_no_description);
            }

            if (this.employee.getRole() != null) {
                role.setText(this.employee.getRole());
            } else {
                /**
                 * Handle if fact description is unavailable
                 */
                role.setText(R.string.msg_no_description);


            }

            if (this.employee.getLocation() != null) {
                location.setText(this.employee.getLocation());
            } else {
                /**
                 * Handle if fact description is unavailable
                 */
                location.setText(R.string.msg_no_description);


            }
        }

    }
}
