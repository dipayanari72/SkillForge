package com.example.skillforge.data

import com.example.skillforge.database.EnrolledCourseDao
import com.example.skillforge.models.EnrolledCourse
import kotlinx.coroutines.flow.Flow

class EnrollmentRepository(private val dao: EnrolledCourseDao) {
      suspend fun enroll(course: EnrolledCourse){
        dao.enroll(course)
    }
    suspend fun disEnroll(courseId: Int){
        dao.disEnrolled(courseId)
    }
    suspend fun disEnrollAllCourse(){
        dao.disEnrollAllCourse()
    }
    fun getEnrolledCourses(): Flow<List<EnrolledCourse>> {
        return dao.getEnrolledCourse()
    }
    suspend fun getCurrentEnrollment(): EnrolledCourse?{
      return dao.getCurrentEnrollment()
    }
    fun isEnrolled(courseId: Int): Flow<Boolean> {
        return dao.isEnrolled(courseId)
    }
}