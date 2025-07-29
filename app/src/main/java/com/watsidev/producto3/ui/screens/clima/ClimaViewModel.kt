package com.watsidev.producto3.ui.screens.clima

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class WeatherViewModel : ViewModel() {

    var uiState = mutableStateOf(WeatherUiState())
        private set

    fun loadWeather() {
        viewModelScope.launch {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("https://api.open-meteo.com/v1/forecast?latitude=19.0413&longitude=-98.2062&current_weather=true&hourly=temperature_2m,relative_humidity_2m,windspeed_10m,weathercode&daily=temperature_2m_max,temperature_2m_min,weathercode,relative_humidity_2m_min,relative_humidity_2m_max&timezone=auto&forecast_days=6")
                    .build()

                val response = withContext(Dispatchers.IO) {
                    client.newCall(request).execute()
                }

                val body = response.body?.string() ?: throw Exception("Respuesta vacía")
                val json = JSONObject(body)

                val current = json.getJSONObject("current_weather")
                val code = current.getInt("weathercode")
                val temp = current.getDouble("temperature")
                val wind = current.getDouble("windspeed")
                val time = current.getString("time")

                val daily = json.getJSONObject("daily")
                val dates = daily.getJSONArray("time")
                val tempsMin = daily.getJSONArray("temperature_2m_min")
                val tempsMax = daily.getJSONArray("temperature_2m_max")
                val codes = daily.getJSONArray("weathercode")
                val humidityMax = daily.getJSONArray("relative_humidity_2m_max")
                val humidity = humidityMax.getDouble(0)

                val forecastList = (0 until dates.length()).map {
                    ForecastItem(
                        date = dates.getString(it),
                        min = tempsMin.getDouble(it),
                        max = tempsMax.getDouble(it),
                        iconRes = mapWeatherCodeToIcon(codes.getInt(it))
                    )
                }

                val hourly = json.getJSONObject("hourly")
                val hours = hourly.getJSONArray("time")
                val hourlyTemps = hourly.getJSONArray("temperature_2m")
                val hourlyHumidity = hourly.getJSONArray("relative_humidity_2m")
                val hourlyWind = hourly.getJSONArray("windspeed_10m")
                val hourlyCodes = hourly.getJSONArray("weathercode")

                val hourlyList = (0 until hours.length()).map {
                    HourlyForecastItem(
                        hour = hours.getString(it).substring(11, 16),
                        temperature = hourlyTemps.getDouble(it),
                        humidity = hourlyHumidity.getDouble(it),
                        windSpeed = hourlyWind.getDouble(it),
                        iconRes = mapWeatherCodeToIcon(hourlyCodes.getInt(it))
                    )
                }

                uiState.value = WeatherUiState(
                    city = "Puebla",
                    temperature = temp,
                    windSpeed = wind,
                    humidity = humidity,
                    time = time,
                    weatherCode = code,
                    forecast = forecastList,
                    hourlyForecast = hourlyList,
                    isLoading = false
                )

            } catch (e: Exception) {
                uiState.value = WeatherUiState(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }
}
