package com.example.skillforge

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private fun getInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://mycourses.free.beeceptor.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getApiService() : ApiInterface{
        return getInstance().create(ApiInterface::class.java)
    }
}