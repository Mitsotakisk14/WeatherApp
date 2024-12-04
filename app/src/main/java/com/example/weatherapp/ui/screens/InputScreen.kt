package com.example.weatherapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun InputScreen(
    onNavigateToToday: () -> Unit,
    onNavigateToWeekly: () -> Unit
) {
    // State variables to hold the latitude and longitude inputs
    var latitude by remember { mutableStateOf(TextFieldValue("")) }
    var longitude by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Input field for latitude
        TextField(
            value = latitude,
            onValueChange = { newValue -> latitude = newValue },
            label = { Text("Enter Latitude") },
            placeholder = { Text("e.g., 37.7749") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Input field for longitude
        TextField(
            value = longitude,
            onValueChange = { newValue -> longitude = newValue },
            label = { Text("Enter Longitude") },
            placeholder = { Text("e.g., -122.4194") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Navigation Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    // Validate inputs and navigate to Today's weather
                    if (isValidCoordinate(latitude.text) && isValidCoordinate(longitude.text)) {
                        onNavigateToToday()
                    } else {
                        // Show error (can use a Snackbar or other UI element)
                    }
                },
                modifier = Modifier.weight(1f).padding(end = 8.dp)
            ) {
                Text("Today's Weather")
            }

            Button(
                onClick = {
                    // Validate inputs and navigate to Weekly weather
                    if (isValidCoordinate(latitude.text) && isValidCoordinate(longitude.text)) {
                        onNavigateToWeekly()
                    } else {
                        // Show error
                    }
                },
                modifier = Modifier.weight(1f).padding(start = 8.dp)
            ) {
                Text("Weekly Weather")
            }
        }
    }
}

// Helper function to validate coordinates
fun isValidCoordinate(input: String): Boolean {
    return input.toFloatOrNull() != null
}
