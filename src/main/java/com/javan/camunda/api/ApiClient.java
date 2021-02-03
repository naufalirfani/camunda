package com.javan.camunda.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public OkHttpClient getInterceptor(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
    }

    public Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("http://sistemis.cloud.javan.co.id/engine-rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}

