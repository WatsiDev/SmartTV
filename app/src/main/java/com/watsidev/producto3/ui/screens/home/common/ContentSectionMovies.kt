package com.watsidev.producto3.ui.screens.home.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Text
import com.watsidev.producto3.model.data.Movie

@Composable
fun ContentSectionMovies(title: String, items: List<Movie>, onGoTitle: (Int) -> Unit) {
    Column {
        Text(
            text = title,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyRow {
            items(items) { movie ->
                MovieCardFromApi(movie, onGoTitle)
            }
        }
    }
}
