package com.example.weatherapp.repository

import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.network.WeatherApi
import android.util.Log

class WeatherRepository(private val api: WeatherApi) {

    suspend fun getWeather(
        latitude: Double,
        longitude: Double,
        hourly: String = "temperature_2m",
        daily: String = "temperature_2m_max,temperature_2m_min",
        timezone: String = "auto",
        forecastDays: Int = 7,
        temperatureUnit: String = "celsius",
    ): WeatherResponse {
        return try {
            api.getWeather(
                latitude = latitude,
                longitude = longitude,
                hourly = hourly,
                daily = daily,
                timezone = timezone,
                forecastDays = forecastDays,
                temperatureUnit = temperatureUnit,
            )
        } catch (e: Exception) {
            Log.e("WeatherRepository", "Error fetching weather data", e)
            WeatherResponse() // Return an empty response in case of failure
        }
    }
}



