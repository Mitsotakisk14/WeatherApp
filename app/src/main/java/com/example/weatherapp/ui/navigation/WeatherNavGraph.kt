package com.example.weatherapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp.ui.screens.InputScreen
import com.example.weatherapp.ui.screens.TodayScreen
import com.example.weatherapp.ui.screens.WeeklyScreen
import com.example.weatherapp.viewmodel.WeatherViewModel

object WeatherRoutes {
    const val Input = "input"
    const val Today = "today"
    const val Weekly = "weekly"
}

@Composable
fun WeatherNavGraph(
    navController: NavHostController,
    weatherViewModel: WeatherViewModel
) {
    NavHost(
        navController = navController,
        startDestination = WeatherRoutes.Input
    ) {
        composable(route = WeatherRoutes.Input) {
            InputScreen(
                weatherViewModel = weatherViewModel,
                onNavigateToToday = { navController.navigate(WeatherRoutes.Today) },
                onNavigateToWeekly = { navController.navigate(WeatherRoutes.Weekly) }
            )
        }

        composable(route = WeatherRoutes.Today) {
            TodayScreen(
                weatherViewModel = weatherViewModel,
                onNavigateToWeekly = { navController.navigate(WeatherRoutes.Weekly) },
                onNavigateToHome = { navController.popBackStack(WeatherRoutes.Input, inclusive = false) }
            )
        }

        composable(route = WeatherRoutes.Weekly) {
            WeeklyScreen(
                weatherViewModel = weatherViewModel,
                onNavigateToToday = { navController.popBackStack(WeatherRoutes.Today, inclusive = false) },
                onNavigateToHome = { navController.popBackStack(WeatherRoutes.Input, inclusive = false) }
            )
        }
    }
}

