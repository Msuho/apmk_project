package com.company.benefit.network

import com.company.benefit.network.Constants.Companion.BASE_URL
import com.company.benefit.network.CreateOkHttpClient.getUnsafeOkHttpClient
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val gson = GsonBuilder().setLenient().create()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(getUnsafeOkHttpClient().build())
            .build()
    }

    val api : RetrofitApi by lazy {
        retrofit.create(RetrofitApi::class.java)
    }

}