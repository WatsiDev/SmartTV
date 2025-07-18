package com.watsidev.producto3.ui.screens.home.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.watsidev.producto3.model.data.Movie

@Composable
fun MovieCardFromApi(movie: Movie, onGoTitle: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .width(120.dp)
            .padding(end = 12.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.DarkGray)
            .clickable { onGoTitle(movie.id) }
    ) {
        AsyncImage(
            model = movie.fullPosterUrl(),
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth()
        )
    }
}
