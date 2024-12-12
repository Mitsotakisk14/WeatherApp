package com.example.weatherapp.model

data class WeatherResponse(
    val hourlyWeather: List<HourlyWeather>? = null,
    val dailyWeather: List<DailyWeather>? = null
)

data class HourlyWeather(
    val time: String? = null,
    val temperature: Float? = null
)

data class DailyWeather(
    val date: String? = null,
    val minTemperature: Float? = null,
    val maxTemperature: Float? = null
)



