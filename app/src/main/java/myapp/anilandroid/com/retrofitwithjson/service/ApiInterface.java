package myapp.anilandroid.com.retrofitwithjson.service;

import myapp.anilandroid.com.retrofitwithjson.model.Employeelist;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiInterface {


    /*
    Retrofit get annotation with our URL
    And our method that will return us the List of EmployeeList
    */
    @GET("retrofit/json_object.json")
    Call<Employeelist> getMyJSON();


}
