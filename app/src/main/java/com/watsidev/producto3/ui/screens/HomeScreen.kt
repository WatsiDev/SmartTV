package com.watsidev.producto3.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Text

@Composable
fun HomeScreen() {
    val sections = listOf("Seguir viendo", "Populares", "Nuevos lanzamientos", "Recomendados")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        item {
            FeaturedBanner()
            Spacer(modifier = Modifier.height(24.dp))
        }

        items(sections) { section ->
            ContentSection(title = section)
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun FeaturedBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.DarkGray)
    ) {
        Text(
            text = "🎬 Título Destacado",
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp),
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ContentSection(title: String) {
    Column {
        Text(
            text = title,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyRow {
            items(10) { index ->
                MovieCard("Póster #$index")
            }
        }
    }
}

@Composable
fun MovieCard(title: String) {
    Column(
        modifier = Modifier
            .width(120.dp)
            .padding(end = 12.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Gray)
    ) {
        Box(
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth()
                .background(Color.DarkGray)
        ) {
            // Aquí iría una imagen con Coil o Glide
        }
        Text(
            text = title,
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
}
