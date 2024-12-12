package com.example.weatherapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapp.viewmodel.WeatherViewModel

@Composable
fun InputScreen(
    weatherViewModel: WeatherViewModel,
    onNavigateToToday: () -> Unit,
    onNavigateToWeekly: () -> Unit
) {
    var latitude by remember { mutableStateOf("") }
    var longitude by remember { mutableStateOf("") }
    var forecastDays by remember { mutableStateOf("7") }
    var temperatureUnit by remember { mutableStateOf("celsius") }
    var windSpeedUnit by remember { mutableStateOf("kmh") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Enter Location", style = androidx.compose.material3.MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = latitude,
            onValueChange = { latitude = it },
            label = { Text("Latitude") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = longitude,
            onValueChange = { longitude = it },
            label = { Text("Longitude") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = forecastDays,
            onValueChange = { forecastDays = it },
            label = { Text("Forecast Days (1-16)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = temperatureUnit,
            onValueChange = { temperatureUnit = it },
            label = { Text("Temperature Unit (celsius/fahrenheit)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = windSpeedUnit,
            onValueChange = { windSpeedUnit = it },
            label = { Text("Wind Speed Unit (kmh/ms/mph/kn)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val lat = latitude.toDoubleOrNull()
                val lon = longitude.toDoubleOrNull()
                val days = forecastDays.toIntOrNull()

                if (lat != null && lon != null && days != null && days in 1..16) {
                    weatherViewModel.fetchWeather(
                        latitude = lat.toFloat(),
                        longitude = lon.toFloat(),
                        forecastDays = days,
                        temperatureUnit = temperatureUnit,
                        windSpeedUnit = windSpeedUnit
                    )
                    onNavigateToToday()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = latitude.isNotBlank() && longitude.isNotBlank()
        ) {
            Text("Show Today's Weather")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onNavigateToWeekly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Show Weekly Weather")
        }
    }
}
