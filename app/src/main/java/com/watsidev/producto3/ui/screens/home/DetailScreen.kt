package com.watsidev.producto3.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.IconButton
import androidx.tv.material3.OutlinedButton
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.watsidev.producto3.R
import com.watsidev.producto3.model.data.Movie
import com.watsidev.producto3.ui.screens.home.common.ContentSectionMovies
import com.watsidev.producto3.ui.screens.home.common.TopNavBar

@Composable
fun DetailScreen(
    id: Int,
    viewModel: MovieViewModel = viewModel(),
    onGoTitle: (Int) -> Unit,
    onSearchMovie: (String) -> Unit,
    onHomeClick: () -> Unit
) {
    val movie = viewModel.selectedMovie
    var searchQuery by remember { mutableStateOf("") }

    // Fetch details solo una vez
    LaunchedEffect(id) {
        viewModel.fetchMovieById(id)
    }

    if (movie == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color.White)
        }
        return
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Fondo con imagen del banner
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w780${movie.backdropPath}",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Degradado oscuro
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Black, Color.Transparent),
                        startY = Float.POSITIVE_INFINITY,
                        endY = 0f
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            TopNavBar(
                onHomeClick = { onHomeClick() },
                value = searchQuery,
                onValueChange = { searchQuery = it },
                onSearchMovie = { onSearchMovie(it) }
            )
            Spacer(modifier = Modifier.height(60.dp))
            FeaturedBannerContent(movie)
            ContentSectionMovies("Popular", viewModel.popular, onGoTitle = { onGoTitle(it) })
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun FeaturedBannerContent(movie: Movie) {
    Column(
        modifier = Modifier.padding(top = 80.dp)
    ) {
        Text(
            text = movie.title,
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "${movie.releaseDate.take(4)} • ${movie.voteAverage} ★",
            color = Color.LightGray,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = movie.overview,
            color = Color.White,
            fontSize = 14.sp,
            maxLines = 3
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = { /* Acción de Play */ }) {
                Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color.Black)
                Text("Play", color = Color.Black)
            }
            Spacer(modifier = Modifier.width(12.dp))
            OutlinedButton(onClick = { /* Más info */ }) {
                Icon(Icons.Default.Info, contentDescription = null)
                Text("More Info")
            }
        }
    }
}
