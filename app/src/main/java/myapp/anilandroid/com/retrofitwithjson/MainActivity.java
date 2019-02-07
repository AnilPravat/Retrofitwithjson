package myapp.anilandroid.com.retrofitwithjson;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;
import java.util.ArrayList;

import myapp.anilandroid.com.retrofitwithjson.adapter.EmployeeAdapter;
import myapp.anilandroid.com.retrofitwithjson.database.Databasehelper;
import myapp.anilandroid.com.retrofitwithjson.model.Employee;
import myapp.anilandroid.com.retrofitwithjson.model.Employeelist;
import myapp.anilandroid.com.retrofitwithjson.service.ApiClient;
import myapp.anilandroid.com.retrofitwithjson.service.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<Employee> employeeArraylist;
    private List<Employee> Emplist;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private EmployeeAdapter employeeAdapter;
    Databasehelper databasehelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Emplist=new ArrayList<>(  );
        databasehelper=new Databasehelper( this );
        /*progressDialog=new ProgressDialog( MainActivity.this );
        progressDialog.setMessage("Loading Data.. Please wait...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();*/


         //Creating an object of our api interface
        ApiInterface api=  ApiClient.getApiService();
        /**
         * Calling JSON
         */
        Call<Employeelist> call=api.getMyJSON();
        /**
         * Enqueue Callback will be call when get response...
         */

        call.enqueue( new Callback<Employeelist>() {
            @Override
            public void onResponse(Call<Employeelist> call, Response<Employeelist> response) {

                //Dismiss Dialog
                //progressDialog.dismiss();
              //for insert the data.......
                if (response.isSuccessful()){
                     employeeArraylist=response.body().getEmployee();
                     int db_emp_count=databasehelper.getEmployeeCount();
                     if(db_emp_count==0){
                         for(int i=0;i<employeeArraylist.size();i++){
                             if(databasehelper.insertData(employeeArraylist.get( i ).getName(),employeeArraylist.get( i ).getDob(),employeeArraylist.get( i ).getDesignation(),employeeArraylist.get( i ).getContact_number(),employeeArraylist.get( i ).getEmail(),employeeArraylist.get( i ).getSalary() )){
                                 Toast.makeText( getApplicationContext(),"Successfully Inserted Data",Toast.LENGTH_SHORT ).show();
                                 getdatafromDatabase();
                             }else {
                                 Toast.makeText( getApplicationContext(),"Successfully Not Inserted Data",Toast.LENGTH_SHORT ).show();
                             }
                         }
                     }else {
                         getdatafromDatabase();
                     }


                   //for retrieving the data......




                     /*recyclerView=(RecyclerView)findViewById( R.id.recyclerview );
                     employeeAdapter=new EmployeeAdapter(MainActivity.this, employeeArraylist );
                     RecyclerView.LayoutManager layoutManager=new LinearLayoutManager( getApplicationContext() );
                     recyclerView.setLayoutManager( layoutManager );
                     recyclerView.setAdapter( employeeAdapter );*/
                }
            }

            @Override
            public void onFailure(Call<Employeelist> call, Throwable t) {
                //progressDialog.dismiss();

            }
        } );

    }

    private void getdatafromDatabase() {
        Emplist=databasehelper.getAllEmployeeData();
        recyclerView=(RecyclerView)findViewById( R.id.recyclerview );
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager( getApplicationContext() );
        recyclerView.setLayoutManager( layoutManager );
        employeeAdapter=new EmployeeAdapter(MainActivity.this, Emplist );
        recyclerView.setAdapter( employeeAdapter );


    }


}
