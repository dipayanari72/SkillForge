package com.example.skillforge.navbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.skillforge.data.AiChatbotRepository
import com.example.skillforge.data.SessionManager
import com.example.skillforge.screens.AIChatScreen
import com.example.skillforge.screens.CourseDetailScreen
import com.example.skillforge.screens.ExploreScreen
import com.example.skillforge.screens.HomeScreen
import com.example.skillforge.screens.LoginScreen
import com.example.skillforge.screens.ProfileScreen
import com.example.skillforge.screens.ProgressScreen
import com.example.skillforge.screens.RegisterScreen
import com.example.skillforge.screens.WelcomeScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun NavGraph (sessionManager: SessionManager){

    val navController = rememberNavController()
    val isLoggedIn by sessionManager.isLoggedIn.collectAsState(initial = false)
    val currentUser = FirebaseAuth.getInstance().currentUser
    val startDestination = if (isLoggedIn || currentUser != null){
        NavRoutes.Home
    }else{
        NavRoutes.Welcome
    }
    NavHost(navController = navController, startDestination = startDestination){
        composable<NavRoutes.Welcome> {
            WelcomeScreen(navController)
        }
        composable<NavRoutes.Login> {

            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(NavRoutes.Home) {

                        popUpTo(NavRoutes.Login) {
                            inclusive = true
                        }
                    }
                },
                onRegisterClick ={
                    navController.navigate(NavRoutes.Register)
                }
            )
        }
        composable<NavRoutes.Register>{
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(NavRoutes.Login){
                        popUpTo(NavRoutes.Login){
                            inclusive = true
                        }
                    }
                },
                onLoginClick = {
                    navController.navigate(NavRoutes.Register)
                }
            )
        }
        composable<NavRoutes.Home> {

            HomeScreen(navController)
        }
        composable<NavRoutes.Progress> {
            ProgressScreen(navController)
        }
        composable<NavRoutes.Profile>{
            ProfileScreen(navController,
                onLogout = {
                    navController.navigate(NavRoutes.Login){
                        popUpTo(0){
                            inclusive = true
                        }
                    }
                })
        }
        composable<NavRoutes.Explore>{
            ExploreScreen(navController)
        }
        composable<NavRoutes.CourseDetails> { backStackEntry ->
            val route = backStackEntry.toRoute<NavRoutes.CourseDetails>()
            CourseDetailScreen (courseId = route.courseId,
                onBackClick ={
                    navController.popBackStack()
                }
            )
        }
        composable<NavRoutes.AiChat> {
            AIChatScreen(
                onBack ={
                    navController.popBackStack()
                })
        }
    }
}