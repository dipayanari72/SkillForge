package com.example.skillforge.models



data class Courses(
    val id: Int,
    val title : String,
    val description : String,
    val instructor : String,
    val imageUrl : String,
    val category : String,
    val level : String,
    val duration : String
)
