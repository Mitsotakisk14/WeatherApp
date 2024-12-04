package com.example.weatherapp.model

data class WeatherResponse(
    val hourly: HourlyWeatherData,
    val daily: DailyWeatherData
)

data class HourlyWeatherData(
    val time: List<String>,
    val temperature_2m: List<Float>
)

data class DailyWeatherData(
    val time: List<String>,
    val temperature_2m_max: List<Float>,
    val temperature_2m_min: List<Float>
)
