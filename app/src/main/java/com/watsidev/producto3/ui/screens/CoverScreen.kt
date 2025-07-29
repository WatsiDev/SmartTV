package com.watsidev.producto3.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.Text
import com.watsidev.producto3.R
import com.watsidev.producto3.ui.screens.clima.WeatherViewModel
import com.watsidev.producto3.ui.screens.clima.mapWeatherCodeToDescription
import com.watsidev.producto3.ui.screens.clima.mapWeatherCodeToIcon
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalTvMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CoverScreen(
    onClick: () -> Unit,
    viewModel: WeatherViewModel = viewModel()
) {
    val context = LocalContext.current
    val time = remember { mutableStateOf(LocalTime.now()) }
    val date = remember { mutableStateOf(LocalDate.now()) }

    val weatherState by viewModel.uiState

    // Cargar clima solo una vez
    LaunchedEffect(Unit) {
        viewModel.loadWeather()
    }

    // Actualiza hora/fecha cada minuto
    LaunchedEffect(Unit) {
        while (true) {
            delay(60_000)
            time.value = LocalTime.now()
            date.value = LocalDate.now()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // 🔸 Clima real desde el ViewModel
        if (!weatherState.isLoading && weatherState.error == null) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(32.dp)
            ) {
                Icon(
                    painter = painterResource(id = mapWeatherCodeToIcon(weatherState.weatherCode)),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = Color.White
                )
                Text(
                    text = mapWeatherCodeToDescription(weatherState.weatherCode),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "${weatherState.temperature.toInt()}°",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        } else {
            // 🔸 Indicador de carga o error
            Column(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(32.dp)
            ) {
                if (weatherState.isLoading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(32.dp))
                } else {
                    Icon(
                        imageVector = Icons.Default.ErrorOutline,
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }

        // 🔸 Fecha y hora actual
        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(32.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = time.value.format(DateTimeFormatter.ofPattern("HH:mm")),
                color = Color.White,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = date.value.format(
                    DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", Locale("es", "MX"))
                ),
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}
