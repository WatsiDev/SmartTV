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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.tv.material3.Text
import com.watsidev.producto3.R
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CoverScreen(
    onClick: () -> Unit
) {
    val context = LocalContext.current
    val time = remember { mutableStateOf(LocalTime.now()) }
    val date = remember { mutableStateOf(LocalDate.now()) }

    // Simulamos condiciones del clima
    val isDay = time.value.hour in 6..18
    val isRainy = false // Puedes cambiar esto para simular

    val weatherIcon = when {
        isDay && !isRainy -> R.drawable.sun
        isDay && isRainy -> R.drawable.cloudy
        !isDay && !isRainy -> R.drawable.sun
        else -> R.drawable.cloudy
    }

    // Actualiza cada minuto
    LaunchedEffect(Unit) {
        while (true) {
            delay(60_000)
            time.value = LocalTime.now()
            date.value = LocalDate.now()
        }
    }

    // Fondo completo
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .clickable{ onClick() }
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Información del clima
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(32.dp)
        ) {
            Image(
                painter = painterResource(id = weatherIcon),
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
            Text(
                text = if (isRainy) "Lluvioso" else if (isDay) "Soleado" else "Despejado",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        // Fecha y hora
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
                text = date.value.format(DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy",
                    Locale("es", "MX")
                )),
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}
