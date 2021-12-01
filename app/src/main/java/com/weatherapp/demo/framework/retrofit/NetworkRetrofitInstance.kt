package com.favortech.myapplication.framework.retrofit


import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkRetrofitInstance {

//    private val BASE_URL = "https://api.github.com/"
    private val BASE_URL = "https://api.openweathermap.org/data/2.5/"
//    https://api.openweathermap.org/data/2.5/forecast?q=nagpur&appid=e82807b473a06c766f5c095abc9c14f8
    val networkService by lazy {

        var interceptor = HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        var client : OkHttpClient =  OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(NetworkRetrofitService::class.java)
    }




}