package com.example.mynetworklibrary

import retrofit2.Call
import retrofit2.http.GET

interface NetworkServiceInterface {


    @GET("mobileapps/android_assignment.json")
    fun fetchCustomUI(): Call<CustomUi>

    @GET("portal/images/logo")
    fun fetchImage(): Call<String>


}