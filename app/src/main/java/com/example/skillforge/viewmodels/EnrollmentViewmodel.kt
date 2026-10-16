package com.example.skillforge.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.skillforge.data.EnrollmentRepository
import com.example.skillforge.database.AppDatabase
import com.example.skillforge.models.Courses
import com.example.skillforge.models.EnrolledCourse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EnrollmentViewmodel(application: Application): AndroidViewModel(application) {
    private val dao = AppDatabase.getInstance(application).enrolledCourseDao()
    private val repository = EnrollmentRepository(dao)
    private val _course = MutableStateFlow<EnrolledCourse?>(null)
    val currentCourse = _course.asStateFlow()
    fun isEnrolled(courseId : Int): Flow<Boolean>{
        return repository.isEnrolled(courseId)
    }
    fun getEnrollMentCourseDetails(){
        viewModelScope.launch {
           _course.value = repository.getCurrentEnrollment()
        }
    }
    fun checkEnrollMent(courses: Courses,onAlreadyEnrolled: ()-> Unit,onFirstEnrollment: ()-> Unit){
        viewModelScope.launch {
            val currentCourse = repository.getCurrentEnrollment()
            if (currentCourse == null){
                onFirstEnrollment()
            }else if (currentCourse.courseId == courses.id){
                onAlreadyEnrolled()
            }else{
                onAlreadyEnrolled()
            }
        }
    }
      fun enroll(courses: Courses){
        viewModelScope.launch {
            repository.enroll(
                EnrolledCourse(
                courseId = courses.id,
                title = courses.title,
                description = courses.description,
                instructor = courses.instructor,
                imageUrl = courses.imageUrl,
                category = courses.category,
                level = courses.level,
                duration = courses.duration
            )
            )
        }
    }
    fun disEnroll(courseId : Int){
        viewModelScope.launch {
            repository.disEnroll(courseId)
        }
    }

}