package com.example.mynetworklibrary

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://demo.ezetap.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}