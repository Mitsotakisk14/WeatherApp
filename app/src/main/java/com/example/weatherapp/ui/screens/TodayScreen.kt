package com.example.weatherapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

// Sample placeholder data for hourly weather
data class HourlyWeather(
    val time: String,
    val temperature: String,
    val condition: String
)

val sampleHourlyData = listOf(
    HourlyWeather("06:00", "15°C", "Clear"),
    HourlyWeather("09:00", "18°C", "Sunny"),
    HourlyWeather("12:00", "22°C", "Partly Cloudy"),
    HourlyWeather("15:00", "24°C", "Cloudy"),
    HourlyWeather("18:00", "20°C", "Clear"),
    HourlyWeather("21:00", "17°C", "Clear Night")
)

@Composable
fun TodayScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Today's Weather") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        // Main content for the screen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Detailed Hourly Forecast",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            HourlyWeatherList(hourlyData = sampleHourlyData)
        }
    }
}

// Composable function to render a scrollable list of hourly weather data
@Composable
fun HourlyWeatherList(hourlyData: List<HourlyWeather>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(hourlyData) { weather ->
            HourlyWeatherItem(weather = weather)
        }
    }
}

// Composable function to render a single item of hourly weather
@Composable
fun HourlyWeatherItem(weather: HourlyWeather) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = weather.time, style = MaterialTheme.typography.bodyLarge)
            Text(text = weather.temperature, style = MaterialTheme.typography.bodyLarge)
            Text(text = weather.condition, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
