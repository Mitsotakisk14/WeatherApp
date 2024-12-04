package com.example.weatherapp.network

import com.example.weatherapp.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("latitude") latitude: Float,
        @Query("longitude") longitude: Float,
        @Query("hourly") hourly: String = "temperature_2m",
        @Query("daily") daily: String = "temperature_2m_max,temperature_2m_min",
        @Query("timezone") timezone: String = "auto"
    ): WeatherResponse
}
