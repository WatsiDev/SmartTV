package com.watsidev.producto3.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.watsidev.producto3.ui.screens.home.common.ContentSectionMovies
import com.watsidev.producto3.ui.screens.home.common.FeaturedBanner
import com.watsidev.producto3.ui.screens.home.common.TopNavBar

@Composable
fun VideoScreen(
    onHomeClick: () -> Unit,
    onGoTitle: (Int) -> Unit,
    onSearchMovie: (String) -> Unit,
    viewModel: MovieViewModel = viewModel()
) {
    val isLoading = viewModel.isLoading
    val popular = viewModel.popular
    val topRated = viewModel.topRated
    val nowPlaying = viewModel.nowPlaying
    val upcoming = viewModel.upcoming
    var searchQuery by remember { mutableStateOf("") }
    val searchResults = viewModel.searchResults
    val isSearching = viewModel.isSearching

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
//                .background(Color.Black)
                .padding(16.dp)
        ) {
            item {
                TopNavBar(
                    onHomeClick = { onHomeClick() },
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    onSearchMovie = { onSearchMovie(it) }
                )
            }
            item {
                FeaturedBanner(movie = popular.first())
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                ContentSectionMovies("Popular", popular, onGoTitle)
                Spacer(modifier = Modifier.height(32.dp))
            }

            item {
                ContentSectionMovies("Top Rated", topRated, onGoTitle)
                Spacer(modifier = Modifier.height(32.dp))
            }

            item {
                ContentSectionMovies("Now Playing", nowPlaying, onGoTitle)
                Spacer(modifier = Modifier.height(32.dp))
            }

            item {
                ContentSectionMovies("Upcoming", upcoming, onGoTitle)
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}
