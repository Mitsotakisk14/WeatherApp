package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.navigation.WeatherNavGraph
import com.example.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // Create a navigation controller
                val navController: NavHostController = rememberNavController()

                // App Surface and Navigation
                Surface(color = MaterialTheme.colorScheme.background) {
                    WeatherNavGraph(navController = navController)
                }
            }
        }
    }
}
