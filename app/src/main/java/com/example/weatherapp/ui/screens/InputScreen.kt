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
    weatherViewModel: WeatherViewModel, // Inject the shared WeatherViewModel
    onNavigateToToday: () -> Unit,      // Navigate to the Today screen
    onNavigateToWeekly: () -> Unit     // Navigate to the Weekly screen
) {
    var latitude by remember { mutableStateOf("") }
    var longitude by remember { mutableStateOf("") }

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

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Trigger WeatherViewModel to fetch data
                val lat = latitude.toDoubleOrNull()
                val lon = longitude.toDoubleOrNull()

                if (lat != null && lon != null) {
                    weatherViewModel.fetchWeather(lat.toFloat(), lon.toFloat()) // Convert Double to Float
                    onNavigateToToday() // Navigate to Today screen
                } else {
                    // Handle invalid input (optional: show error message)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = latitude.isNotBlank() && longitude.isNotBlank()
        ) {
            Text("Show Today's Weather")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                // Navigate directly to Weekly Screen
                onNavigateToWeekly()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Show Weekly Weather")
        }
    }
}
