package com.example.skillforge

import com.example.skillforge.models.Courses
import retrofit2.http.GET

interface ApiInterface{
    @GET("courses")
    suspend fun getCourses(): List<Courses>
}