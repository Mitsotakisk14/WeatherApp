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
import com.example.weatherapp.model.DailyWeather

@Composable
fun WeeklyScreen(
    weatherViewModel: WeatherViewModel
) {
    val weatherData by weatherViewModel.weatherData.collectAsState(initial = null)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Weekly Weather Forecast",
            style = androidx.compose.material3.MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        weatherData?.dailyWeather?.let { dailyWeather ->
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(dailyWeather) { daily ->
                    DailyWeatherItem(daily ?: DailyWeather("Unknown", 0.0f, 0.0f))
                }
            }
        } ?: Text(
            text = "No data available",
            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun DailyWeatherItem(daily: DailyWeather) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = daily.date ?: "Unknown Date")
        Text(text = "${daily.minTemperature ?: 0.0f}°C / ${daily.maxTemperature ?: 0.0f}°C")
    }
}



