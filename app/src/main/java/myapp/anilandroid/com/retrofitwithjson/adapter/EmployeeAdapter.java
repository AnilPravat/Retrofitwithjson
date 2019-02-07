package myapp.anilandroid.com.retrofitwithjson.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import myapp.anilandroid.com.retrofitwithjson.R;
import myapp.anilandroid.com.retrofitwithjson.model.Employee;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    Context context;
private List <Employee> employeeList;

    public EmployeeAdapter(Context context,List<Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView= LayoutInflater.from( parent.getContext() ).inflate( R.layout.row_dat_item,parent,false );
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EmployeeAdapter.ViewHolder holder, int position) {

        Employee employee=employeeList.get( position );
        holder.employeename.setText( employee.getName() );
        holder.email.setText( employee.getEmail() );
        holder.designation.setText( employee.getDesignation() );
        holder.salary.setText( employee.getSalary() );
        holder.dob.setText( employee.getDob() );
        holder.contactnumber.setText( employee.getContact_number() );
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView employeename, designation, email, salary, dob, contactnumber;

        public ViewHolder(View itemView) {
            super( itemView );

            employeename = (TextView) itemView.findViewById( R.id.employeeName );
            designation = (TextView) itemView.findViewById( R.id.designation );
            email = (TextView) itemView.findViewById( R.id.email );
            salary = (TextView) itemView.findViewById( R.id.salary );
            dob = (TextView) itemView.findViewById( R.id.dob );
            contactnumber = (TextView) itemView.findViewById( R.id.contactNumber );
        }
    }

}

