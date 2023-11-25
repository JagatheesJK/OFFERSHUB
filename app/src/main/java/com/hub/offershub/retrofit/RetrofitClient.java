package com.hub.offershub.retrofit;

import static com.hub.offershub.base.Constants.BASE_URL;

import android.util.Log;


import com.androidnetworking.interceptors.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(provideHttpLoggingInterceptor())
            .build();

    private static Retrofit retrofit = null;
    private static Retrofit retrofit1 = null;

    public static Retrofit getApiClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        return retrofit;
    }

    static HttpLoggingInterceptor provideHttpLoggingInterceptor(){
        return new HttpLoggingInterceptor(message ->
                Log.e("APILOGSTATUS===>", message)).setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}
