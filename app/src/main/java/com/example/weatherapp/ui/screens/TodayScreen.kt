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
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

@Composable
fun TodayScreen(
    weatherViewModel: WeatherViewModel,
    onNavigateToWeekly: () -> Unit,
    onNavigateToHome: () -> Unit
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

        weatherData?.hourly?.let { hourlyData ->
            val currentHour = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:00"))
            val times = hourlyData.time?.filter { it >= currentHour } ?: listOf()
            val temperatures = hourlyData.temperature_2m?.takeLast(times.size) ?: listOf()
            val cloudCovers = hourlyData.cloudcover?.takeLast(times.size) ?: listOf()

            if (times.isEmpty()) {
                Text(
                    text = "No weather data available for today",
                    style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
                )
            } else {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(times) { time ->
                        val index = times.indexOf(time)
                        val temperature = temperatures.getOrNull(index) ?: 0f
                        val cloudCoverage = cloudCovers.getOrNull(index) ?: 0f
                        HourlyWeatherItem(time, temperature, cloudCoverage)
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
            onClick = onNavigateToWeekly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "View Weekly Weather")
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
fun HourlyWeatherItem(time: String, temperature: Float, cloudCoverage: Float) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = time)
            Text(text = "${temperature}°C")
        }
        CloudCoverageIndicator(cloudCoverage) // Shared function
    }
}





