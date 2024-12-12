package com.example.weatherapp.model

data class WeatherResponse(
    val hourly: HourlyWeatherData? = null, // Maps to "hourly" in the API response
    val daily: DailyWeatherData? = null   // Maps to "daily" in the API response
)

data class HourlyWeatherData(
    val time: List<String>? = null,            // List of timestamps for hourly data
    val temperature_2m: List<Float>? = null,    // List of temperatures corresponding to each timestamp
    val cloudcover: List<Float>? = null        // Cloud coverage for hourly data
)

data class DailyWeatherData(
    val time: List<String>? = null,             // List of dates for daily data
    val temperature_2m_min: List<Float>? = null, // List of minimum temperatures for each day
    val temperature_2m_max: List<Float>? = null,  // List of maximum temperatures for each day
    val cloudcover_mean: List<Float>? = null  // Mean cloud coverage for daily data
)



