package com.watsidev.producto3.ui.screens.game

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun SciFiMainMenu(onStartGame: () -> Unit, onQuitGame: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFF050A30), Color(0xFF0D1B2A))))
    ) {
        // Fondo holográfico (simulado con un círculo animado si se quiere)
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(300.dp)
                .background(Color(0xFF1E90FF).copy(alpha = 0.3f), shape = CircleShape)
                .border(2.dp, Color.Cyan, CircleShape)
        )

        // Título
        Text(
            "COMMAND BEAT",
            color = Color.Cyan,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.Center)
        )

        // Botones del menú
        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 48.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SciFiButton("START GAME", isSelected = true) { onStartGame() }
//            SciFiButton("MAP SELECT")
            SciFiButton("SETTINGS")
            SciFiButton("ABOUT US")
            SciFiButton("QUIT GAME", isSelected = false) { onQuitGame() }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            repeat(4) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color(0xFF1B263B), shape = RoundedCornerShape(12.dp))
                        .border(2.dp, Color.Cyan, RoundedCornerShape(12.dp))
                )
            }
        }

        // Copyright
        Text(
            "Copyright ©2025 WatsiDev",
            fontSize = 12.sp,
            color = Color.LightGray,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp)
        )

        Text(
            "Ver. 1.6.0",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.dp)
        )
    }
}

@Composable
fun SciFiButton(text: String, isSelected: Boolean = false, onClick: () -> Unit = {}) {
    val backgroundColor = if (isSelected) Color(0xFFEEC643) else Color(0xFF1B263B)
    val borderColor = if (isSelected) Color.Yellow else Color.Cyan

    Text(
        text,
        style = MaterialTheme.typography.labelLarge,
        color = Color.White,
        modifier = Modifier
            .clickable { onClick() }
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .border(2.dp, borderColor, RoundedCornerShape(8.dp))
            .padding(horizontal = 24.dp, vertical = 12.dp)
    )
}
