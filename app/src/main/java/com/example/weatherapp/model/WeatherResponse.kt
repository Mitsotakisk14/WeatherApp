package com.example.weatherapp.model

data class WeatherResponse(
    val hourlyWeather: List<HourlyWeather>, // Correctly maps to hourly weather items
    val dailyWeather: List<DailyWeather>   // Correctly maps to daily weather items
)

data class HourlyWeather(
    val time: String,
    val temperature: Float
)

data class DailyWeather(
    val date: String,
    val minTemperature: Float,
    val maxTemperature: Float
)


