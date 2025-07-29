package com.watsidev.producto3.ui.screens.clima

data class ForecastItem(
    val date: String,
    val min: Double,
    val max: Double,
    val iconRes: Int
)

data class HourlyForecastItem(
    val hour: String,
    val temperature: Double,
    val humidity: Double,
    val windSpeed: Double,
    val iconRes: Int
)