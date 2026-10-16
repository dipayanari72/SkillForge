package com.example.skillforge.viewmodels

import android.util.Log
import androidx.compose.ui.graphics.vector.EmptyPath
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skillforge.RetrofitInstance
import com.example.skillforge.models.Courses
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CourseViewmodel: ViewModel() {
    private val _courses = MutableStateFlow<List<Courses>>(emptyList())
    val courses = _courses.asStateFlow()
    init {
        getCourses()
    }
    private fun getCourses(){
        viewModelScope.launch {
            try {
                val result = RetrofitInstance.getApiService().getCourses()
                result.forEach {
                    Log.d("CourseViewmodel", "Course: ${it.title}")
                }
                _courses.value = result
            }catch (e: Exception){
                e.printStackTrace()
                Log.e(
                    "CourseViewmodel",
                    "Failed to fetch courses",
                    e
                )
            }
        }
    }
}