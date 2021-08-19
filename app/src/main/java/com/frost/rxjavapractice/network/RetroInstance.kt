package com.frost.rxjavapractice.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    companion object{
        val baseURL = "https://www.googleapis.com/books/v1/" //volumes?q=harry

        fun getRetrofitInstance(): Retrofit =
            Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }
}