package com.example.weatherapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp.ui.screens.InputScreen
import com.example.weatherapp.ui.screens.TodayScreen
import com.example.weatherapp.ui.screens.WeeklyScreen

// Define the navigation routes
object WeatherRoutes {
    const val Input = "input"
    const val Today = "today"
    const val Weekly = "weekly"
}

@Composable
fun WeatherNavGraph(navController: NavHostController) {
    // Define the navigation flow
    NavHost(
        navController = navController,
        startDestination = WeatherRoutes.Input // Set the Input screen as the start
    ) {
        // Route for the Input screen
        composable(route = WeatherRoutes.Input) {
            InputScreen(
                onNavigateToToday = { navController.navigate(WeatherRoutes.Today) },
                onNavigateToWeekly = { navController.navigate(WeatherRoutes.Weekly) }
            )
        }

        // Route for Today's weather screen
        composable(route = WeatherRoutes.Today) {
            TodayScreen(navController = navController)
        }

        // Route for Weekly weather screen
        composable(route = WeatherRoutes.Weekly) {
            WeeklyScreen(navController = navController)
        }
    }
}
