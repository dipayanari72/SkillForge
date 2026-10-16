package com.example.skillforge.navbar

import kotlinx.serialization.Serializable

sealed class NavRoutes {
    @Serializable
    data object Welcome : NavRoutes()

    @Serializable
    data object Home : NavRoutes()

    @Serializable
    data object Login : NavRoutes()
    @Serializable
    data object Register : NavRoutes ()

    @Serializable
    data object Progress : NavRoutes()

    @Serializable
    data object Explore : NavRoutes()
    @Serializable
    data class CourseDetails(val courseId : Int) : NavRoutes()
    @Serializable
    data object Profile : NavRoutes()
    @Serializable
    data object AiChat : NavRoutes()
}