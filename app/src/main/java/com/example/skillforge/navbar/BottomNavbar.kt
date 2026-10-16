package com.example.skillforge.navbar

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.outlined.GraphicEq
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BottomNavBar(navController: NavController,routes: String) {
    val navItems =listOf(
        NavItem("Home",Icons.Outlined.Home, NavRoutes.Home),
        NavItem("Explore", Icons.AutoMirrored.Filled.MenuBook, NavRoutes.Explore),
        NavItem("Progress", Icons.Outlined.GraphicEq, NavRoutes.Progress),
        NavItem("Profile", Icons.Outlined.Person, NavRoutes.Profile),
    )
        NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier.height(70.dp)
    ) {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = item.title == routes,
                onClick = {
                    navController.navigate(item.routes){
                        popUpTo(navController.graph.startDestinationId){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        modifier = Modifier.size(25.dp)
                    )
                },
                label = { Text(text = item.title) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Blue,       // Color when selected
                    unselectedIconColor = Color.Gray,      // Color when not selected
                    selectedTextColor = Color.Blue,       // Label color when selected
                    unselectedTextColor = Color.Gray,     // Label color when not selected
                    indicatorColor = Color.Gray.copy(alpha = 0.03f)     // Removes the Material 3 pill background highlight if you don't want it
                ),

            )

        }
    }
}
data class NavItem(
    val title : String,
    val icon : ImageVector,
    val routes : NavRoutes
)
