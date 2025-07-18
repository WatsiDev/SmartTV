package com.watsidev.producto3.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.watsidev.producto3.model.data.Movie
import com.watsidev.producto3.ui.screens.home.common.TopNavBar
import retrofit2.http.Query

@Composable
fun SearchScreen(
    query: String,
    onGoTitle: (Int) -> Unit,
    onHomeClick: () -> Unit,
    viewModel: MovieViewModel = viewModel()
) {
    LaunchedEffect(query) {
        viewModel.searchMovies(query)
    }
    val searchResults = viewModel.searchResults
    val isSearching = viewModel.isSearching

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        TopNavBar(
            onHomeClick = onHomeClick,
            value = "", // o puedes pasar el query actual
            onValueChange = {}, // si quieres deshabilitar cambios aquí
            onSearchMovie = {}  // deshabilitado aquí o puedes volver a buscar
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (searchResults.isEmpty()) {
            Text(
                text = "No se encontraron resultados.",
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            LazyColumn {
                items(searchResults) { movie ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { onGoTitle(movie.id) }
                    ) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                            contentDescription = movie.title,
                            modifier = Modifier
                                .width(100.dp)
                                .height(150.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = movie.title ?: "Sin título",
                            color = Color.White,
                            fontSize = 20.sp,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
            }
        }
    }
}
