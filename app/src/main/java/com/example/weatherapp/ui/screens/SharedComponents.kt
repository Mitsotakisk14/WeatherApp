package com.example.weatherapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding


@Composable
fun CloudCoverageIndicator(cloudCoverage: Float) {
    val color = when {
        cloudCoverage < 25 -> Color.Blue  // Clear sky
        cloudCoverage < 50 -> Color.LightGray  // Partly cloudy
        cloudCoverage < 75 -> Color.Gray  // Mostly cloudy
        else -> Color.DarkGray  // Overcast
    }

    Box(
        modifier = Modifier
            .size(24.dp)
            .background(color, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "${cloudCoverage.toInt()}%",
            color = Color.White,
            modifier = Modifier.padding(2.dp)
        )
    }
}
