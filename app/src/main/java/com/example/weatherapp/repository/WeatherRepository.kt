package com.example.weatherapp.repository

import com.example.weatherapp.model.DailyWeather
import com.example.weatherapp.model.HourlyWeather
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.network.WeatherApi

class WeatherRepository(private val api: WeatherApi) {

    suspend fun getWeather(latitude: Double, longitude: Double): WeatherResponse {
        // Fetch raw weather data from the API
        val response = api.getWeather(
            latitude = latitude,
            longitude = longitude,
            hourly = "temperature_2m",
            daily = "temperature_2m_max,temperature_2m_min"
        )
        // Map hourly weather data
        val hourlyWeather = response.hourlyWeather // Already mapped in API response

        // Map daily weather data
        val dailyWeather = response.dailyWeather // Already mapped in API response

        // Return mapped weather data
        return WeatherResponse(
            hourlyWeather = hourlyWeather,
            dailyWeather = dailyWeather
        )
    }
}

