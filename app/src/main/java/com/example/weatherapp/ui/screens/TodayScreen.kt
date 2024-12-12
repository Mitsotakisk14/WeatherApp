package com.example.weatherapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapp.viewmodel.WeatherViewModel
import com.example.weatherapp.model.HourlyWeather

@Composable
fun TodayScreen(
    weatherViewModel: WeatherViewModel,
    onNavigateToWeekly: () -> Unit
) {
    val weatherData by weatherViewModel.weatherData.collectAsState(initial = null)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Today's Weather",
            style = androidx.compose.material3.MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        weatherData?.hourlyWeather?.let { hourlyWeather ->
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(hourlyWeather) { hourly ->
                    HourlyWeatherItem(hourly ?: HourlyWeather("Unknown", 0.0f))
                }
            }
        } ?: Text(
            text = "No data available",
            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        androidx.compose.material3.Button(
            onClick = onNavigateToWeekly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "View Weekly Weather")
        }
    }
}

@Composable
fun HourlyWeatherItem(hourly: HourlyWeather) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = hourly.time ?: "Unknown Time")
        Text(text = "${hourly.temperature ?: 0.0f}°C")
    }
}
