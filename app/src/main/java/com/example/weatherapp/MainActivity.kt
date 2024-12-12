package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.navigation.WeatherNavGraph
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.viewmodel.WeatherViewModel
import com.example.weatherapp.viewmodel.WeatherViewModelFactory
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.network.ApiService
import com.example.weatherapp.network.WeatherApi
import com.jakewharton.threetenabp.AndroidThreeTen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ThreeTenABP
        AndroidThreeTen.init(this)

        // Create an instance of WeatherApi using ApiService
        val api = ApiService.createService(WeatherApi::class.java)

        // Create the repository and ViewModelFactory
        val repository = WeatherRepository(api)
        val factory = WeatherViewModelFactory(repository)

        // Create the WeatherViewModel
        val weatherViewModel: WeatherViewModel by viewModels { factory }

        setContent {
            WeatherAppTheme {
                // Create a navigation controller
                val navController: NavHostController = rememberNavController()

                // App Surface and Navigation
                Surface(color = MaterialTheme.colorScheme.background) {
                    // Pass the WeatherViewModel to the WeatherNavGraph
                    WeatherNavGraph(
                        navController = navController,
                        weatherViewModel = weatherViewModel
                    )
                }
            }
        }
    }
}
