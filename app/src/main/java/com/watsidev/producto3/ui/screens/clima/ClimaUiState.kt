package com.watsidev.producto3.ui.screens.clima

data class WeatherUiState(
    val city: String = "Puebla",
    val temperature: Double = 0.0,
    val windSpeed: Double = 0.0,
    val humidity: Double = 0.0,
    val time: String = "",
    val weatherCode: Int = 0,
    val forecast: List<ForecastItem> = emptyList(),
    val hourlyForecast: List<HourlyForecastItem> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
)
