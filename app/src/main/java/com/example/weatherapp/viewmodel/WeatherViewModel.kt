package com.example.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    val weatherData: StateFlow<WeatherResponse?> get() = _weatherData

    fun fetchWeather(
        latitude: Float,
        longitude: Float,
        hourly: String = "temperature_2m",
        daily: String = "temperature_2m_max,temperature_2m_min",
        timezone: String = "auto",
        forecastDays: Int = 7,
        temperatureUnit: String = "celsius",
    ) {
        viewModelScope.launch {
            try {
                val response = repository.getWeather(
                    latitude = latitude.toDouble(),
                    longitude = longitude.toDouble(),
                    hourly = hourly,
                    daily = daily,
                    timezone = timezone,
                    forecastDays = forecastDays,
                    temperatureUnit = temperatureUnit,
                )
                _weatherData.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}


