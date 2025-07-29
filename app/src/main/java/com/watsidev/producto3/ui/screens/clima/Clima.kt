package com.watsidev.producto3.ui.screens.clima

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.InvertColors
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.collections.firstOrNull

@Composable
fun WeatherScreenTV(
    viewModel: WeatherViewModel = viewModel(),
    onBackToMenu: () -> Unit
) {
    val state by viewModel.uiState

    LaunchedEffect(Unit) {
        viewModel.loadWeather()
    }

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color.White)
        }
        return
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF001B48))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = state.city, color = Color.White, fontSize = 20.sp)
                Spacer(Modifier.height(8.dp))
                Icon(
                    painter = painterResource(id = mapWeatherCodeToIcon(state.weatherCode)),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = Color.White
                )
                Text(text = mapWeatherCodeToDescription(state.weatherCode), color = Color.LightGray, fontSize = 16.sp)
                Text(text = "${state.temperature.toInt()}°", color = Color.White, fontSize = 48.sp)
                Text(
                    text = "Máx: ${state.forecast.firstOrNull()?.max?.toInt()}° | Mín: ${state.forecast.firstOrNull()?.min?.toInt()}°",
                    color = Color.LightGray, fontSize = 14.sp
                )
                Text(text = "Actualizado: ${formatHour(state.time)}", color = Color.Gray, fontSize = 12.sp)
            }
        }

        item {
            Spacer(Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.InvertColors, contentDescription = null, tint = Color.Cyan)
                    Text("${state.humidity.toInt()}%", color = Color.White)
                    Text("Humedad", color = Color.Gray)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.Air, contentDescription = null, tint = Color.Cyan)
                    Text("${state.windSpeed.toInt()} km/h", color = Color.White)
                    Text("Viento", color = Color.Gray)
                }
            }
        }

        item {
            Spacer(Modifier.height(16.dp))
            Text("Hoy por hora", color = Color.White, fontSize = 18.sp)
        }

        items(state.hourlyForecast.take(12)) { hourly ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(hourly.hour, color = Color.White)
                Icon(
                    painter = painterResource(id = hourly.iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.White
                )
                Text("${hourly.temperature.toInt()}°", color = Color.LightGray)
                Text("${hourly.humidity.toInt()}%", color = Color.Cyan)
            }
        }

        item {
            Spacer(Modifier.height(16.dp))
            Button(onClick = onBackToMenu) {
                Icon(Icons.Outlined.ArrowBackIosNew, contentDescription = "Volver")
            }
        }

        item {
            Text("Próximos días", color = Color.White, fontSize = 18.sp, modifier = Modifier.padding(vertical = 8.dp))
        }
        items(state.forecast.drop(1)) { forecast ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(forecast.getFormattedDay(), color = Color.White)
                Icon(
                    painter = painterResource(id = forecast.iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color.White
                )
                Text("${forecast.min.toInt()}°/${forecast.max.toInt()}°", color = Color.Gray)
            }
        }
    }
}
