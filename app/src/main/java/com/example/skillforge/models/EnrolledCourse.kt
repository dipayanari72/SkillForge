package com.example.skillforge.models

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "enrolled_course")
data class EnrolledCourse (
    @PrimaryKey
    val courseId : Int,
    val title : String,
    val description : String,
    val instructor: String,
    val imageUrl: String,
    val category: String,
    val level: String,
    val duration: String,
)