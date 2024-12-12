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
            // Get the current hour in the same format as the API (e.g., "2024-12-12T14:00")
            val currentHour = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:00"))

            // Filter data starting from the current hour
            val times = hourlyData.time?.filter { it >= currentHour } ?: listOf()
            val temperatures = hourlyData.temperature_2m?.takeLast(times.size) ?: listOf()

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(times) { time ->
                    val index = times.indexOf(time)
                    val temperature = temperatures.getOrNull(index) ?: 0f
                    HourlyWeatherItem(time, temperature)
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
fun HourlyWeatherItem(time: String, temperature: Float) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = time)
        Text(text = "${temperature}°C")
    }
}


