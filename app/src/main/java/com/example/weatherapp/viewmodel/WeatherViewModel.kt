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

    fun fetchWeather(latitude: Float, longitude: Float) {
        viewModelScope.launch {
            try {
                // Call the correct repository method
                val response = repository.getWeather(latitude.toDouble(), longitude.toDouble())
                _weatherData.value = response
            } catch (e: Exception) {
                // Handle errors
                e.printStackTrace()
            }
        }
    }
}

