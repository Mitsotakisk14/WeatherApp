package com.example.weatherapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

// Sample placeholder data for daily weather
data class DailyWeather(
    val date: String,
    val minTemp: String,
    val maxTemp: String,
    val condition: String
)

val sampleWeeklyData = listOf(
    DailyWeather("Mon, Dec 4", "10°C", "18°C", "Sunny"),
    DailyWeather("Tue, Dec 5", "12°C", "20°C", "Partly Cloudy"),
    DailyWeather("Wed, Dec 6", "8°C", "16°C", "Rainy"),
    DailyWeather("Thu, Dec 7", "6°C", "14°C", "Cloudy"),
    DailyWeather("Fri, Dec 8", "7°C", "15°C", "Sunny"),
    DailyWeather("Sat, Dec 9", "9°C", "17°C", "Clear"),
    DailyWeather("Sun, Dec 10", "11°C", "19°C", "Sunny")
)

@Composable
fun WeeklyScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Weekly Weather") },
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
                text = "7-Day Weather Forecast",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            DailyWeatherList(weeklyData = sampleWeeklyData)
        }
    }
}

// Composable function to render a scrollable list of daily weather data
@Composable
fun DailyWeatherList(weeklyData: List<DailyWeather>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(weeklyData) { weather ->
            DailyWeatherItem(weather = weather)
        }
    }
}

// Composable function to render a single item of daily weather
@Composable
fun DailyWeatherItem(weather: DailyWeather) {
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
            Column {
                Text(text = weather.date, style = MaterialTheme.typography.bodyLarge)
                Text(text = weather.condition, style = MaterialTheme.typography.bodyMedium)
            }
            Column(horizontalAlignment = androidx.compose.ui.Alignment.End) {
                Text(text = "Min: ${weather.minTemp}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Max: ${weather.maxTemp}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
