package com.example.weatherapp.repository

import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.network.ApiService

class WeatherRepository {
    private val api = ApiService.api

    suspend fun fetchWeatherData(latitude: Float, longitude: Float): WeatherResponse {
        return api.getWeatherForecast(
            latitude = latitude,
            longitude = longitude
        )
    }
}
