package myapp.anilandroid.com.retrofitwithjson.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient  {

    private static final String ROOT_URL = "http://api.androiddeft.com/";

    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory( GsonConverterFactory.create())
                .build();
    }

    public static ApiInterface getApiService() {
        return  getRetrofitInstance().create(ApiInterface.class);
    }
}
