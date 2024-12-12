package com.example.weatherapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapp.viewmodel.WeatherViewModel

@Composable
fun WeeklyScreen(
    weatherViewModel: WeatherViewModel,
    onNavigateToToday: () -> Unit,
    onNavigateToHome: () -> Unit
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

        weatherData?.daily?.let { dailyData ->
            val dates = dailyData.time ?: listOf()
            val maxTemps = dailyData.temperature_2m_max ?: listOf()
            val minTemps = dailyData.temperature_2m_min ?: listOf()
            val cloudCovers = dailyData.cloudcover_mean ?: listOf()

            if (dates.isEmpty()) {
                Text(
                    text = "No weekly weather data available",
                    style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
                )
            } else {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(dates) { date ->
                        val index = dates.indexOf(date)
                        val maxTemp = maxTemps.getOrNull(index) ?: 0f
                        val minTemp = minTemps.getOrNull(index) ?: 0f
                        val cloudCoverage = cloudCovers.getOrNull(index) ?: 0f
                        DailyWeatherItem(date, maxTemp, minTemp, cloudCoverage)
                    }
                }
            }
        } ?: Text(
            text = "No data available",
            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onNavigateToToday,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Back to Today")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onNavigateToHome,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Back to Home")
        }
    }
}

@Composable
fun DailyWeatherItem(date: String, maxTemp: Float, minTemp: Float, cloudCoverage: Float) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = date)
            Text(text = "${minTemp}°C / ${maxTemp}°C")
        }
        CloudCoverageIndicator(cloudCoverage) // Shared function
    }
}





