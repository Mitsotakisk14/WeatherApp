package com.example.weatherapp.network

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.weatherapp.model.WeatherResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeatherApiTest {

    @Test
    fun testWeatherApiCall() = runBlocking {
        // Given
        val api = ApiService.api
        val latitude = 37.7749f  // Example latitude (San Francisco)
        val longitude = -122.4194f  // Example longitude (San Francisco)

        // When
        val response: WeatherResponse = api.getWeatherForecast(
            latitude = latitude,
            longitude = longitude
        )

        // Then
        assertNotNull("API response should not be null", response)
        assertTrue("Hourly data should not be empty", response.hourly.time.isNotEmpty())
        assertTrue("Daily data should not be empty", response.daily.time.isNotEmpty())
    }
}
