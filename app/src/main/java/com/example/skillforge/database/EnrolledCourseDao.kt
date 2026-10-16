package com.example.skillforge.database

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import com.example.skillforge.models.EnrolledCourse
import kotlinx.coroutines.flow.Flow

@Dao
interface EnrolledCourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun enroll(course: EnrolledCourse)

    @Query("SELECT * FROM enrolled_course")
    fun getEnrolledCourse(): Flow<List<EnrolledCourse>>

    @Query("DELETE FROM enrolled_course WHERE courseId = :courseId")
    suspend fun disEnrolled(courseId: Int)

    @Query("DELETE FROM enrolled_course")
    suspend fun disEnrollAllCourse()
    @Query("SELECT EXISTS(SELECT 1 FROM enrolled_course WHERE courseId = :courseId)")
    fun isEnrolled(courseId: Int): Flow<Boolean>

    @Query("SELECT * FROM enrolled_course LIMIT 1")
    suspend fun getCurrentEnrollment(): EnrolledCourse?
}