package com.example.skillforge.database

import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase
import com.example.skillforge.models.EnrolledCourse

@Database(entities = [EnrolledCourse::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase(){
    companion object{
        fun getInstance(context: Context):  AppDatabase{
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "enrolled_course"
            ).build()
        }
    }
    abstract fun enrolledCourseDao(): EnrolledCourseDao
}