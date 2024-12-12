package com.example.weatherapp.repository

import com.example.weatherapp.model.DailyWeather
import com.example.weatherapp.model.HourlyWeather
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.network.WeatherApi
import android.util.Log

class WeatherRepository(private val api: WeatherApi) {

    suspend fun getWeather(latitude: Double, longitude: Double): WeatherResponse {
        try {
            val response = api.getWeather(
                latitude = latitude,
                longitude = longitude,
                hourly = "temperature_2m",
                daily = "temperature_2m_max,temperature_2m_min"
            )

            val hourlyWeather = response.hourlyWeather ?: emptyList()
            val dailyWeather = response.dailyWeather ?: emptyList()

            return WeatherResponse(hourlyWeather = hourlyWeather, dailyWeather = dailyWeather)
        } catch (e: Exception) {
            Log.e("WeatherRepository", "Error fetching weather data", e)
            return WeatherResponse()
        }
    }
}


